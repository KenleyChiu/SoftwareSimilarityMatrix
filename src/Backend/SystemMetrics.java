package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class SystemMetrics {
    private File[] folders;
    private int numOfFiles=0;
    private String[] filesArray = new String[35];
    private String[] operators = {".","( )","if","for","=","+","while","!",";","==","<=","<","float","int",">",
    "++","new","&&","||","/","{ }"};
    private String[] operands = {"x","y","i","j"};
    private int dots,par,ifs,fors,equal,plus,whiles,not,colon,equals,lessEqual,less,
            floats,ints,greater,pluses,news,ands,ors,fslash,curly;
    private int xs,ys,is,js;
    private int sum_operators,sum_operands,totalOps,count_operators,count_operands,totalCnt;
    //private int[] operandsCount = new int[10];

    public void getAllFiles(File directory) throws IOException {
        folders = directory.listFiles();
        for(File file : folders){  //folders.length
            if(file.isDirectory()){  //folders[x]
                System.out.println("\nFOLDER: "+file.getCanonicalPath());
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

    public void searchOperationsAndOperands() throws FileNotFoundException {
        String word = "";
        boolean isComment = false;

        for(int i=0; i<numOfFiles; i++) {  //numOfFiles [change numOfFiles to 1 for the first file only)
            File file = new File(filesArray[i]);
            Scanner fileScanner = new Scanner(file);
            //System.out.println(filesArray[i]);

            while(fileScanner.hasNext()){
                word = fileScanner.nextLine();
                System.out.println(word);

                for(int x=0;x<word.length();x++){
                    //OPERATORS
                    if(word.charAt(x) == '/'){
                        if(x==word.length()-1) continue;
                        if(word.charAt(x+1) == '/') isComment = true;
                        if(word.charAt(x+1) != '/'){
                            if(word.charAt(x-1) != '/' && !isComment){
                                System.out.println("SLASH: " + word.charAt(x));
                                fslash++;
                            }
                        }
                    } else {
                        if(!isComment) {
                            if (word.charAt(x) == '.') dots++;
                            if (word.charAt(x) == '(') par++;
                            if (word.charAt(x) == 'i') {
                                if (x == word.length() - 1) continue;
                                if (word.charAt(x + 1) == 'f') ifs++;
                            }
                            if (word.charAt(x) == 'f' && word.charAt(x + 1) == 'o' && word.charAt(x + 2) == 'r') {
                                if (x == word.length() - 1) continue;
                                fors++;
                            }
                            if (word.charAt(x) == '=') equal++;
                            if (word.charAt(x) == '+') plus++;
                            if (word.charAt(x) == 'w') {
                                if (x == word.length() - 1) continue;
                                if (word.charAt(x + 1) == 'h' && word.charAt(x + 2) == 'i' && word.charAt(x + 3) == 'l' && word.charAt(x + 4) == 'e')
                                    whiles++;
                            }
                            if (word.charAt(x) == '!') not++;
                            if (word.charAt(x) == ';') colon++;
                            if (word.charAt(x) == '=') {
                                if (x == word.length() - 1) continue;
                                if (word.charAt(x + 1) == '=') equals++;
                            }
                            if (word.charAt(x) == '<') {
                                if (x == word.length() - 1) continue;
                                if (word.charAt(x + 1) == '=') lessEqual++;
                            }
                            if (word.charAt(x) == '<') less++;
                            if (word.charAt(x) == 'f') {
                                if (x == word.length() - 1) continue;
                                if (word.charAt(x + 1) == 'l' && word.charAt(x + 2) == 'o' && word.charAt(x + 3) == 'a' && word.charAt(x + 4) == 't')
                                    floats++;
                            }
                            if (word.charAt(x) == 'i') {
                                if (x == word.length() - 1) continue;
                                if (x + 1 == word.length() - 1) continue;
                                if (word.charAt(x + 1) == 'n' && word.charAt(x + 2) == 't') ints++;
                            }
                            if (word.charAt(x) == '>') greater++;
                            if (word.charAt(x) == '+' && word.charAt(x + 1) == '+') pluses++;
                            if (word.charAt(x) == 'n') {
                                if (x == word.length() - 1) continue;
                                if (x + 1 == word.length() - 1) continue;
                                if (word.charAt(x + 1) == 'e' && word.charAt(x + 2) == 'w') news++;
                            }
                            if (word.charAt(x) == '&') {
                                if (x == word.length() - 1) continue;
                                if (word.charAt(x + 1) == '&') ands++;
                            }
                            if (word.charAt(x) == '|') {
                                if (x == word.length() - 1) continue;
                                if (word.charAt(x + 1) == '|') ors++;
                            }
//                    if(word.charAt(x) == '/') fslash++;
                            if (word.charAt(x) == '{') curly++;

                            //OPERATORS
                    /*if(word.charAt(x) == 'x') xs++;    //considers all x,y,i,j
                    if(word.charAt(x) == 'y') ys++;
                    if(word.charAt(x) == 'i') xs++;
                    if(word.charAt(x) == 'j') ys++;*/

                            //considers only x,y,i,j used in for loops or basta as variables lang
                            if (word.charAt(x) == 'x') {
                                if (x == word.length() - 1) continue;
                                if ((word.charAt(x + 1) == '<' || word.charAt(x + 1) == '=' || word.charAt(x + 1) == '+' || word.charAt(x + 1) == ')' || word.charAt(x + 1) == ']')) {
                                    xs++;
                                }
                            }
                            if (word.charAt(x) == 'y') {
                                if (x == word.length() - 1) continue;
                                if ((word.charAt(x + 1) == '<' || word.charAt(x + 1) == '=' || word.charAt(x + 1) == '+' || word.charAt(x + 1) == ')' || word.charAt(x + 1) == ']')) {
                                    ys++;
                                }
                            }
                            if (word.charAt(x) == 'i') {
                                if (x == word.length() - 1) continue;
                                if ((word.charAt(x + 1) == '<' || word.charAt(x + 1) == '=' || word.charAt(x + 1) == '+' || word.charAt(x + 1) == ')' || word.charAt(x + 1) == ']')) {
                                    is++;
                                }
                            }
                            if (word.charAt(x) == 'j') {
                                if (x == word.length() - 1) continue;
                                if ((word.charAt(x + 1) == '<' || word.charAt(x + 1) == '=' || word.charAt(x + 1) == '+' || word.charAt(x + 1) == ')' || word.charAt(x + 1) == ']')) {
                                    js++;
                                }
                            }
                        }
                    }
                }
                isComment = false;
            }
        }


        sum_operators = dots+par+ifs+fors+equal+plus+whiles+not+colon+equals+lessEqual+less+floats+ints+greater+pluses+news+ands+ors+fslash+curly;
        sum_operands = xs+ys+is+js;
        totalOps = sum_operators + sum_operands;
        count_operators = operators.length;
        count_operands = operands.length;
        totalCnt = count_operators + count_operands;
    }


    public void printMetrics(){

        System.out.print("\n-- OPERATORS --");              System.out.println("  -- OPERANDS --");
        System.out.print("\t" + operators[0] + " -> " + dots);     System.out.println("\t\t" + operands[0] + " -> " + xs);
        System.out.print("\t" + operators[1] + " -> " + par);      System.out.println("\t\t" + operands[1] + " -> " + ys);
        System.out.print("\t" + operators[2] + " -> " + ifs);      System.out.println("\t\t" + operands[2] + " -> " + is);
        System.out.print("\t" + operators[3] + " -> " + fors);     System.out.println("\t\t" + operands[3] + " -> " + js);
        System.out.println("\t" + operators[4] + " -> " + equal);
        System.out.println("\t" + operators[5] + " -> " + plus);
        System.out.println("\t" + operators[6] + " -> " + whiles);
        System.out.println("\t" + operators[7] + " -> " + not);
        System.out.println("\t" + operators[8] + " -> " + colon);
        System.out.println("\t" + operators[9] + " -> " + equals);
        System.out.println("\t" + operators[10] + " -> " + lessEqual);
        System.out.println("\t" + operators[11] + " -> " + less);
        System.out.println("\t" + operators[12] + " -> " + floats);
        System.out.println("\t" + operators[13] + " -> " + ints);
        System.out.println("\t" + operators[14] + " -> " + greater);
        System.out.println("\t" + operators[15] + " -> " + pluses);
        System.out.println("\t" + operators[16] + " -> " + news);
        System.out.println("\t" + operators[17] + " -> " + ands);
        System.out.println("\t" + operators[18] + " -> " + ors);
        System.out.println("\t" + operators[19] + " -> " + fslash);
        System.out.println("\t" + operators[20] + " -> " + curly);
        System.out.print("\tSUM -> " + sum_operators);       System.out.println("\t\tSUM -> " + sum_operands);


//        System.out.println("\n-- OPERANDS --");
//        System.out.println(operands[0] + " -> " + xs);
//        System.out.println(operands[1] + " -> " + ys);
//        System.out.println(operands[2] + " -> " + is);
//        System.out.println(operands[3] + " -> " + js);
//        System.out.println("SUM -> " + sum_operands);

        System.out.println("\nVolume -> " + volume());

    }

    public int volume(){
        return totalOps * (int)(Math.log(totalCnt)/Math.log(2));
    }



    public void createSystemMetricsTable(File directory) throws IOException {

        getAllFiles(directory);

        searchOperationsAndOperands();

        printMetrics();

    }

}
