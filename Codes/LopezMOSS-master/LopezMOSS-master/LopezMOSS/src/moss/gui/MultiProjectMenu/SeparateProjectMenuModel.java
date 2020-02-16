package moss.gui.MultiProjectMenu;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A model for the separate project option
 */
@SuppressWarnings("WeakerAccess")
public final class SeparateProjectMenuModel {
    private Collection<Path> projectPaths = new ArrayList<>();


    /**
     * @param projectPath Project path to be added
     */
    public void addProject(Path projectPath){
        projectPaths.add(projectPath);
    }

    /**
     * @return Collection of project paths collected for processing
     */
    public Collection<Path> retrieveProjectPaths(){
        return projectPaths;
    }
}
