public class Kermit {

    /**
     * Adds a top and bottom horizontal line to text
     * @param text Text to be formatted.
     * @return Formatted version of text.
     */
    public static String formatText(String text) {
        String horizontalDivider = "____________________________________________________________";
        return horizontalDivider + "\n" + text + "\n" + horizontalDivider;
    }
    public static void main(String[] args) {
        System.out.println(formatText("Hello from Kermit, eaten any flies today?\nWhat can I do for you?"));
    }
}
