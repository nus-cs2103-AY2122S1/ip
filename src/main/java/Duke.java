public class Duke {
    private String command;

    private Duke(String command) {
        this.command = command;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Hello from\n" + logo + "How may I help you today master?\n");
        System.out.println("-------------------------------------------------------------------");
    }
}
