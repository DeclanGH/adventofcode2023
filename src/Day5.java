import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Advent of Code Day 5 Solution
 */
public class Day5 {

    private static class DayFiveConstants {
        private static final String SEEDS = "seeds:";
        private static final String SEED_TO_SOIL_MAP = "seed-to-soil map:";
        private static final String SOIL_TO_FERTILIZER_MAP = "soil-to-fertilizer map:";
        private static final String FERTILIZER_TO_WATER_MAP = "fertilizer-to-water map:";
        private static final String WATER_TO_LIGHT_MAP = "water-to-light map:";
        private static final String LIGHT_TO_TEMPERATURE_MAP = "light-to-temperature map:";
        private static final String TEMPERATURE_TO_HUMIDITY_MAP = "temperature-to-humidity map:";
        private static final String HUMIDITY_TO_LOCATION_MAP = "humidity-to-location map:";
    }

    public static void main(String[] args) throws IOException {

        Set<String> dayFiveConstantsSet = Utils.getStringConstantsInClassAsSet(DayFiveConstants.class);

        File file = new File(FilePaths.DAY5_FILEPATH);
        BufferedReader br = new BufferedReader(new FileReader(file));


        // Read first line that contains the value of seeds
        ArrayList<Long> seedsList = new ArrayList<>();
        String[] firstLineAsArray = br.readLine().split(Constants.BLANK_SPACE_REGEX);
        for (String s : firstLineAsArray) {
            if (!dayFiveConstantsSet.contains(s)) {
                seedsList.add(Long.parseLong(s));
            }
        }

        String currLine;

        while ((currLine = br.readLine()) != null) {

            if (dayFiveConstantsSet.contains(currLine)) {
                updateSeedsListToMappedLocation(br, seedsList);
            }
        }

        long result = seedsList
                .stream()
                .sorted()
                .toList()
                .get(0);

        System.out.println("The lowest location number is " + result);
    }

    private static void updateSeedsListToMappedLocation(BufferedReader br, ArrayList<Long> seedsList) throws IOException {
        List<Long> sourceLocations = new ArrayList<>();
        LinkedHashMap<Long, Long> sourceToDestinationMap = new LinkedHashMap<>();
        Map<Long, Long> destinationToRangeMap = new HashMap<>();

        String currLine;

        while ((currLine = br.readLine()) != null && !currLine.isEmpty()){
            String[] currLineAsArray = currLine.split(Constants.BLANK_SPACE_REGEX);
            long destination = Long.parseLong(currLineAsArray[0]);
            long source = Long.parseLong(currLineAsArray[1]);
            long range = Long.parseLong(currLineAsArray[2]);

            sourceLocations.add(source);
            sourceToDestinationMap.put(source, destination);
            destinationToRangeMap.put(destination, range);
        }

        sourceLocations.sort(Collections.reverseOrder());

        for (int i=0; i<seedsList.size(); i++) {
            long seed = seedsList.get(i);
            for (long source : sourceLocations) {
                if (seed >= source) {
                    long difference = seed - source;
                    long destination = sourceToDestinationMap.get(source);
                    long range = destinationToRangeMap.get(destination);
                    if (difference <= range) { // checking if difference is within range
                        seedsList.set(i, (destination + difference));
                        break;
                    }
                }
            }
        }
    }
}
