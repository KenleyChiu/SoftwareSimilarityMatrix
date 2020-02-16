package moss.gui.MultiProjectMenu;

import moss.project.PathFilter;

import javax.inject.Inject;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Model for the multi-project menu. Contains stuff to be displayed by the table menu
 */
@SuppressWarnings("WeakerAccess")
public final class MultiProjectMenuModel{
    //CHANGE: Changed to final to prevent inheritance
    private Path chosenProjectsPath;
    @Inject
    private PathFilter.PathFilterBuilder filter;

    /**
     * @return Chosen project path
     */
    public Path getChosenProjectsPath() {
        return chosenProjectsPath;
    }

    /**
     * @param chosenProjectsPath Chosen project path
     */
    public void setChosenProjectsPath(Path chosenProjectsPath) {
        this.chosenProjectsPath = chosenProjectsPath;
    }


    /**
     * @return Filter used by multi-project menu
     */
    PathFilter.PathFilterBuilder getFilterBuilder() {
        return filter;
    }

    /**
     * @return The filter built from the filter builder.
     */
    public PathFilter createFilter(){
        return filter.createFilter();
    }
}
