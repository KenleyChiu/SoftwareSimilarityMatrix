package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class SystemMetrics {
    private File[] folders;
    private int numOfFiles=0;
    private String[] filesArray = new String[8];

    public void getAllFiles(File directory) throws IOException {
        folders = directory.listFiles();
        for(File file : folders){  //folders.length
            if(file.isDirectory()){  //folders[x]
                System.out.println("FOLDER: "+file.getCanonicalPath());
                getAllFiles(file);
            } else {
                System.out.println("FILE: "+file.getCanonicalPath());
                if(file.getCanonicalPath().contains(".java") || file.getCanonicalPath().contains(".cpp")){
                    filesArray[numOfFiles++] = file.getCanonicalPath();
                }
            }
        }
        //System.out.println(numOfFiles);
    }

    public void searchDots() throws FileNotFoundException {
        String word = "";
        int dots=0;

        for(int i=0; i<numOfFiles; i++) {  //numOfFiles [change numOfFiles to 1 for the first file only)
            File file = new File(filesArray[i]);
            Scanner fileScanner = new Scanner(file);
            //System.out.println(filesArray[i]);

            while(fileScanner.hasNextLine()){
                word = fileScanner.nextLine();
                //System.out.println(word);

                for(int x=0;x<word.length();x++){
                    if(word.charAt(x) == '.'){
                        dots++;
                    }
                }

            }
        }
        System.out.println("NUMBER OF PERIODS: " + dots);
    }

}
