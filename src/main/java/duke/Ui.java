package duke;

public class Ui {
    private final Duke duke;
    /**
     * A public constructor to initialized the Ui.
     */
    public Ui(Duke duke) {
        this.duke = duke;
    }

    /**
     * A method to return the logo of duke
     *
     * @return A String, the logo of duke.
     */
    public static String printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return generateDukeResponse("Hello from", logo);
    }

    /**
     * A method to return the greeting message of duke.
     *
     * @return A String, the greeting message of duke.
     */
    public static String greet() {
        return generateDukeResponse(
            "Hello! I'm Duke",
            "What can I do for you?");
    }

    /**
     * A public method to generate duke response.
     *
     * @param messages The given message, can be the description of the
     *                 result of execution, or the exception message.
     * @return A String, the duke response.
     */
    public static String generateDukeResponse(String ...messages) {
        String dukeResponse = new String("");
        int n = messages.length;
        for (int i = 0; i < n; i++) {
            dukeResponse += messages[i] + "\n";
        }
        return dukeResponse;
    }
}
