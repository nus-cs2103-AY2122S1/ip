package duke;

import java.util.Scanner;

public class Ui {

    private static final String PADDING = "   ";
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String getInput() {
        return sc.nextLine() + " ";
    }

    /**
     * Adds padding on the left of each line.
     *
     * @param display Text to be displayed.
     * @return Text with padding on the left.
     */
    public String addPadding(String display) {
        String[] lines = display.split("\n");
        StringBuilder out = new StringBuilder();
        for (String line: lines) {
            out.append(PADDING).append(line).append("\n");
        }
        return out.toString();
    }

    public void close() {
        this.sc.close();
    }
}
