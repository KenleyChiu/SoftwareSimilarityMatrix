package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Similarity {
    private float  percentage = 0;
    private Scanner prog1Scan, prog2Scan;
    private File filename1, filename2;
    private Matrix form= new Matrix();
    private StringBuilder wholeMatrix = new StringBuilder();

    public void ReadCodeLine() {
        float sameLines = 0, totalLines = 0, lineLength = 0;
        StringBuilder prog1String = new StringBuilder();
        StringBuilder prog2String = new StringBuilder();

        while (prog1Scan.hasNextLine() || prog2Scan.hasNextLine()) {
            String prog1Word = prog1Scan.nextLine();
            String prog2Word = prog2Scan.nextLine();

            prog1String.append(prog1Word + "\n");
            prog2String.append(prog2Word + "\n");

            if (prog1Word.equals(prog2Word)) {
                /*int newLineLength = prog1Word.length();

                if (newLineLength > lineLength) {
                    lineLength = newLineLength;
                }*/
                sameLines++;
            }
            totalLines++;
            if (!prog1Scan.hasNextLine() || !prog2Scan.hasNextLine()) break;
        }
        //System.out.println("same: " + sameLines + " and total: " + totalLines);
        percentage = (sameLines / totalLines);
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
    }

    public void readFile(String comparison) throws FileNotFoundException {

        File prog1File = new File("Codes");
        File prog2File = new File("Codes");
        File[] file1 = prog1File.listFiles();
        File[] file2 = prog2File.listFiles();
        for(int i=0; i<file1.length; i++)
        {
            form.setNewArray();
            this.filename1=file1[i];
            for(int j=0; j<file2.length; j++)
            {
                this.filename2=file2[j];
                prog1Scan = new Scanner(filename1);
                prog2Scan = new Scanner(filename2);
                if(comparison.equals("line")) ReadCodeLine();
                else ReadCodeCharacter();

                //form.addArray(percentage);  //raw percentage
                form.addArray((float)(Math.round(percentage*100.0)/100.0));  //two decimal points

            }
            form.setMatrix();
        }

        for(int x=0;x<form.arraySize();x++){
            //System.out.print(form.getMatrix().get(0).get(x) + " ");  //to see first row values
            for(int y=0;y<form.matrixSize();y++){
                wholeMatrix.append(form.getMatrix().get(y).get(x)).append("  ");
                //System.out.print(form.getMatrix().get(y).get(x) + "   ");
            }
            wholeMatrix.append("\n");
            //System.out.println();
        }

        //System.out.println(wholeMatrix);
    }

    public StringBuilder getSB(){
        return wholeMatrix;
    }

//    public void clearSb(){
//        wholeMatrix.setLength(0);
//        System.out.println(wholeMatrix);
//    }

}
