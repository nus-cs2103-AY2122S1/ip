package duke;

public class Ui {
    public static void printResponse(String ... lines) {
        String LINE = "    --------------------------------------------------";
        String INDENTATION = "      ";
        System.out.println(LINE);
        for (String line : lines) {
            System.out.println(INDENTATION + line);
        }
        System.out.println(LINE);
    }
}
