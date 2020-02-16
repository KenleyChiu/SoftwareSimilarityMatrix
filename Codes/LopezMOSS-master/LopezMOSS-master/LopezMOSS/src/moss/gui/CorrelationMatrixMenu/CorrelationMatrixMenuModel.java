package moss.gui.CorrelationMatrixMenu;

import moss.projectpairmachine.ProjectsCorrelationMatrix;


/**
 * Data passed unto the correlation matrix menu
 */
public class CorrelationMatrixMenuModel {
    private ProjectsCorrelationMatrix correlationMatrix;

    /**
     * @param matrix Matrix to be loaded into the correlation matrix menu
     */
    public void loadMatrix(ProjectsCorrelationMatrix matrix){
        this.correlationMatrix = matrix;
    }

    /**
     * @return Gets the matrix to be loaded into the menu
     */
    final ProjectsCorrelationMatrix getMatrix(){
        //NOTE: This is package-private because the matrix will only be used for display and should not be tampered with from other locations
        return correlationMatrix;
    }


}
