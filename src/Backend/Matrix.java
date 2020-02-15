package Backend;

import java.util.ArrayList;

public class Matrix {
    private ArrayList<ArrayList<Float>> matrix = new ArrayList<>();
    private ArrayList<Float> arrayTemp;
    private ArrayList<String> userFilesArray = new ArrayList<>();

    //FOR USER FILE NAMES ARRAY
    public void newUsers()
    {
        userFilesArray= new ArrayList<>();
    }

    public ArrayList<String> getUserFileNames() {
        return userFilesArray;
    }

    public void addUser(String username) {
        userFilesArray.add(username);
    }

    public int userFilesSize(){
        return userFilesArray.size();
    }


    //FOR MASTER MATRIX
    public void newMatrix()
    {
        matrix= new ArrayList<>();
    }

    public ArrayList<ArrayList<Float>> getMatrix() {
        return matrix;
    }

    public void setMatrix() {
        matrix.add(arrayTemp);
    }

    public int matrixSize(){
        return matrix.size();
    }


    //FOR TEMP (INNER) ARRAY
    public void setNewArray() {
        arrayTemp= new ArrayList<>();
    }

    public void addArray(float value) {
        arrayTemp.add(value);
    }

    public int arraySize(){
        return arrayTemp.size();
    }


}
