package moss.algorithm;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;

@SuppressWarnings("WeakerAccess")
class TokenClusterOccurrenceTable {
    private HashMap<TokenCluster, Integer> occurrences;
    static int DEFAULT_CLUSTER_SIZE = 2;

    /**
     * Creates the table
     */
    TokenClusterOccurrenceTable(){
        occurrences = new HashMap<>();
    }


    /**
     * Adds the token cluster to the table of clusters that have occurred (if absent) or adds one to the
     * occurrence count of that cluster if present
     * @param tokenCluster Cluster to be added
     */
    //adds the token to the occurrences table. Puts it in if the token has yet to occur
    private void addOccurred(TokenCluster tokenCluster){
        occurrences.putIfAbsent(tokenCluster, 0);
        occurrences.put(tokenCluster,
                occurrences.get(tokenCluster) + 1);
    }

    /**
     * Adds all the tokens from a reader to the table
     * @param reader source of tokens to be added to the table
     * @throws IOException Thrown if there is a problem communicating with <b>reader</b>.
     */
    void tabulate(Reader reader) throws IOException {
        HashingTokenizer tokenizer = new HashingTokenizer(reader);
        for (TokenCluster cluster : tokenizer.remainingTokenClusters(DEFAULT_CLUSTER_SIZE)){
            this.addOccurred(cluster);
        }
    }


    /**
     * @return The number of unique tokens that have been tabulated more than once.
     */
    final int collisionCount(){
        //CHANGE: Changed to final to prevent overriding
        int count = 0;
        for (Integer singleCount : this.occurrences.values()){
            if (singleCount >= 2) count++;
        }
        return count;
    }


    /**
     * @return Number of unrepeated tokens
     */
    final int uniqueCount(){
        return this.total() - this.collisionCount();
    }

    /**
     * @return The total number of unique tokens that have been tabulated.
     */
    //quick delegation
    final int total(){
        //CHANGE: Changed to final to prevent overriding
        //NOTE: Upon testing, there seems to be cases for which this might return 0 for some strange reason
        if (this.occurrences.size() == 0) System.err.println("Error detected");
        return this.occurrences.size();
    }



}
