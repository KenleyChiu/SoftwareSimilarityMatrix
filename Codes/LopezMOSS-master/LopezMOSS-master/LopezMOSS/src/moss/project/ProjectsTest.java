package moss.project;

import moss.algorithm.TokenHashingStrategy;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 


public class ProjectsTest {

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: compare(Project first, Project second, ComparisonStrategy strategy)
 * @throws Exception All caught exceptions
* 
*/ 
@Test
public void testCompare() throws Exception { 
//TODO: Test goes here...
    ProjectBuilder projectBuilder = new ProjectBuilder();
    Project project1 = projectBuilder.setName("Test").setPath(TestObjects.TEST_PROJECT_1_PATH).createProject();
    Project project2 = projectBuilder.setName("Test_2").setPath(TestObjects.TEST_PROJECT_2_PATH).createProject();
    double comparison = Projects.compare(project1, project2, new TokenHashingStrategy());
    assert(comparison >= 0.0 && comparison <= 1.0);
    System.out.println("Test result:" + comparison);
} 


} 
