public class Ui {
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static String WelcomeMessage = "    ____________________________________________________________\n"
            + "     Hello! I'm Duke\n"
            + "     What can I do for you?\n"
            + "    ____________________________________________________________";

    private static String ByeMessage = "    ____________________________________________________________\n"
            + "     Bye. Hope to see you again soon!\n"
            + "    ____________________________________________________________";

    public Ui() {
        System.out.println("Hello from\n" + logo + WelcomeMessage);
    }

    public void printBye() {
        System.out.println(ByeMessage);
    }
}
