package aoc2023.day1;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.TextReader;

public class Day1 {

    private static final Pattern REGEX_PATTERN = Pattern.compile("\\d");

    public static void main(String[] args) {
        List<String> input = TextReader.readInput("input/2023/day1.txt");
        countNumbers(input);
    }

    private static void countNumbers(List<String> input) {
        int sum = 0;
        for (String s : input) {
            sum += getNumberFromString(s);
        }
        System.out.println("Total: " + sum);
    }

    private static int getNumberFromString(String s) {
        String convertedString = convertToNumsString(s);
        Matcher matcher = REGEX_PATTERN.matcher(convertedString);
        StringBuilder sb = new StringBuilder();
        while(matcher.find()) {
            sb.append(matcher.group());
        }

        int result = Integer.valueOf(String.format("%s%s", sb.charAt(0), sb.charAt(sb.length() - 1)));
//        System.out.println("CS: " + convertedString + ": " + result);
        return result;
    }

    private static String convertToNumsString(String s) {
        String fullString = new String();
        for (char character : s.toLowerCase().toCharArray()) {
            fullString += character;
            fullString = mapNumbers(fullString);
        }

//        System.out.println(s + ": " + fullString);
        return fullString;
    }

    private static String mapNumbers(String s) {

        String converted = s.replace("one", "1ne")
                .replace("two", "2wo")
                .replace("three", "3hree")
                .replace("four", "4our")
                .replace("five", "5ive")
                .replace("six", "6ix")
                .replace("seven", "7even")
                .replace("eight", "8ight")
                .replace("nine", "9ine");
        System.out.println(s + ": "  + converted);
        return converted;
    }
}
