public class Parser {
    public static String getRemainingText(String firstWord, String input) {
        String remainingText = "";
        if (input.length() > firstWord.length() + 1) {
            remainingText = input.substring(firstWord.length() + 1).trim();
        }
        return remainingText;
    }
}
