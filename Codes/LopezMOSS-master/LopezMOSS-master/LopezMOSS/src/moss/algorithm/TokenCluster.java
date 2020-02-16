package moss.algorithm;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Used to hold a sequential group of tokens
 */
class TokenCluster {
    private final Collection<Token> tokens;

    /**
     * @param o cluster being compared with
     * @return equality of the two clusters based on the equality of each token in them
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenCluster that = (TokenCluster) o;
        return tokens.equals(that.tokens);
    }

    /**
     * @return combined hash code of all the <i>Token</i>s in the cluster
     */
    @Override
    public int hashCode() {
        return Objects.hash(tokens);
    }

    /**
     * The TokenCluster class is meant to be immutable upon construction,
     * so adding of tokens will and can only be done through this builder. You can add Token(s) sequentially in this Builder.
     */
    static class TokenClusterBuilder{
        private Collection<Token> tokens;
        TokenClusterBuilder(){ this.tokens = new ArrayList<>(); }

        /**
         * This will bring <b>tok</b> to the end of the cluster
         * @param tok token to be added to the cluster
         */
        void addNext(Token tok){ tokens.add(tok); }

        /**
         * @return a cluster of all the added tokens
         */
        TokenCluster cluster(){ return new TokenCluster(tokens); }
    }

    /**
     * @param tokens Container for tokens to be placed in the cluster. Holds the tokens in the proper sequence
     */
    private TokenCluster(Collection<Token> tokens){
        this.tokens = tokens;
    }
}
