package moss.project;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@SuppressWarnings("WeakerAccess")
public class TestObjects {
    static public final Path TEST_PROJECT_1_PATH = Paths.get(new File("testfiles/project1").getAbsolutePath());
    static public final Path TEST_PROJECT_2_PATH = Paths.get(new File("testfiles/project2").getAbsolutePath());
    static public final Path SUBMISSIONS_PATH = Paths.get(new File("test_submissions").getAbsolutePath());
    static public final int SUBMISSION_PATH_FOLDER_COUNT = 36; //TODO: automate number

}
