package duke.util;

import java.util.Scanner;

public class Ui {
    private boolean isRunning;
    private Scanner sc;
    private static final String INDENT = "    ";
    private static final String TOP_BORDER = "____________________________________";
    private static final String BOTTOM_BORDER = "------------------------------------";
    private static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String CLOSING_STATEMENT = "Bye, hope to see you again! :)";


    public Ui() {
        this.start();
        printFormatted("Hello from\n" + LOGO);
    }

    public void start() {
        this.sc = new Scanner(System.in);
        this.isRunning = true;
    }

    public void close() {
        sc.close();
        this.isRunning = false;
        printFormatted(CLOSING_STATEMENT);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public boolean isPendingReply() {
        return sc.hasNext();
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public static void printFormatted(String text) {
        String textWithBorders = TOP_BORDER + "\n"+  text + "\n" + BOTTOM_BORDER + "\n";
        String[] lines = textWithBorders.split("\n");
        for (String line : lines) {
            System.out.println(Ui.INDENT + line);
        }
    }
}
