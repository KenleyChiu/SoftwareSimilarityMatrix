package moss.project;

import java.io.Reader;
import java.nio.file.Path;

/**
 * Holds a programming project folder
 */
public class Project {
    private final String name;
    private final Path path;
    private final ProjectFlatReaderDistributor readerDistributor;


    /**
     * @param path Path to the project folder
     * @param name Name of the project folder
     * @param globFilter The GLOB-formatted filter text used to pick which files can be accessed from this object
     */
    Project(Path path, String name, PathFilter globFilter){
        this.name = name;
        this.path = path;
        readerDistributor = new ProjectFlatReaderDistributor(this.path, globFilter);
    }

    /**
     * @return A <i>Reader</i> that spits out a concatenation of all files in the project.
     * @see Reader
     */
    //CHANGE: This was changed to public because it is needed by some facilities outside the project package
    public final Reader getConcatenatedReader(){
        return readerDistributor.distribute();
    }

    /**
     * @return The path to the project
     * @see Path
     */
    public final Path getPath(){
        return this.path;
    }


    /**
     * @return The name of the project
     */
    public final String getName() {
        return this.name;
    }
}
