// Advent of code Day 2 solution

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Day3 {
    public static void main(String[] args) throws IOException {
        int sumOfPartNumbers = 0;
        long sumOfGearRatios = 0;

        File file = new File(FilePaths.DAY3_FILEPATH);
        BufferedReader br = new BufferedReader(new FileReader(file));

        // create parallel arrays
        ArrayList<ArrayList<Character>> numbers = new ArrayList<>();
        ArrayList<ArrayList<Character>> symbols = new ArrayList<>();

        String currLine;

        // populate the parallel arrays
        while ((currLine = br.readLine()) != null) {
            char[] arrayOfCurrLine = currLine.toCharArray();
            ArrayList<Character> numberList = new ArrayList<>();
            ArrayList<Character> symbolList = new ArrayList<>();

            for (int i=0; i<arrayOfCurrLine.length; i++) {
                if(arrayOfCurrLine[i] >= 48 && arrayOfCurrLine[i] <= 57) {
                    numberList.add(arrayOfCurrLine[i]);
                    symbolList.add('.');
                }else {
                    symbolList.add(arrayOfCurrLine[i]);
                    numberList.add(null);
                }
            }

            numbers.add(numberList);
            symbols.add(symbolList);
        }

        // part one
        for (int i=0; i<numbers.size(); i++) {
            int sizePerIndex = numbers.get(i).size();
            for (int j=0; j<sizePerIndex; j++) {
                boolean isPartNumber = false;
                int partNumber;
                String partNumberAsString = "";

                if (numbers.get(i).get(j) != null) {
                    StringBuilder sb = new StringBuilder();

                    while(j < sizePerIndex && numbers.get(i).get(j) != null) {
                        sb.append(numbers.get(i).get(j));

                        // check top
                        if (i>0 && j>0 && symbols.get(i-1).get(j-1) != '.') isPartNumber = true;
                        // check middle (our current line)
                        if (j>0 && symbols.get(i).get(j-1) != '.') isPartNumber = true;
                        // check bottom
                        if (i<numbers.size()-1 && j>0 && symbols.get(i+1).get(j-1) != '.') isPartNumber = true;

                        j++;
                    }

                    if (j < sizePerIndex) {
                        if (i>0 && (symbols.get(i-1).get(j) != '.' || symbols.get(i-1).get(j-1) != '.')) isPartNumber = true;
                        if (j>0 && symbols.get(i).get(j) != '.') isPartNumber = true;
                        if (i<numbers.size()-1 && (symbols.get(i+1).get(j) != '.' || symbols.get(i+1).get(j-1) != '.')) isPartNumber = true;
                    }

                    partNumberAsString = sb.toString();
                }

                if (isPartNumber) {
                    partNumber = Integer.parseInt(partNumberAsString);
                    sumOfPartNumbers += partNumber;
                }
            }
        }

        // part two
        HashMap<String, ArrayList<Integer>> possibleGear = new HashMap<>();
        for (int i=0; i<numbers.size(); i++) {
            int sizePerIndex = numbers.get(i).size();
            for (int j=0; j<sizePerIndex; j++) {
                String gearAxis = "";
                int partNumber;
                String partNumberAsString = "";

                if (numbers.get(i).get(j) != null) {
                    StringBuilder sb = new StringBuilder();

                    while (j < sizePerIndex && numbers.get(i).get(j) != null) {
                        sb.append(numbers.get(i).get(j));

                        // check top
                        if (i>0 && j>0 && symbols.get(i-1).get(j-1) == '*') gearAxis = (i-1) + "-" + (j-1);
                        // check middle (our current line)
                        if (j>0 && symbols.get(i).get(j-1) == '*') gearAxis = i + "-" + (j-1);
                        // check bottom
                        if (i<numbers.size()-1 && j>0 && symbols.get(i+1).get(j-1) == '*') gearAxis = (i+1) + "-" + (j-1);

                        j++;
                    }

                    // things got messy here. got too lazy changing traversal technique
                    if (j < sizePerIndex) {
                        if (i>0 && symbols.get(i-1).get(j) == '*' ) gearAxis = (i-1) + "-" + j;
                        if (i>0 && symbols.get(i-1).get(j-1) == '*') gearAxis = (i-1) + "-" + (j-1);
                        if (j>0 && symbols.get(i).get(j) == '*') gearAxis = i + "-" + j;
                        if (i<numbers.size()-1 && symbols.get(i+1).get(j) == '*') gearAxis = (i+1) + "-" + j;
                        if (i<numbers.size()-1 && symbols.get(i+1).get(j-1) == '*') gearAxis = (i+1) + "-" + (j-1);
                    }

                    partNumberAsString = sb.toString();
                }

                if (!gearAxis.isEmpty()) {
                    partNumber = Integer.parseInt(partNumberAsString);
                    possibleGear.computeIfAbsent(gearAxis, k -> new ArrayList<>());
                    possibleGear.get(gearAxis).add(partNumber);
                }
            }
        }

        // iterate to add gear ratio to the final sum
        for (String gearAxis : possibleGear.keySet()) {
            if (possibleGear.get(gearAxis).size() > 1) {
                ArrayList<Integer> gearValues = possibleGear.get(gearAxis);
                int gearRatio = gearValues.get(0);
                for (int i=1; i<gearValues.size(); i++) {
                    gearRatio *= gearValues.get(i);
                }
                sumOfGearRatios += gearRatio;
            }
        }

        System.out.println(sumOfPartNumbers);
        System.out.println(sumOfGearRatios);
    }
}