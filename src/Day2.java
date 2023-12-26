// Advent of code Day 2 solution

import java.io.*;

public class Day2 {
    public static void main(String[] args) throws IOException {
        int totalPossibleGames = 0;

        File file = new File("/Users/declan/IdeaProjects/adventofcode2023/src/puzzle texts/games and record");
        BufferedReader br = new BufferedReader(new FileReader(file));

        int gameNumber = 0;

        String currLine;

        while ((currLine = br.readLine()) != null) {
            gameNumber += 1;
            String stringAfterGameID = currLine.substring(currLine.indexOf(':')+2);
            String[] gameData = stringAfterGameID.split(" ");

            if (isPossibleGame(gameData)) totalPossibleGames += gameNumber;
        }

        System.out.println(totalPossibleGames);
    }

    private static boolean isPossibleGame(String[] gameData) {
        int cubeCount = 0;

        for (int i=0; i<gameData.length; i++) {

            if (i%2 == 0) { // indexes with cube count
                cubeCount = Integer.parseInt(gameData[i]);
            }else {
                char color = gameData[i].charAt(0); // first letter of the string (distinct per color)
                if ((color == 'r' && cubeCount > 12) || (color == 'g' && cubeCount > 13) || (color == 'b' && cubeCount > 14))
                    return false;
            }
        }

        return true;
    }
}
