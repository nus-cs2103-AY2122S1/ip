package duke.core;

public class Ui {
    private static final String LOGO =
            " ____        _        \n" +
            "|  _ \\ _   _| | _____ \n" +
            "| | | | | | | |/ / _ \\\n" +
            "| |_| | |_| |   <  __/\n" +
            "|____/ \\__,_|_|\\_\\___|\n";

    private static final String HORIZONTAL_SEPARATOR =
            "------------------------------------------------------------------------";

    /**
     * greet is called when the user starts up the program.
     *
     * @return a welcome message when user starts interacting with ChatBot
     */
    public void greetUser() {
        formatDisplay("Hello from\n" + LOGO +
                "Hello! I'm Duke!\nHow may I be of service to you?");
    }

    public void formatDisplay(String input) {
        StringBuilder sb = new StringBuilder();
        StringBuilder formattedSb = sb
                .append(HORIZONTAL_SEPARATOR)
                .append("\n")
                .append(input)
                .append("\n")
                .append(HORIZONTAL_SEPARATOR);
        System.out.println(formattedSb);
    }
}
