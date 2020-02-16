package moss.projectpairmachine;

import moss.algorithm.TokenHashingStrategy;
import moss.project.*;
import org.junit.Test;


public class SimpleMultiProjectComparisonTest {

    @Test
    public void compareAgainstAll() {

    }


    @Test
    public void module0SubmissionsTest(){
        MultiProjectStorage storage =
                MultiProjectStorage.projectsIn(TestObjects.SUBMISSIONS_PATH, PathFilter.CPP_AND_JAVA_FILTER);

        SimpleMultiProjectComparison simpleMultiProjectComparison = new SimpleMultiProjectComparison(new TokenHashingStrategy());
        ProjectsCorrelationMatrix matrix = simpleMultiProjectComparison.compareAll(storage);
        for (ProjectsCorrelationMatrix.ResultRow row : matrix){
            System.out.print(row.getProject().getName());
            for (ProjectsCorrelationMatrix.ResultSet.ResultRecord score : row.getResults()){
                System.out.printf("\t%.4f", score.getScore());
            }
            System.out.println();
        }
    }
}