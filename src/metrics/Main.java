package metrics;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner user_input = new Scanner( System.in );
        System.out.print("Enter Directory Name: ");
        String pathDirectory = user_input.next( );
        user_input.close();

        HalsteadMetrics hal = MetricMachine.getMetrics(pathDirectory);
        System.out.println("Overall Distinct Operators in the directory= "+ hal.getDistOperators());
        System.out.println("Overall Distinct Operands in the directory= "+ hal.getDistOperands());
        System.out.println("Overall Total Operators in the directory= "+ hal.getTotOperators());
        System.out.println("Overall Total Operands in the directory= "+ hal.getTotOperands());
        System.out.println("\n");
        System.out.println("###### Halstead Complexity Metrics ######");
        System.out.println("Vocabulary= "+ hal.getVocabulary());
        System.out.println("Program Length= "+ hal.getProglen());
        System.out.println("Calculated Program Length= "+ hal.getCalcProgLen());
        System.out.println("Volume= "+ hal.getVolume());
        System.out.println("Difficulty= "+ hal.getDifficulty());
        System.out.println("Effort= "+ hal.getEffort());
        System.out.println("Time Required to Program= "+ hal.getTimeReqProg() + " seconds");
        System.out.println("Number of delivered bugs= "+ hal.getTimeDelBugs());

    }
}
