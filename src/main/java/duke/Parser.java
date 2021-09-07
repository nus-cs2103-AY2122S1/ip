package duke;

/**
 * Deciphers user inputs and calls appropriate functions.
 */
public class Parser {
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates Parser object.
     *
     * @param tasks A list of Tasks to interact with.
     * @param ui Way to get user input.
     */
    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Main function of class.
     * Converts user inputs to Command and calls the corresponding functions.
     *
     * @param fullCommand User inputted String.
     * @return Whether or not the program continues or ends.
     */
    public String parse(String fullCommand) {
        String[] temp = fullCommand.split("\\s+", 2);
        Command cmd = Command.cmdFromString(temp[0]);
        String remainder = temp.length > 1 ? temp[1] : "";

        assert cmd != null : "Error with Parser occurred.";
        switch (cmd) {
        case LIST:
            //display tasklist
            return tasks.displayList();
        case FIND:
            //find keyword
            return tasks.findTask(remainder);
        case DONE:
            //mark task as done
            int finishedTaskIndex = Integer.parseInt(remainder);
            return tasks.markTaskDone(finishedTaskIndex - 1);
        case DELETE:
            //delete task
            int deletedTaskIndex = Integer.parseInt(remainder);
            return tasks.deleteTask(deletedTaskIndex - 1);
        case CLEAR:
            //clear all tasks
            return tasks.clearTasks();
        case TODO:
            //add Todo task
            return tasks.addTodo(remainder.trim());
        case DEADLINE:
            //add Deadline task
            String[] ds = remainder.split("/by");
            return tasks.addDeadline(ds);
        case EVENT:
            //add Event task
            String[] es = remainder.split("/at");
            return tasks.addEvent(es);
        case INVALID:
            //invalid user input
            return "I'm sorry, I don't understand. Please try again.";
        case BYE:
            //end program
            return "Bye. Hope to see you again soon!";
        default:
            //unreachable
            return "I'm sorry, I don't understand. Please try again.";
        }
    }
}
