package duke.util;

public class Ui {
    //Duke logo
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public Ui() {
    }

    /**
     * Welcome statement for new user.
     */
    public void welcome() {
        System.out.println("Hello from\n" + logo);
    }
}
