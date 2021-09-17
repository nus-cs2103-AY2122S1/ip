package banana;

import java.util.ArrayList;

/**
 * The Parser class makes sense
 * of the user's input.
 *
 * @author: Ravi Ananya
 **/
public class Parser {

    private String input;
    protected static String prevInput;
    private static Task deletedTask;

    /**
     * Constructor for the Parser class.
     *
     * @param input the user input.
     */
    public Parser(String input) {
        this.input = input;
    }

    /**
     * Sets the previous input to be
     * handled in case of the user input
     * being "undo".
     *
     * @param prevInput the user's previous input.
     */
    public static void setPrevInput(String prevInput) {
        Parser.prevInput = prevInput;
    }

    /**
     * Checks how to handle the user's
     * input command.
     *
     * @param tasks the list of tasks.
     * @return the correct output.
     * @throws DukeException if the input was invalid.
     */
    public String parseInput(TaskList tasks) throws DukeException {
        exceptionCommand(tasks);
        if (input.equals("undo")) {
            return undoCommand(tasks);
        } else if (input.equals("list")) {
            return listCommand(tasks);
        } else if (input.contains("done")) {
            return doneCommand(tasks);
        } else if (input.contains("delete")) {
            return deleteCommand(tasks);
        } else if (input.contains("find")) {
            return findCommand(tasks);
        } else if (input.contains("deadline") || input.contains("event")) {
            return deadlineOrEventCommand(tasks);
        } else {
            return addTaskCommand(tasks);
        }
    }

    /**
     * Undoes the previous command's
     * action.
     *
     * @param tasks the list of tasks.
     * @return the correct output.
     */
    public String undoCommand(TaskList tasks) {
        String undoText = "";
        if (prevInput == null) {
            undoText = " You have not inputted anything"
                    + "to undo yet.";
        } else if (prevInput.contains("done")) {
            int index = Integer.parseInt(prevInput.substring(5));
            tasks.getTask(index - 1).setIsDone(false);
            undoText = " Undid done on task " + index;
        } else if (prevInput.contains("delete")) {
            int index = Integer.parseInt(prevInput.substring(7));
            TaskList latterTasks = tasks.subList(index - 1, tasks.getSize());
            tasks.removeTasks(latterTasks);
            tasks.addTask(deletedTask);
            tasks.addTasks(latterTasks);
            undoText = " Undid delete on task " + index;
        } else if (!prevInput.contains("find")
                && !prevInput.equals("list")) {
            Task temp = tasks.getTask(tasks.getSize() - 1);
            tasks.removeTask(temp);
            undoText = " Removed task " + temp.getDescription();
        } else {
            undoText = " This command does not change the tasklist,"
                    + "so there is nothing to undo.";
        }

        return undoText;
    }

    /**
     * Gets and prints the existing
     * list of tasks.
     *
     * @param tasks the list of tasks.
     * @return the correct output.
     */
    public String listCommand(TaskList tasks) {
        String itemCollection = ParserFunctions.getItems(tasks);
        String listText = " Here are the tasks in your list: \n"
                + "     ";
        return listText + itemCollection;
    }

    /**
     * Indicates a task as done.
     *
     * @param tasks the list of tasks.
     * @return the correct output.
     */
    public String doneCommand(TaskList tasks) {
        int index = Integer.parseInt(input.substring(5, 6)) - 1;
        tasks.getTask(index).setIsDone(true);
        String doneText = " Nice! I've marked this task as done: \n"
                + "       ";
        return doneText + tasks.getTask(index).toString();
    }

    /**
     * Adds a task or the subclass ToDo.
     *
     * @param tasks the list of tasks.
     * @return the correct output.
     */
    public String addTaskCommand(TaskList tasks) {
        if (input.contains("todo")) {
            String info = input.substring(5);
            tasks.addTask(new ToDo(info));
        } else {
            tasks.addTask(new Task(input));
        }
        String addTaskText = " Got it. I've added this task:  \n"
                + "       ";
        String taskNumberText = "\n"
                + " Now you have " + Integer.toString(tasks.getSize())
                + " tasks in the list.";
        return addTaskText + tasks.getTask(
                tasks.getSize() - 1).toString() + taskNumberText;
    }

