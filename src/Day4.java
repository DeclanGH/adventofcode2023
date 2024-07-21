// Advent of code Day 4 solution

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class Day4 {
    public static void main(String[] args) throws IOException {
        int totalPoint = 0;
        int totalScratchcards = 0;

        File file = new File(FilePaths.DAY4_FILEPATH);
        BufferedReader br = new BufferedReader(new FileReader(file));

        int cardCount = 220; // I'll just hardcode this because I know
        int[] scratchcardCount = new int[cardCount+1];
        int cardNumber = 1;

        String currLine;

        while ((currLine = br.readLine()) != null) {
            String stringAfterCardID = currLine.substring(currLine.indexOf(':')+1);
            String[] cardData = stringAfterCardID.split(Constants.BLANK_SPACE_REGEX);

            // part one
            HashSet<String> cardValues = new HashSet<>();
            int points = 0;
            boolean isFirst = true;

            for (int i=1; i<cardData.length; i++) {
                if (!cardValues.add(cardData[i])) {
                    if (isFirst) {
                        points = 1;
                        isFirst = false;
                    } else points += points;
                }
            }
            totalPoint += points;

            // part two
            scratchcardCount[cardNumber] += 1; // to mark original
            int nextWinningCopy = cardNumber;
            cardValues = new HashSet<>();
            for (int j=1; j<cardData.length; j++){
                // you might have to read the line to understand. It's difficult to explain in a comment
                if (!cardValues.add(cardData[j])) scratchcardCount[++nextWinningCopy] += scratchcardCount[cardNumber];
            }
            cardNumber++;
        }

        // sum of the numbers in the scratchcardCount array should be out total sum
        totalScratchcards = Arrays.stream(scratchcardCount).sum();

        System.out.println(totalPoint);
        System.out.println(totalScratchcards);
    }
}