package moss.project;

import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.io.Reader;


public class ProjectFlatReaderDistributorTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: distribute() 
* @throws Exception All thrown exceptions
*/ 
@Test
public void testDistribute() throws Exception { 
//TODO: Test goes here...
    ProjectFlatReaderDistributor distributor = new ProjectFlatReaderDistributor(TestObjects.TEST_PROJECT_1_PATH,
            PathFilter.TXT_FILTER);
    Reader reader = distributor.distribute();
    assert(reader.read() == 'c');
} 


} 
