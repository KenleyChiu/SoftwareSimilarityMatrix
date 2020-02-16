package moss.projectpairmachine;

import moss.project.MultiProjectStorage;

public interface MultiProjectComparison {
    //CHANGE: Changed to public interface to allow other packages to hide the particular comparison system they are using.
    ProjectsCorrelationMatrix compareAll(MultiProjectStorage projects);

}
