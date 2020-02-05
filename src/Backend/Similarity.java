package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Similarity {
    private float  percentage = 0;
    private Scanner prog1Scan, prog2Scan;
    private File filename1, filename2;
    private Matrix line = new Matrix();
    private Matrix character = new Matrix();

    public void ReadCodeLine() {
        float sameLines=0,prog1Lines=0,prog2Lines=0,totalLines=0,longestLength=0;
        String longestString = "";

        while(prog1Scan.hasNextLine() || prog2Scan.hasNextLine()){

            if(!prog1Scan.hasNextLine() && prog2Scan.hasNextLine()){
                prog2Lines++;

                String prog2_line = prog2Scan.nextLine();
                //System.out.println("PROG 2 LINE: " + prog2_line);

            } else if(!prog2Scan.hasNextLine() && prog1Scan.hasNextLine()){
                prog1Lines++;

                String prog1_line = prog1Scan.nextLine();
                //System.out.println("PROG 1 LINE: "+prog1_line);

            } else {

                String prog1_line = prog1Scan.nextLine();
                //System.out.println("PROG 1 LINE: "+prog1_line);

                String prog2_line = prog2Scan.nextLine();
                //System.out.println("PROG 2 LINE: " + prog2_line);

                if(prog1_line.equals(prog2_line)){
                    if(prog1_line.length() > longestLength){
                        longestString = prog1_line;
                        longestLength = longestString.length();
                    }
                    sameLines++;
                }

            }
            totalLines++;
        }
        //System.out.println("SAME: " + sameLines);
        //System.out.println("TOTAL: " + totalLines);
        //System.out.println("\nLongest Similar Line: \n" + longestString);

        //System.out.println("same: " + sameLines + " and total: " + totalLines);
        percentage = (sameLines / totalLines);
        //percentage = (float)((sameLines/totalLines)-0.5) * 2; //testing for negatives
    }


    public void ReadCodeCharacter() {
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
        percentage = ((float) countChar / (float) countTotal);
        //percentage = (float)((countChar/countTotal)-0.5) * 2; //testing for negatives

    }

    public void readFile(String comparison,Boolean status) throws FileNotFoundException {

        File prog1File = new File("Codes");
        File prog2File = new File("Codes");
        File[] file1 = prog1File.listFiles();
        File[] file2 = prog2File.listFiles();
        for(int i=0; i<file1.length; i++) //file1.length
        {
            if(status) line.setNewArray();
            else character.setNewArray();

            this.filename1=file1[i];
            for(int j=0; j<file2.length; j++) //file2.length
            {
                this.filename2=file2[j];
                prog1Scan = new Scanner(filename1);
                prog2Scan = new Scanner(filename2);
                if(comparison.equals("line")) ReadCodeLine();
                else ReadCodeCharacter();

                //form.addArray(percentage);  //raw percentage
                if(status) line.addArray((float)(Math.round(percentage*100.0)/100.0));  //two decimal points
                else character.addArray((float)(Math.round(percentage*100.0)/100.0));  //two decimal points

            }
            if(status) line.setMatrix();
            else character.setMatrix();
        }

    }

    public Matrix getMatrix1(){
        return line;
    }

    public Matrix getMatrix2(){
        return character;
    }
}
