package Backend;

import java.io.File;
import java.io.IOException;

public class Testrun {
    public static void main(String[] args) {
        File currentDir = new File("Codes"); // current directory
        displayDirectoryContents(currentDir);
    }

    public static void displayDirectoryContents(File folder) {
        try {
            File[] files = folder.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println("FOLDER:" + file.getCanonicalPath());
                    displayDirectoryContents(file);
                } else {
                    if(file.getCanonicalPath().contains(".java") || file.getCanonicalPath().contains(".cpp")) {
                        System.out.println("FILE:" + file.getCanonicalPath());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
