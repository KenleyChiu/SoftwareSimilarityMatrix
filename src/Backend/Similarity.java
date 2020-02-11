package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Similarity {
    private float  percentage = 0;
    private Scanner prog1Scan, prog2Scan;
    private File filename1, filename2;
    private Matrix data = new Matrix();
    private ArrayList<String> arrayA,arrayB;


    public void ReadCodeLine() {
        float sameLines=0,totalLines=0,shorterLimit=0,longestLength=0;
        String longestString = "";

        arrayA = new ArrayList<>();
        arrayB = new ArrayList<>();

        while(prog1Scan.hasNextLine() || prog2Scan.hasNextLine()) {

            if(!prog1Scan.hasNextLine() && prog2Scan.hasNextLine()) {
                String line2 = prog2Scan.nextLine();

                if(!line2.trim().isEmpty()) {
                    arrayB.add(line2);
                    totalLines++;
                }
            }
            else if (prog1Scan.hasNextLine() && !prog2Scan.hasNextLine()) {
                String line1 = prog1Scan.nextLine();

                if(!line1.trim().isEmpty()) {
                    arrayA.add(line1);
                    totalLines++;
                }
            }
            else {
                String line1 = prog1Scan.nextLine();
                String line2 = prog2Scan.nextLine();

                if(!line1.trim().isEmpty()) arrayA.add(line1);
                if(!line2.trim().isEmpty()) arrayB.add(line2);
                //if(!line1.trim().isEmpty() && !line2.trim().isEmpty()) totalLines++;
            }
        }

        if(arrayA.size() > arrayB.size()) totalLines = arrayA.size();
        else totalLines = arrayB.size();

//        totalLines = arrayA.size() + arrayB.size();

        System.out.println("\nNUMBER OF PROG1 LINES: " + arrayA.size());
        System.out.println("NUMBER OF PROG2 LINES: " + arrayB.size() + "\n");


        for(int x=0;x<arrayA.size();x++){  //arrayA.size()
//            System.out.println("PROG 1 LINE: "+arrayA.get(x));
            boolean compared = false;
            for(int y=0;y<arrayB.size();y++){  //arrayB.size()
//                System.out.println("PROG 2 LINE: "+arrayB.get(y));
                if(arrayA.get(x).equals(arrayB.get(y))){
                    if(!compared) {
                        System.out.println("PROG 1 LINE #"+(x+1)+": "+arrayA.get(x));
                        System.out.println("PROG 2 LINE #"+(y+1)+": "+arrayB.get(y));
                        sameLines++;
                        System.out.println(sameLines);
                    }
                    compared = true;
                }
            }
        }

        System.out.println("\nNUMBER OF SAME LINES: " + sameLines);
        System.out.println("NUMBER OF TOTAL LINES: " + totalLines + "\n");
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

    public void readFile(String comparison) throws FileNotFoundException {

        File prog1File = new File("Codes");
        File prog2File = new File("Codes");
        File[] file1 = prog1File.listFiles();
        File[] file2 = prog2File.listFiles();

        data.newMatrix();

        for(int i=0; i<file1.length; i++) //file1.length
        {
            data.setNewArray();

            this.filename1=file1[i];
            for(int j=0; j<file2.length; j++) //file2.length
            {
                this.filename2=file2[j];
                prog1Scan = new Scanner(filename1);
                prog2Scan = new Scanner(filename2);
                if(comparison.equals("line")) ReadCodeLine();
                else ReadCodeCharacter();

                //form.addArray(percentage);  //raw percentage
                data.addArray((float)(Math.round(percentage*100.0)/100.0));  //two decimal points
            }
            data.setMatrix();
        }

    }

    public Matrix getMatrix(){
        return data;
    }


}
