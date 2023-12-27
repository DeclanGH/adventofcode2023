// Advent of code Day 2 solution

import java.io.*;
import java.util.ArrayList;

public class Day3 {
    public static void main(String[] args) throws IOException {
        int sumOfPartNumbers = 0;

        File file = new File("/Users/declan/IdeaProjects/adventofcode2023/src/puzzle texts/engine schematic");
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

        System.out.println(sumOfPartNumbers);
    }
}