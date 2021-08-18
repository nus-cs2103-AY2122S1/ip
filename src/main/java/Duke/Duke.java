package Duke;

public class Duke {
    private final static String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final static String GREETING_MESSAGE = "Hello from\n" + LOGO;

    private void say(String message) {
        System.out.println(message);
    }

    public void run() {
        this.say(GREETING_MESSAGE);
    }
}