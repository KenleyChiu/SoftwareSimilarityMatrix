package moss.project;


import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

/**
 * Combines filtered files from a single path to a single reader.
 */
public class ProjectFlatReaderDistributor {
    private final MultiStreamReaderGenerator generator;

    public ProjectFlatReaderDistributor(Path projectPath){
        this(projectPath, PathFilter.NO_FILTER);
    }

    /**
     * if an empty string is passed to extension, this will not do any extension filtering
     * the filter uses the GLOB syntax
     * @param projectPath Path to the project
     * @param globFilter GLOB filter for files to be combined
     */
    public ProjectFlatReaderDistributor(Path projectPath, PathFilter globFilter) {
        MultiStreamReaderGenerator generatorTmp;
        Collection<InputStream> inputFileStreams = new ArrayList<>();

        try(Stream<Path> paths = Files.walk(projectPath)) {
            paths
                    .filter(Files::isRegularFile)
                    .filter(globFilter::matchesAll)
                    .forEach(path -> {
                        try {
                            inputFileStreams.add(Files.newInputStream(path));
                        } catch (IOException e) {
                            //TODO: Handle properly (This will happen if the file stream could not be created)
                            e.printStackTrace();
                        }
                    });
            generatorTmp = new MultiStreamReaderGenerator(inputFileStreams);
        } catch (IOException e) {
            //TODO: Handle properly
            e.printStackTrace();
            generatorTmp = null;
            System.exit(1);
        }


        generator = generatorTmp;
    }


    /**
     * @return A copy of the combined flat reader
     */
    Reader distribute(){
        return generator.generate();
    }
}
