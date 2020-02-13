package Backend;

import java.io.File;
import java.io.IOException;

public class MainOperations {


    public static void main(String[] args) throws IOException {

        SystemMetrics metrics = new SystemMetrics();

        File masterFile = new File("Codes");  //for our files [Codes or src only]
        metrics.createSystemMetricsTable(masterFile);  //check this for other's files


    }


}

