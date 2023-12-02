package aoc2023.day2;

import util.TextReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2 {

    private static final Pattern GAME_ID_PATTERN = Pattern.compile("^Game \\d*: ");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d*");
    private static final Pattern CHAR_PATTERN = Pattern.compile("\\D*");
    private static final Map<Integer, Map<String, Integer>> games = new HashMap<>();
    private static final Map<String, Integer> max_amounts = Map.of("red", 12, "green", 13, "blue", 14);

    public static void main(String[] args) {
        List<String> input = TextReader.readInput("input/2023/day2.txt");
        day2(input);
    }

    private static void day2(List<String> input) {
        int result = 0;
        int powerResult = 0;
        Map<String, Integer> hands;
        for (String s : input) {
            int gameId = getGameId(s);
            hands = extractHands(s);
            games.put(gameId, hands);

            boolean gamePossible = true;
            for (Map.Entry<String, Integer> pulls : hands.entrySet()) {
                if (pulls.getValue() > max_amounts.get(pulls.getKey())) {
                    gamePossible = false;
                }
            }
            if (gamePossible) {
                System.out.println("Game " + gameId + " is possible");
                System.out.println(s);
                result += gameId;
            }

            int power = 1;
            for (Map.Entry<String, Integer> pulls : hands.entrySet()) {
                System.out.println("Multiplying " + power + " with " + pulls.getValue());
                power *= pulls.getValue();
            }
            powerResult+=power;
        }
        System.out.println("part1: " + result);
        System.out.println("part2: " + powerResult);
    }

    private static int getGameId(String s) {
        String gameIdString = removeGameIdFromString(s);
        return Integer.valueOf(gameIdString.replace("Game ", "").replace(": ", ""));
    }

    private static Map<String, Integer> extractHands(String s) {
        s = s.replace(removeGameIdFromString(s), "");
        Map<String, Integer> game = new HashMap<>();

        String[] pulls = s.split(";");
        for (String pull : pulls) {
            String[] stones = pull.split(", ");
            for (String stone : stones) {
                int amount = extractAmount(stone);
                String color = extractColor(stone);

                int max = game.getOrDefault(color, 0);

                if (amount > max) {
                    game.put(color, amount);
                }
            }
        }
        return game;
    }

    private static int extractAmount(String s) {
        Matcher matcher = NUMBER_PATTERN.matcher(s.replace(" ", ""));
        matcher.find();
        return Integer.valueOf(matcher.group());
    }

    private static String extractColor(String s) {
        if (s.contains("green")) {
            return "green";
        } else if (s.contains("red")) {
            return "red";
        } else if (s.contains("blue")) {
            return "blue";
        }
        throw new RuntimeException("Help");
    }

    private static String removeGameIdFromString(String s) {
        return GAME_ID_PATTERN.matcher(s)
                .results()
                .findFirst()
                .map(MatchResult::group)
                .get();
    }
}
