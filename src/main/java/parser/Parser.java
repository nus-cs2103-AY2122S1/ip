package main.java.parser;

public class Parser {

    /**
     * Method to split a string into an array, removing spaces.
     * @param input string to be segmented.
     * @return String array with no spaces.
     */
    public static String[] sanitizeInput(String input) {
        String[] inputArr = input.split(" ");
        return inputArr;
    }
}
