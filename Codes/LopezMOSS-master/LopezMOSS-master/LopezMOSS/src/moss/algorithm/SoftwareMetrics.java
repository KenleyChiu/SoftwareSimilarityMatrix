package moss.algorithm;

import moss.project.PathFilter;
import moss.project.Project;
import moss.project.ProjectBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;

/**
 * Takes in a project object
 * and allows the user to compute
 * different metrics for that project
 * based on Haelstead's software metrics.
 */
@SuppressWarnings("WeakerAccess")
final public class SoftwareMetrics{
    private final Project measuredProject;
    private final TokenClusterOccurrenceTable table;


    /**
     * @param project Project to be measured
     * @return Software metric object from the project
     */
    public static SoftwareMetrics fromProject(Project project) throws IOException {
        return new SoftwareMetrics(project);
    }

    /**
     * @param path Path of the project
     * @param filters Filters to be applied to that path
     * @return Software metric object from the project
     */
    public static SoftwareMetrics fromPath(Path path, PathFilter filters) throws IOException {
        ProjectBuilder projectBuilder = new ProjectBuilder();
        projectBuilder
                .setPath(path)
                .setName(path.toString())
                .setFilter(filters);
        return SoftwareMetrics
                .fromProject(projectBuilder.createProject());
    }



    /**
     * @param project Project to be tested for metrics
     * @throws IOException If project reader cannot be read
     */
    private SoftwareMetrics(Project project) throws IOException {
        this.measuredProject = project;
        table = new TokenClusterOccurrenceTable();
        table.tabulate(project.getConcatenatedReader());
    }

    /**
     * @return The volume of the project
     */
    public final double volume() {
        return table.total() * Math.log(table.uniqueCount());
    }



}