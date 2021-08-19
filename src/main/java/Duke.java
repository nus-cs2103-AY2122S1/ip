/**
 * Main program
 */
public class Duke {
    /**
     * Print out the greetings to the user
     */
    private static void greetings() {
        String logo = "\t  ____        _        \n"
                + "\t |  _ \\ _   _| | _____ \n"
                + "\t | | | | | | | |/ / _ \\\n"
                + "\t | |_| | |_| |   <  __/\n"
                + "\t |____/ \\__,_|_|\\_\\___|\n";

        PrintUtil.insertSeparateLine();
        System.out.println(logo);
        PrintUtil.displayContent("Hello! I'm Duke");
        PrintUtil.displayContent("What can I do for you?");
        PrintUtil.insertSeparateLine();
    }

    public static void main(String[] args) {
        Duke.greetings();
        DukeIoHandler ioHandler = new DukeIoHandler();
        ioHandler.start();
    }
}
