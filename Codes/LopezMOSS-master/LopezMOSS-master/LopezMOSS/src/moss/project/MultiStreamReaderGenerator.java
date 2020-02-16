package moss.project;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Collection;
import java.util.Collections;

/*the purpose of this class is to collect
* a bunch of readers, combine them into a single
* buffer of memory, and generate a byte stream
* when necessary.
* The benefits of this class is that it minimizes the actual number of copies of the characters needed by the package.
* */
class MultiStreamReaderGenerator {
    private final byte[] combinedByteArray;

    /**
     * @param streams Input streams to be combined
     * @throws IOException Thrown when one input stream fails
     */
    MultiStreamReaderGenerator(Collection<? extends InputStream> streams) throws IOException {
        SequenceInputStream combinedReaderStream =
                new SequenceInputStream(Collections.enumeration(streams));
        ByteArrayOutputStream byteos = new ByteArrayOutputStream();
        IOUtils.copy(combinedReaderStream, byteos);
        combinedByteArray = byteos.toByteArray();
    }

    /**
     *
     *creates an independent reader to the collection of streams. This means that the generated readers are independent of one another.
     * but at the same time, it does not distribute any unnecessary copies of the combined streams
     *
     * @return Reader generated from combining all the input streams
     * */
    final Reader generate(){
        return new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream(combinedByteArray)));
    }
}
