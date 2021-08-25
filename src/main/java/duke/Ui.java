package duke;

public class Ui {
    private final static String LINE = "-----------------------------------------";
    private final static String DEFAULT_MESSAGE = LINE + "\nHello! I'm duke.Duke \nWhat can I do for you?\n" + LINE;

    public static void sayHello() {
        System.out.println(DEFAULT_MESSAGE);
    }
}