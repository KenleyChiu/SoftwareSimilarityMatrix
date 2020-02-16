package moss.algorithm;

import java.io.IOException;
import java.io.Reader;

/**
 * Interface for all comparison strategies for the MOSS project
 * All subclasses of this class are obligated to:
 * 1. Restore their internal state to its original state after comparison. It is recommended that there be no way to store internal state if possible.
      and instead stick to the use of local variables
 */
@FunctionalInterface
public interface ComparisonStrategy {
    Double compare(Reader str1, Reader str2) throws IOException;
}
