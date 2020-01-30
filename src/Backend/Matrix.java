package Backend;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Matrix {
    ArrayList<ArrayList<Float>> matrix=new ArrayList<>();
    public void setMatrix(int i, float value)
    {
        matrix.get(i).add(value);
    }
    public void getMatrix(){

    }
}
