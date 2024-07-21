// Advent of code Day 1 solution

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Day1 {
    public static void main(String[] args) throws IOException {
        int sumOfCalibrationValues = 0;

        File file = new File(FilePaths.DAY1_FILEPATH);
        BufferedReader br = new BufferedReader(new FileReader(file));

        // Using Regex is an option, but i want my own dictionary
        HashMap<String,Integer> dictionary = new HashMap<>();
        dictionary.put("0",0); dictionary.put("zero",0);
        dictionary.put("1",1); dictionary.put("one",1);
        dictionary.put("2",2); dictionary.put("two",2);
        dictionary.put("3",3); dictionary.put("three",3);
        dictionary.put("4",4); dictionary.put("four",4);
        dictionary.put("5",5); dictionary.put("five",5);
        dictionary.put("6",6); dictionary.put("six",6);
        dictionary.put("7",7); dictionary.put("seven",7);
        dictionary.put("8",8); dictionary.put("eight",8);
        dictionary.put("9",9); dictionary.put("nine",9);

        String currLine;

        while ((currLine = br.readLine()) != null) {
            char[] currLineChars = currLine.toCharArray();

            ArrayList<Integer> integersInCurrLine = getIntegersInCurrLine(currLineChars,dictionary);

            int valAtTens = 0;
            int valAtUnits = 0;

            int size = integersInCurrLine.size();

            // normal case versus when only one number is seen. if zero, values remain zero
            if (size > 1) {
                valAtTens = integersInCurrLine.get(0);
                valAtUnits = integersInCurrLine.get(size-1);
            } else if (size == 1) {
                valAtUnits = integersInCurrLine.get(0);;
                valAtTens = integersInCurrLine.get(0);;
            }

            sumOfCalibrationValues += (valAtTens * 10) + valAtUnits;
        }

        System.out.println(sumOfCalibrationValues);
    }

    private static ArrayList<Integer> getIntegersInCurrLine(char[] currLineChars, HashMap<String, Integer> dictionary) {
        ArrayList<Integer> result = new ArrayList<>();

        // the max length of a word in our dictionary is 5
        for (int i=0; i<currLineChars.length; i++){
            StringBuilder sb = new StringBuilder();
            int j = i;
            while (j<currLineChars.length && j<i+5){
                sb.append(currLineChars[j]);
                String numberString = sb.toString();
                if (dictionary.containsKey(numberString)) {
                    result.add(dictionary.get(numberString));
                    break;
                }
                j++;
            }
        }

        return result;
    }
}
