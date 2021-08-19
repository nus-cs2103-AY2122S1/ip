import static java.lang.Math.max;

public class Message {
    private static final int BOX_LENGTH = 60;
    private static final int INDENT_LENGTH = 4;
    private static final String INDENT = " ".repeat(INDENT_LENGTH);

    // Message methods
    private static void horizontal_line() {
        System.out.print("_".repeat(BOX_LENGTH) + "\n");
    }

    public static void display_message(String message) {
        horizontal_line();
        String[] lineArr = message.split("\n");
        for (int i = 0; i < lineArr.length; i++) {
            int remainingSpace = max(BOX_LENGTH - lineArr[i].length() - INDENT_LENGTH - 2, 0);
            lineArr[i] = "|" + INDENT + lineArr[i] + " ".repeat(remainingSpace) + "|";
            System.out.println(lineArr[i]);
        }
        horizontal_line();
    }

}
