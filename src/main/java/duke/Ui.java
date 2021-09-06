package duke;

/**
 * UI of the Duke application.
 */
public class Ui {
    private static final String LINE = "-----------------------------------------";
    private static final String DEFAULT_MESSAGE = LINE + "\nHello! I'm duke.Duke \nWhat can I do for you?\n" + LINE;

    public static void sayHello() {
        System.out.println(DEFAULT_MESSAGE);
    }
}
