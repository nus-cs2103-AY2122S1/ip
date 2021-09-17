package duke;

import java.util.ArrayList;

import duke.command.AddCommand;
import duke.command.CommandsTypes;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkDoneCommand;
import duke.command.TagCommand;
import duke.task.Task;


/**
 * Class to handle the user interface of Duke.
 */
public class Ui {

    /**
     * Prints out sentence welcoming user when Duke starts.
     *
     * @return welcome message by Duke.
     */
    public String showWelcome() {
        return "Urgh I hate having to wake up. Why did you do that";
    }

    /**
     * Returns confirmation when user adds a task.
     *
     * @param task the task which is added.
     * @param count the current number of tasks.
     * @return confirmation when user adds a task.
     */
    public String taskAdded(Task task, int count) {
        String toPrint = String.format("Got it. I've added this task:\n     "
                        + "%s\nNow you have %x task%s in the list.",
                task.toString(), count, count > 1 ? "s" : "");
        return toPrint;
    }

    /**
     * Return string saying bye.
     *
     * @return String saying bye.
     */
    public String sayBye() {
        return "Don't wake me up again";
    }

    /**
     * Returns sentence confirming when user marks a task as done.
     *
     * @param task task that is marked done.
     * @return sentence confirming when user marks a task as done.
     */
    public String showMarkDone(Task task) {
        return "Toight!\n" + task + " marked done.";
    }

    /**
     * Returns sentence confirming when user deletes a task.
     *
     * @param task task that is deleted.
     * @return string for delete command.
     */
    public String deleteTask(Task task) {
        return task + " deleted. Bruh be more careful next time";
    }

    /**
     * Return string of the list of current tasks.
     *
     * @param tasks the current list of tasks.
     * @return string of list of current tasks.
     */
    public String printList(Tasklist tasks) {
        return tasks.toString();
    }

    /**
     * Returns string for exception thrown.
     *
     * @param e the exception that was thrown.
     * @return string for exception thrown.
     */
    public String showError(Exception e) {
        return e.getMessage();
    }

    /**
     * Returns string for find command.
     *
     * @param tasks tasks that are related to keyword.
     * @param keywords keywords that user is searching for.
     * @return string for find command.
     */
    public String printRelatedTasks(Tasklist tasks, ArrayList<String> keywords) {
        String strKeywords = keywords.stream().reduce((x, y) -> x + " " + y).orElse("");
        return "Your tasks that match with " + strKeywords + ":\n" + this.printList(tasks);
    }

    /**
     * Returns string for tag command.
     *
     * @param taggedTasks tasks that are tagged.
     * @param tags tags for tasks.
     * @return string for tag command.
     */
    public String showTagsAdded(Tasklist taggedTasks, ArrayList<String> tags) {
        String tagString = tags.toString();
        return "Tagged tasks:\n" + taggedTasks + " with " + tagString;
    }

    /**
     * Returns help message for different command types.
     *
     * @param commandsType the command type which user wants help with.
     * @return help message for command type which user wants.
     */
    public String helpMessage(CommandsTypes commandsType) {
        switch (commandsType) {
            case ADD: {
                return AddCommand.getHelpMessage();
            }
            case LIST: {
                return ListCommand.getHelpMessage();
            }
            case DELETE: {
                return DeleteCommand.getHelpMessage();
            }
            case TAG: {
                return TagCommand.getHelpMessage();
            }
            case FIND: {
                return FindCommand.getHelpMessage();
            }
            case MARK_DONE: {
                return MarkDoneCommand.getHelpMessage();
            }
            case HELP: {
                return HelpCommand.getHelpMessage();
            }
            default: {
                assert false : "Unknown command type for help";
            }
        }
        return null;
    }
}
