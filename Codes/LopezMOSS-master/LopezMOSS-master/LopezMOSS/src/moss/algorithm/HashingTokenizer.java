package moss.algorithm;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Creates tokens and token clusters from I/O readers
 */
@SuppressWarnings("unused")
//CHANGE: Suppressed unused warning
class HashingTokenizer {
    private StreamTokenizer tokenizer;
    //CHANGE: this was moved from the TokenClusterOccurrenceTable class because it is more efficient to just ignore a token right from the start
    /*CHANGE: Removed black-listing. It cannot be found anywhere in the entire project. There are currently too many issues with implementing a blacklist
     *such as where to filter it out. However, it is important that it is implemented at some point in the future because there is actually a need to
     * ignore certain common tokens such as if and for because they occur too much in the code to be reliable
     * Thus,
     * TODO: Implement black-listing (see comment in HashingTokenizer for details)
    * */
    static final int QUOTE = '"';

    static class TokenizerEndException extends Exception{
       TokenizerEndException(String message){
           super(message);
       }
    }

    public void addCharacterToken(int ch){
        tokenizer.ordinaryChar(ch);
    }

    /**isAtEnd simply checks if the next if the next token is the end-of-file character
     * In general, this tokenizer will not return an exception once you try to read beyond
     * @return Whether the end-of-file token has been reached
     * */
    final boolean isAtEnd(){
        //A significant design decision here is the fact that an IOException will crash the system unwarranted after printing the stack trace
        //this function will not tamper with the order of the tokens
        tokenizer.pushBack();
        try {
            return tokenizer.nextToken() == StreamTokenizer.TT_EOF;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return true;
    }


    /**
     * @return the next token yet to be read from the Reader.
     * @throws IOException throws when the next token cannot be read from the Reader
     */
    final Token getNextTokenInfo() throws IOException, TokenizerEndException {
        //CHANGE: The EOF token has been removed from the tokenizer. The user of the class might need to do an isAtEnd check before using this
        if (this.isAtEnd()) throw new TokenizerEndException("Reached end of file. Use isAtEnd method to do prior checking");
        Token.TokenBuilder tokenBuilder = new Token.TokenBuilder();
        int tokenNumber = tokenizer.nextToken();
        int id;
        switch(tokenNumber){
            case StreamTokenizer.TT_EOL:
                tokenBuilder.setType(Token.TYPE.EOL);
                break;
            case StreamTokenizer.TT_NUMBER:
                //CHANGE: recently changed id for number to the NUMBER constant instead of the integer's hash value
                tokenBuilder.setTypeWithValue(Token.TYPE.NUMBER, tokenizer.nval);
                break;
            case StreamTokenizer.TT_WORD:
                //For strings
                tokenBuilder.setTypeWithValue(Token.TYPE.WORD, tokenizer.sval);
                break;
            default:
                //extra checks
                //a quote check here
                if (tokenNumber == QUOTE) tokenBuilder.setTypeWithValue(Token.TYPE.STRING_LITERAL, tokenizer.sval);
                else tokenBuilder.setTypeWithValue(Token.TYPE.OTHER, tokenNumber);
                break;
        }
        return tokenBuilder.setLineNo(tokenizer.lineno()).createToken();
    }

    /**
     * this creates a sequence (cluster) of an arbitrary number of tokens yet to be read
     * @see TokenCluster
     * @param size the number of tokens to be clustered
     * @return returns a cluster of <b>at least</b><i>size</i> tokens. If the end-of-file has been reached, it will return however many remained
     * @throws TokenizerEndException thrown only when there are no more tokens remaining to begin with (see return value)
     *
     */
    final TokenCluster getNextTokenCluster(int size) throws IOException, TokenizerEndException {
        //Notes on the TokenizerEndException: this cannot be contained here because it will just return an empty cluster, which the user might not expect.
        /*Phase 1: this is the initial isAtEnd check, in the scenario that there is no more tokens right at the start of the call,
          a TokenizerEndException is thrown immediately. */
        if (this.isAtEnd()) throw new TokenizerEndException(
                "Cluster could not be formed. No tokens could be read prior to the end of file. " +
                "Consider using isAtEnd method for a priori checking");


        /*Phase 2: clusters the next at-least [size] tokens.*/
        TokenCluster.TokenClusterBuilder clusterOrganizer = new TokenCluster.TokenClusterBuilder();
        for (int i = 0; i < size; ++i){
            // since the first end-check has been passed by this point,
            // any encounter with the end-of-file just stops further collection (hence the break)
            if (this.isAtEnd()) break;
            clusterOrganizer.addNext(this.getNextTokenInfo());
        }

        return clusterOrganizer.cluster();
    }

    /**
     * @param size size of the token clusters to be returned
     * @return a collection of all clusters of a particular <i>size</i> from the Reader.
     */
    final Collection<TokenCluster> remainingTokenClusters(int size) throws IOException {
        Collection<TokenCluster> tokenClusters = new ArrayList<>();
        while(!this.isAtEnd()) {
            try {
                tokenClusters.add(this.getNextTokenCluster(size));
            } catch (TokenizerEndException ignored){}
            //IMPORTANT NOTE: this exception is contained in this class because the isAtEnd at the top will help us avoid this anyway
        }
        return tokenClusters;
    }



    HashingTokenizer(Reader reader){
        tokenizer = new StreamTokenizer(reader);
        tokenizer.quoteChar(QUOTE);


    }
}
