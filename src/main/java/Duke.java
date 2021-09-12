public class Duke {
    public static void main(String[] args) {
<<<<<<< Updated upstream
        String logo = " ____        _        \n"
=======
        Duke user = new Duke();
        boolean end = true;
        user.userInterface.greet();
        user.userInterface.getDataInputList();
        while (end) {
            end = user.userInterface.echo();
            System.out.println("__________________________________");
        }
    }

    public String getResponse(String input) {
            return userInterface.choiceOfAction(input);
    }

    public static final String greet() {
        return Ui.greet();
    }
}


//deals with interactions with the user
class Ui {
    private final Storage store = new Storage();
    private TaskList taskList = new TaskList();
    /**
     * Generate the initiate message.
     */
    public static final String greet() {
        String logo = "__________________________________\n"
                +" ____        _        \n"
>>>>>>> Stashed changes
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}
