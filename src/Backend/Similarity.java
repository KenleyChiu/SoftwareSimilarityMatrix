package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Similarity {
    private float  percentage = 0;
    private Scanner prog1Scan, prog2Scan;
    private String filename1, filename2;

    public String ReadCodeLine(String comparison) {
        float sameLines = 0, totalLines = 0, lineLength = 0;
        StringBuilder prog1String = new StringBuilder();
        StringBuilder prog2String = new StringBuilder();


        while (prog1Scan.hasNextLine() || prog2Scan.hasNextLine()) {
            String prog1Word = prog1Scan.nextLine();
            String prog2Word = prog2Scan.nextLine();

            prog1String.append(prog1Word + "\n");
            prog2String.append(prog2Word + "\n");

            if (prog1Word.equals(prog2Word)) {
                int newLineLength = prog1Word.length();

                if (newLineLength > lineLength) {
                    lineLength = newLineLength;
                }
                sameLines++;
            }

            if (!prog1Scan.hasNextLine() || !prog2Scan.hasNextLine()) break;
            totalLines++;
        }
        percentage = (sameLines / totalLines) * 100;

        return filename1+" and "+filename2+" have a similarity rate of "+percentage+ "% when compared per " + comparison + ".";
    }

    public String ReadCodeCharacter(String comparison) {
        int countChar = 0;
        int countTotal = 0;
        while (true) {
            if (prog1Scan.hasNext() && prog2Scan.hasNext()) {
                String data1 = prog1Scan.nextLine();
                String data2 = prog2Scan.nextLine();
                int i = 0;
                while (true) {
                    if (i < data1.length() && i < data2.length()) {
                        if (data1.charAt(i) == data2.charAt(i)) {
                            countChar++;
                            countTotal++;
                        }
                        i++;
                    } else if (i < data2.length()) {
                        countTotal++;
                        i++;
                    } else if (i < data1.length()) {
                        countTotal++;
                        i++;
                    } else {
                        break;
                    }
                }

            } else if (prog1Scan.hasNext()) {
                countTotal = countTotal + prog1Scan.nextLine().length();
            } else if (prog2Scan.hasNext()) {
                countTotal = countTotal + prog2Scan.nextLine().length();
            } else {
                break;
            }

        }
        prog1Scan.close();
        prog2Scan.close();
        percentage = ((float) countChar / (float) countTotal) * (float) 100;
        return filename1+" and "+filename2+" have a similarity rate of "+percentage+ "% when compared per " + comparison + ".";


    }

    public void readFile(String filename1, String filename2) throws FileNotFoundException {
        File prog1File = new File("Codes/"+filename1);
        File prog2File = new File("Codes/"+filename2);
        //File prog1File = new File("D:\\Downloads\\College stuff\\Term 5\\LBYCP2D\\SoftwareSimilarityGui\\src\\sample\\" + filename1);
        //File prog2File = new File("D:\\Downloads\\College stuff\\Term 5\\LBYCP2D\\SoftwareSimilarityGui\\src\\sample\\" + filename2);
        this.filename1=filename1;
        this.filename2=filename2;
        prog1Scan = new Scanner(prog1File);
        prog2Scan = new Scanner(prog2File);
    }

}
