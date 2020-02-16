package Backend;

import java.util.ArrayList;

public class Matrix {
    private ArrayList<ArrayList<Float>> matrix = new ArrayList<>();
    private ArrayList<Float> arrayTemp;

    public void newMatrix()
    {
        matrix= new ArrayList<>();
    }

    public ArrayList<ArrayList<Float>> getMatrix() {
        return matrix;
    }

    public void setNewArray() {
        arrayTemp= new ArrayList<>();
    }

    public void addArray(float value) {
        arrayTemp.add(value);
    }

    public void setMatrix() {
        matrix.add(arrayTemp);
    }

    public int arraySize(){
        return arrayTemp.size();
    }

    public int matrixSize(){
        return matrix.size();
    }

}
