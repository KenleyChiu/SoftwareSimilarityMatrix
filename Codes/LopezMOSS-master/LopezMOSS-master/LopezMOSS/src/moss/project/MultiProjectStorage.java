package moss.project;

import com.sun.org.apache.xpath.internal.operations.Mult;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Class that stores multiple projects
 */
public class MultiProjectStorage implements Iterable<Project>{
    //NOTES on this iterator: The decision to make this project iterator is motivated mainly by the fact that
    //I have observed that another module has become too dependent on multiple objects of this package.

    final private Collection<Project> projects;

    /**
     * @return Number of projects stored
     */
    public int projectCount(){
        return projects.size();
    }

    /**
     * @return An iterator for all the contained projects
     */
    @Override
    public final Iterator<Project> iterator() {
        return projects.iterator();
    }

    /**
     * Takes projects from the subdirectories of a path and stores them
     * @param projectsFolder Folder that contains the projects
     * @param filter Path filter for files
     * @return An iterable storage
     */
    public static MultiProjectStorage projectsIn(Path projectsFolder, PathFilter filter){
        return new MultiProjectStorage(projectsFolder, filter);
    }

    /**
     * Takes projects from a collection and stores them
     * @param projects Collection of projects to be stored
     * @return An iterable storage of projects
     */
    public static MultiProjectStorage fromCollection(Collection<Project> projects){
        return new MultiProjectStorage(projects);
    }

    /**
     * @param projectPaths Collection of paths to each project to be added
     * @return Storage for project objects for every path in the collection
     */
    public static MultiProjectStorage fromPathCollection(Collection<Path> projectPaths, PathFilter filters){
        return new MultiProjectStorage(projectPaths, filters);
    }


    /**
     * @param projectPaths Collection of paths to each project to be added
     */
    private MultiProjectStorage(Collection<Path> projectPaths, PathFilter filters){
        this.projects = new ArrayList<>();
        for (Path projectPath : projectPaths){
            //TODO: This is precisely the same snippet of code as the one from the other constructor. Consider code extraction
            ProjectBuilder projectBuilder = new ProjectBuilder();
            projects.add(projectBuilder
                    .setPath(projectPath)
                    .setFilter(filters)
                    .createProject());
        }
    }

    /**
     * @param projects Collection of projects to be stored
     */
    private MultiProjectStorage(Collection<Project> projects){
        this.projects = projects;
    }


    /**
     * @param projectsFolder Folder containing projects to be stored
     * @param filter Filter for particular types of files
     */
    private MultiProjectStorage(Path projectsFolder, PathFilter filter){
        projects = new ArrayList<>();

        try (Stream<Path> projectPath = Files.list(projectsFolder)) {
            projectPath
                    .filter(Files::isDirectory)
                    .forEach((path) -> {
                        ProjectBuilder projectBuilder = new ProjectBuilder();
                        projects.add(projectBuilder
                                .setPath(path)
                                .setFilter(filter)
                                .createProject());
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
