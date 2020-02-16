package moss.gui.MultiProjectMenu;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import moss.algorithm.TokenHashingStrategy;
import moss.project.MultiProjectStorage;
import moss.project.PathFilter;
import moss.projectpairmachine.ProjectsCorrelationMatrix;
import moss.projectpairmachine.SimpleMultiProjectComparison;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Stream;

/**
 * Services to be used by the multi-project menu GUI
 */
@SuppressWarnings("WeakerAccess")
public class MultiProjectMenuService {
    //CHANGE: Removed @Inject for comparisonMachine because it makes no difference. Only one instance of this class will be used.
    //A static reference will be used instead
    private static final SimpleMultiProjectComparison comparisonMachine = SimpleMultiProjectComparison.fromStrategy(new TokenHashingStrategy());

    /**
     * @param projectsFolder Folder containing all the projects to be compared
     * @param filters Filters to be imposed upon the project folders
     * @return A matrix containing the results
     */
    public ProjectsCorrelationMatrix processMultiProjectFolder(Path projectsFolder, PathFilter filters){
        MultiProjectStorage projects =
                MultiProjectStorage.projectsIn(projectsFolder, filters);
        return comparisonMachine.compareAll(projects);
    }


    /**
     * @param folder Path whose subdirectories will be counted
     * @return Number of folders/directories in the <b>folder</b> path
     */
    public int folderCount(Path folder){
        int count = 0;
        try(Stream<Path> paths = Files.list(folder)) {
            //Removes all non-directories then returns the count of whatever remains
            return (int) paths
                        .filter(Files::isDirectory)
                        .count();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }



    /**
     * @param paths Collection of paths to be included in the project
     * @param filters Filters to be imposed upon the projects
     * @return A matrix containing the results
     */
    public ProjectsCorrelationMatrix processMultiPaths(Collection<Path> paths, PathFilter filters){
        MultiProjectStorage projects =
                MultiProjectStorage.fromPathCollection(paths, filters);
        return comparisonMachine.compareAll(projects);
    }


    /**
     * This will bring up
     * @return Path given by the user
     */
    Path requestDirectoryFromUser() {
        //TODO: Consider moving this to the multi-project menu presenter. Having a GUI-based request feels out of place here
        //NOTE: This has been made package-private precisely because of the reasons stated above.
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        final File requestedDirectory = directoryChooser.showDialog(new Stage());
        //We need to check if the directory is valid
        if (requestedDirectory != null){
            return requestedDirectory.toPath();
        } else{
            //if the user fails to pick a path, the original path he came from will be returned instead
            //this is to prevent errors from happening in the system.
            return directoryChooser.getInitialDirectory().toPath();
        }

    }
}
