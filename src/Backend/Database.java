package Backend;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Database {
    public void appendToFile(String message, String fileName) throws IOException {
        FileWriter writer = new FileWriter("Logs/"+fileName+".txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(message);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }

}
