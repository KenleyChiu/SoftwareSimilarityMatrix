package moss.algorithm;

import java.io.IOException;
import java.io.Reader;

/**
 * An algorithm that compares two readers based
 * on their component tokens using N-Gram comparisons with token hashing
 */
public class TokenHashingStrategy implements ComparisonStrategy {


    /**
     * [CHANGE] Deprecation notice has been removed. The strategy for all such instances of a ComparisonStrategy
     * is that they should be contractually obligated to be in a state where they are reusable after use
     */
    public TokenHashingStrategy(){

    }


    /**
     * @param str1 First reader to be compared
     * @param str2 Second reader to be compared
     * @return A number between 0.0 and 1.0 that indicates the percentage of closeness of the two readers based on their tokens
     * @throws IOException Thrown when one of the readers fail
     */
    //returns a score between 0.0 and 1.0
    @Override
    public Double compare(Reader str1, Reader str2) throws IOException {
        //CHANGE: The token cluster tables have been converted to a local variable to allow reusability of this class
        TokenClusterOccurrenceTable table = new TokenClusterOccurrenceTable();
        table.tabulate(str1);
        table.tabulate(str2);
        return (double) table.collisionCount() / (double) table.total();
    }


}
