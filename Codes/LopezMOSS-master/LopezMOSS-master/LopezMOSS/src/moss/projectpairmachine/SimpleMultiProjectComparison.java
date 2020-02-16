package moss.projectpairmachine;

import moss.algorithm.ComparisonStrategy;
import moss.project.*;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.stream.Stream;

/**
 * Compares multiple projects pair-wise
 */
public final class SimpleMultiProjectComparison implements MultiProjectComparison {
    //CHANGE: Changed to public so that other classes can make use of this
    /*
    TODO: Issues with the new refactor:
      1. With the new abstractions, the matrix is, for some reason, not entirely symmetrical
     */
    private final ComparisonStrategy strategy;

    /**
     * @param strategy Algorithm to be used for comparison
     * @return A comparison device for comparing projects with the given strategy
     */
    public static SimpleMultiProjectComparison fromStrategy(ComparisonStrategy strategy){
        //The static class will serve as the only way to make this class.
        //TODO: Since this class is stateless, consider using a hashtable to map strategies to already-built classes
        return new SimpleMultiProjectComparison(strategy);
    }

    /**
     * @param strategy Algorithm to be used for comparison
     */
    SimpleMultiProjectComparison(ComparisonStrategy strategy) {
        //CHANGE: This class will no longer have the ability to create projects; instead,
        //a new object dependency (MultiProjectStorage) will be used to contain all the projects
        //before it is sent here
        this.strategy = strategy;
    }


    /**

     */
    @Override
    public ProjectsCorrelationMatrix compareAll(MultiProjectStorage projects){
        //CHANGE: The result of this comparison has been refactored to a single less verbose class for correlation matrices
        //         This is to reduce the verbosity of the results' type
        ProjectsCorrelationMatrix.ProjectsCorrelationMatrixBuilder results =
                new ProjectsCorrelationMatrix.ProjectsCorrelationMatrixBuilder();
        Collection<Project> completedProjects = new ArrayList<>();
        for (Project firstProject : projects){
            for (Project secondProject : projects){
                if (completedProjects.contains(secondProject)) continue;
                results.addRecordIfNone(firstProject, secondProject,
                        Projects.compare(firstProject, secondProject, strategy));
            }
            completedProjects.add(firstProject);
        }

        return results.createMatrix();
    }
    /**
     * @param project Project to be compared against all stored projects
     * @param allProjects Other projects to be compared against <i>project</i>
     * @return A hashtable containing the MOSS of the passed project against all projects stored in this class
     */
    private Hashtable<Project, Double> compareAgainstAll(Project project, MultiProjectStorage allProjects){
        Hashtable<Project, Double> scores = new Hashtable<>();
        for (Project comparedProject : allProjects){
            //Projects.compare (a static class) is called here to get the score of the comparison against the passed project
            scores.putIfAbsent(comparedProject,
                    Projects.compare(project, comparedProject, strategy));
        }
        return scores;
    }

//    public Hashtable<Path, Double> compareAgainstAll(Path path){
//        for (Map.Entry<Path, ProjectFlatReaderDistributor> distributorEntry : projects.entrySet()){
//
//        }
//    }


}
