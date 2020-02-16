package moss.gui.MultiProjectMenu;

import org.junit.Test;

import javax.inject.Inject;
import moss.project.TestObjects;

import static org.junit.Assert.*;

/**
 * Used to test the multi-project menu services
 */
public class MultiProjectMenuServiceTest {
    @Inject
    MultiProjectMenuService testService = new MultiProjectMenuService();

    /**
     * Format: int folderCount(Path folder)
     */
    @Test
    public void folderCount() {
        assert(testService.folderCount(TestObjects.SUBMISSIONS_PATH) == TestObjects.SUBMISSION_PATH_FOLDER_COUNT);
    }
}