    /**
     * Adds an Event or a Deadline and
     * gets the time/date in correct format
     * if necessary.
     *
     * @param tasks the list of tasks.
     * @return the correct output.
     */
    public String deadlineOrEventCommand(TaskList tasks) {
        assert input.contains("deadline") || input.contains("event");
        if (input.contains("deadline")) {
            assert input.contains("/by");
            String[] info = input.substring(9).split(" /by ");
            tasks.addTask(ParserFunctions.getDateAndTime(
                    info, "deadline"));
        } else {
            assert input.contains("/at");
            String[] info = input.substring(6).split(" /at ");
            tasks.addTask(ParserFunctions.getDateAndTime(
                    info, "event"));
        }
        String addTaskText = " Got it. I've added this task:  \n"
                + "       ";
        String taskNumberText = "\n"
                + " Now you have " + Integer.toString(tasks.getSize())
                + " tasks in the list.";
        return addTaskText + tasks.getTask(
                tasks.getSize() - 1).toString() + taskNumberText;
    }

    /**
     * Throws an exception if the wrong
     * commands/inputs have been entered.
     *
     * @param tasks the list of tasks.
     * @return the correct output.
     * @throws DukeException the exception.
     */
    public void exceptionCommand(TaskList tasks) throws DukeException {
        if (input.equals("todo") || input.equals("event")
                || input.equals("deadline")) {
            throw new DukeException(" OOPS!!! The description of "
                    + "a todo cannot be empty.");
        } else if (input.equals("done") || input.equals("done ")) {
            throw new DukeException(" OOPS!!! The completed task "
                    + "number must be given.");
        } else if (input.equals("delete") || input.equals("delete ")) {
            throw new DukeException(" OOPS!!! You need to specify which"
                    + " task you want to delete.");
        } else if (input.equals("blah")) {
            throw new DukeException(" OOPS!!! I'm sorry, but I don't know"
                    + " what that means :-(");
        } else {
            String[] splitInput = input.split(" ");
            if (splitInput.length == 2) {
                int index = Integer.parseInt(splitInput[1]);
                if (index < 0 || index >= tasks.getSize()) {
                    throw new DukeException(" OOPS!!! That index "
                            + "is out of bounds :-(");
                }
            }
        }
    }

    /**
     * Delete a task from the list.
     *
     * @param tasks the list of tasks.
     * @return the correct output.
     */
    public String deleteCommand(TaskList tasks) {
        int index = Integer.parseInt(input.substring(7, 8)) - 1;
        deletedTask = tasks.getTask(index);
        tasks.removeTask(deletedTask);
        String removeTaskText = " Noted. I've removed this task:  \n"
                + "       ";
        String taskNumberText = "\n"
                + " Now you have " + Integer.toString(tasks.getSize())
                + " tasks in the list.";
        return removeTaskText + deletedTask.toString()
                + taskNumberText;
    }

    /**
     * Finds the task that contains
     * or matches the user's input.
     *
     * @param tasks the list of tasks.
     * @return the correct output.
     */
    public String findCommand(TaskList tasks) {
        String outputText = " No matching tasks, sorry";
        TaskList newTasks = new TaskList(new ArrayList<>());
        if (input.contains("find")) {
            String item = input.split(" ")[1];
            for (int i = 0; i < tasks.getSize(); i++) {
                if (tasks.getTask(i).getDescription().contains(item)) {
                    newTasks.addTask(tasks.getTask(i));
                }
            }
            outputText = " Here are the matching tasks "
                    + "in your list: \n" + "     ";
            return outputText + ParserFunctions.getItems(newTasks);
        }
        return outputText;
    }


}
