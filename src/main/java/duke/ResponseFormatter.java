package duke;

import java.io.IOException;

import java.util.ArrayList;

import duke.task.Task;

public class ResponseFormatter {
    /**
     * Returns a greeting from duke
     *
     */
    public String formatGreetings() {
        return "Hello I am Duke! What a pleasant surprise to see you here.\n"
                + "Are you sure you are ready to do work...";
    }
    /**
     * Returns the current list formatted
     *
     * @param taskList current list
     *
     */
    public String formatList(TaskList taskList) {
        ArrayList<Task> todos = taskList.getList();
        todos.trimToSize();
        String formatted = "";
        if (todos.size() == 0) {
            return " <(_ _)> Ehe no task for now.";
        } else {
            for (int i = 0; i < todos.size(); i++) {
                formatted += (i + 1) + ": " + todos.get(i).toString() + "\n";
            }
            return formatted;
        }
    }

    /**
     * Returns the task added to list formatted
     *
     * @param task new task added
     * @param taskLength the new length of the list
     *
     */
    public String formatAdd(Task task, int taskLength) {
        return "added: " + task.toString() + "\n"
                + "You have " + taskLength + " task(s) to go! (]><)]";
    }

    /**
     * Returns the goodbye message to user
     */
    public String formatBye() {
        return "Bye. Hope to see you again soon! =^_^=";
    }

    /**
     * Returns the task that has been marked as complete formatted
     *
     * @param completedTask the description field of the task that has been completed
     *
     */
    public String formatDone(String completedTask) {
        return "(@_@;) What a rarity! This task has been marked as done:\n"
                + completedTask;
    }

    /**
     * Returns the specific task deleted by user formatted
     *
     * @param deletedTask description field of the task that has been deleted
     * @param listLength current length of list after deletion
     *
     */
    public String formatDelete(String deletedTask, int listLength) {
        return "(~_~) Ok... This task has been deleted:\n"
                + "deleted: " + deletedTask + "\n"
                + "You have " + listLength + " task(s) to go! (]><)]";
    }

    /**
     * Returns file error that has occurred during storage
     *
     * @param e the error to getMessage()
     */
    public String formatFileError(IOException e) {
        return "（ ﾟ Дﾟ) File Error..." + e.getMessage();
    }

    /**
     * Returns type of error formatted
     *
     * @param message error message to be understood
     * @param emoticon emoticon to add expressions and character to duke
     *
     */
    public String formatError(String message, String emoticon) {
        return emoticon + " " + message;
    }

    /**
     * Return already formatted error messages
     *
     * @param message formatted error message
     *
     */
    public String formatError(String message) {
        return message;
    }

    /**
     * Return tasks related to keywords
     *
     * @param foundTasks list of tasks that matches
     */
    public String formatFound(ArrayList<Task> foundTasks) {
        String foundList = "";
        if (foundTasks.size() == 0) {
            return "(///￣ ￣///) No tasks with that keyword was found...";
        } else {
            for (int i = 0; i < foundTasks.size(); i++) {
                foundList += (i + 1) + ": " + foundTasks.get(i).toString() + "\n";
            }
            return "(*^u^*) Here are the matching tasks in your list:\n"
                    + foundList;
        }
    }

    public String formatUndo(String commandType) {
        return commandType + " has been undone!";
    }

    public String formatSnooze(String snoozedTask) {
        return "Ok..." + snoozedTask + " has been snoozed OwO";
    }
}
