package duke;

import java.util.Scanner;

public class Ui {
    private static final String LOGO =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE = "-----------------------------------------------------------------------\n";
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public static void printMessage(String msg) {
        System.out.println(LINE + msg + LINE);
    }

    public static void showWelcomeText() {
        Ui.printMessage(LOGO + "\nHello! I'm Duke :)\nWhat can I do for you?\n");
    }

    public static void showEndText() {
        Ui.printMessage("Bye. Hope to see you again soon!\n");
    }

    public static void showLoadingError() {
        Ui.printMessage("Seems like you do not have a previous save file. " +
                "I will create one for you once you input a task!\n");
    }
}
