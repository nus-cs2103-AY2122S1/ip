package duke;

/**
 * Represents the Duke chatbot. The chatbot currently acts as a to-do list that can receives3 different types of tasks.
 * You can mark the tasks as done and delete them when you're done with the tasks.
 */
public class Duke {

    private static Parser parser = new Parser();
    private duke.Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Duke class.
     */
    public Duke() {
        ui = new Ui();
        storage = new duke.Storage("data/alexa.txt");
        try {
            tasks = new TaskList(duke.Storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            String text = "";
            if (input.equals("bye")) {
                text = "Bye. Hope to see you again soon!";
            } else if (input.equals("list")) {
                text = Ui.list();
            } else if (parser.parseDone(input)) {
                text = Ui.done(input);
            } else if (parser.parseFind(input)) {
                text = Ui.find(input);
            } else if (parser.parseToDo(input)) {
                text = TaskList.todo(input);
            } else if (parser.parseDeadline(input)) {
                text = TaskList.deadline(input);
            } else if (parser.parseEvent(input)) {
                text = TaskList.event(input);
            } else if (parser.parseDelete(input)) {
                text = TaskList.delete(input);
            } else {
                Ui.invalidInput();
                text = "Sorry, please enter a valid command!";
            }
            duke.Storage.writeTasks();
            return text;
        } catch (DukeException err) {
            return err.getMessage();
        }
    }
}
