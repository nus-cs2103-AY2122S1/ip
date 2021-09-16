package duke;

/**
 * Class that parse the input from the user's input.
 */
public class Parser {
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";
    private static final String TODO_COMMAND = "todo";
    private static final String EVENT_COMMAND = "event";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EXIT_COMMAND = "bye";
    private static final String HELP_COMMAND = "help";

    private Ui ui;
    int taskNumber;

    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Performs the actions based on what the user input.
     *
     * @param input String containing text to add
     * @return string to be showed on the GUI
     */
    public String parse(String input) {
        if (input.startsWith(EXIT_COMMAND)) {
            return ui.goodbye();
        } else if (input.equals(LIST_COMMAND)) {
            return ui.listTasks();
        } else {
            if (input.startsWith(DONE_COMMAND)) {
                taskNumber = Integer.parseInt(input.substring(input.indexOf(" ") + 1)) - 1;
                assert taskNumber >= 0 : "Task number should be positive";
                return ui.markDone(taskNumber);
            } else if (input.startsWith(TODO_COMMAND)) {
                Todo todo = new Todo(input.substring(input.indexOf(" ") + 1));
                return ui.add(todo);
            } else if (input.startsWith(DEADLINE_COMMAND)) {
                Deadline deadline = new Deadline(input.substring(input.indexOf(" ") + 1, input.indexOf(" /by")),
                        input.substring(input.indexOf("/by") + 4));
                return ui.add(deadline);
            } else if (input.startsWith(EVENT_COMMAND)) {
                Event event = new Event(input.substring(input.indexOf(" ") + 1, input.indexOf(" /at")),
                        input.substring(input.indexOf("/at") + 4));
                return ui.add(event);
            } else if (input.startsWith(DELETE_COMMAND)) {
                taskNumber = Integer.parseInt(input.substring(input.indexOf(" ") + 1)) - 1;
                assert taskNumber >= 0 : "Task number should be positive";
                return ui.delete(taskNumber);

            } else if (input.startsWith(FIND_COMMAND)) {
                int thingToFind = input.indexOf(" ") + 1;
                return ui.findTasks(input.substring(thingToFind));
            } else if (input.startsWith(HELP_COMMAND)) {
                String helpMessage = "Welcome to the quick help page!\n" +
                        "Here are some examples of what you can type: \n" +
                        "\t1) help\n" +
                        "\t2) list\n" +
                        "\t3) todo do homework\n" +
                        "\t4) deadline assignment \\by 2021-09-24\n" +
                        "\t5) event career fair \\at 2021-09-08\n" +
                        "\t6) find homework\n" +
                        "\t7) done 3\n" +
                        "\t8) delete 1\n" +
                        "\t9) bye\n";
                return helpMessage;
            }
        }
        return "I don't understand. Please try again!";
    }
}
