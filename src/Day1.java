// Advent of code Day 1 solution

import java.io.*;

public class Day1 {
    public static void main(String[] args) throws IOException {
        int sumOfCalibrationValues = 0;

        File file = new File("/Users/declan/IdeaProjects/adventofcode2023/src/calibration document");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String currLine;

        while ((currLine = br.readLine()) != null) {
            char[] currLineChars = currLine.toCharArray();

            // initialize at -1 to indicate "not seen"
            int valAtTens = -1;
            int valAtUnits = -1;

            for (int i=0; i<currLineChars.length; i++) {
                if (currLineChars[i] >= 48 && currLineChars[i] <= 57) {
                    if (valAtTens == -1){
                        valAtTens = Integer.parseInt(String.valueOf(currLineChars[i]));
                    } else {
                        valAtUnits = Integer.parseInt(String.valueOf(currLineChars[i]));
                    }
                }
            }

            if (valAtTens == -1 && valAtUnits == -1) {
                valAtTens = 0;
                valAtUnits = 0;
            } else if (valAtTens > -1 && valAtUnits == -1) {
                valAtUnits = valAtTens;
            }

            sumOfCalibrationValues += (valAtTens * 10) + valAtUnits;
        }

        System.out.println(sumOfCalibrationValues);
    }
}
