public class Duke {

    private final static String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final static String LINE_SPLIT =
            "____________________________________________________________";

    public static void main(String[] args) {
        printLogo();
    }

    public static void printLogo() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(LINE_SPLIT);
    }
}
