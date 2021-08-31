import duke.TaskList;

public class Parser {

    TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Reads userInput and calls on the corresponding function(s).
     *
     * @param userInput Input provided by user.
     */
    public void parseCommand(String userInput) {

        if (userInput.matches("list")) {

            tasks.list();

        }  else if (userInput.length() > 3 && userInput.substring(0, 4).matches("done")){

            tasks.markDone(userInput);

        } else if (userInput.length() > 5 && userInput.substring(0, 6).matches("delete")){

            tasks.delete(userInput);

        } else if (userInput.length() > 3 && userInput.substring(0, 4).matches("todo")) {

            tasks.makeTodo(userInput);

        } else if (userInput.length() > 4 && userInput.substring(0, 5).matches("event")) {

            tasks.makeEvent(userInput);

        } else if (userInput.length() > 7 && userInput.substring(0, 8).matches("deadline")) {

            tasks.makeDeadline(userInput);

        } else if (userInput.length() > 3 && userInput.substring(0, 4).matches("find")) {

            tasks.find(userInput);

        } else {

            tasks.error();

        }

    }

}