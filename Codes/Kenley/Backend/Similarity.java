package Backend;

import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Similarity {
    private float  percentage = 0;
    private Scanner checkerScan, comparisonScan;
    private Matrix data = new Matrix();
    private ArrayList<String> arrayA,arrayB;

    private SystemMetrics metrics = new SystemMetrics();
    private ArrayList<File> listFiles= new ArrayList<>();


    public void ReadCodeLine() {
        float sameLines=0,totalLines=0;

        arrayA = new ArrayList<>();
        arrayB = new ArrayList<>();

        while(checkerScan.hasNextLine() || comparisonScan.hasNextLine()) {

            if(!checkerScan.hasNextLine() && comparisonScan.hasNextLine()) {
                String line2 = comparisonScan.nextLine();

                if(!line2.trim().isEmpty()) {
                    arrayB.add(line2);
                    totalLines++;
                }
            }
            else if (checkerScan.hasNextLine() && !comparisonScan.hasNextLine()) {
                String line1 = checkerScan.nextLine();

                if(!line1.trim().isEmpty()) {
                    arrayA.add(line1);
                    totalLines++;
                }
            }
            else {
                String line1 = checkerScan.nextLine();
                String line2 = comparisonScan.nextLine();

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
            if (checkerScan.hasNext() && comparisonScan.hasNext()) {
                String data1 = checkerScan.nextLine();
                String data2 = comparisonScan.nextLine();
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

            } else if (checkerScan.hasNext()) {
                countTotal = countTotal + checkerScan.nextLine().length();
            } else if (comparisonScan.hasNext()) {
                countTotal = countTotal + comparisonScan.nextLine().length();
            } else {
                break;
            }

        }
        checkerScan.close();
        comparisonScan.close();
        percentage = ((float) countChar / (float) countTotal);
        //percentage = (float)((countChar/countTotal)-0.5) * 2; //testing for negatives

    }

    //creating the correlation Matrix
    public void creationMatrix(String comparison) throws IOException {

        File checkerFile, comparisonFile; //storing for files when comparing
        File prog1File = new File("Codes");
        File[] dir = prog1File.listFiles(); // getting all file in the Codes directory
        data.newMatrix(); // creating a new correlation matrix
        getAllFiles(dir);

        //comparing files
        for(int i=0; i<listFiles.size(); i++)
        {
            data.setNewArray();// creating a new array for the checker file
            checkerFile=listFiles.get(i);
            for(int j=0; j<listFiles.size();j++)
            {
                comparisonFile=listFiles.get(j);
                checkerScan = new Scanner(checkerFile);
                comparisonScan = new Scanner(comparisonFile);
                //checks whether the user chose the to compare by line or by character
                if(comparison.equals("line")) ReadCodeLine();
                else ReadCodeString();
                data.addArray((float)(Math.round(percentage*100.0)/100.0));  //two decimal points
            }
            data.setMatrix();//saving the data gathered to another array list for the correlation matrix
        }

    }

    private void getAllFiles(File[] dir) throws IOException //get all files recursively
    {
        for(File files: dir)
        {
            if(files.isDirectory())// if the list found is a directory
            {
                File[] newDir=files.listFiles(); //gets the filename of the files inside the directory
                getFilesRecursively(newDir,files.getName());// going to the method that will check every file recursively
            }
        }
    }

    private void getFilesRecursively(File[] dir,String fileName) throws IOException {
        for(File files: dir)
        {
            if(files.isDirectory())// if the list found is a directory
            {
                File[] newDir=files.listFiles(); //gets the filename of the files inside the directory
                getFilesRecursively(newDir,fileName);// going to the method that will check every file recursively
            }
            else
            {
                if(files.getName().contains(".java") || files.getName().contains(".cpp"))
                {
                    FileWriter fstream = new FileWriter("MergedCodes/"+fileName+".txt",true);
                    BufferedWriter writing = new BufferedWriter(fstream);
                    Scanner input = new Scanner(files);
                    while(input.hasNext())
                    {
                        writing.write(input.nextLine());
                        writing.newLine();
                    }
                }


            }
        }
    }



    public Matrix getMatrix(){
        return data;
    }


}
