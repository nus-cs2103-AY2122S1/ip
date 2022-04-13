package duke;

public class Parser {

    private final TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Reads userInput and calls on the corresponding function(s).
     *
     * @param userInput Input provided by user.
     */
    public String parseCommand(String userInput) throws DukeException {

        if (userInput.matches("list")) {

            return tasks.list();

        } else if (userInput.length() > 3 && userInput.substring(0, 4).matches("done")) {

            return tasks.markDone(userInput);

        } else if (userInput.length() > 5 && userInput.substring(0, 6).matches("delete")) {

            return tasks.delete(userInput);

        } else if (userInput.length() > 3 && userInput.substring(0, 4).matches("todo")) {

            return tasks.makeTodo(userInput);

        } else if (userInput.length() > 4 && userInput.substring(0, 5).matches("event")) {

            return tasks.makeEvent(userInput);

        } else if (userInput.length() > 7 && userInput.substring(0, 8).matches("deadline")) {

            return tasks.makeDeadline(userInput);

        } else if (userInput.length() > 3 && userInput.substring(0, 4).matches("find")) {

            return tasks.find(userInput);

        } else if (userInput.length() > 3 && userInput.substring(0, 4).matches("help")) {

            return tasks.getHelp();

        } else {

            return tasks.displayError();

        }

    }

}
