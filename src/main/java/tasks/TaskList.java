package tasks;

import java.time.format.DateTimeParseException;
import java.util.List;

import duke.DukeException;
import duke.Storage;


/**
 * A TaskList class that handles all the methods that manipulates
 * the task list.
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * The output that will be shown when the input command is list.
     * 
     * @param str
     * @return a String with all the existing tasks shown in a list view. 
     */
    public String showList(String str) {
        for (int i = 0; i < tasks.size(); i++) {
            str += (i+1) + ". " + tasks.get(i);
            if (i != tasks.size() - 1) {
                str += "\n";
            }
        }
        return str;
    }

    /**
     * A method to mark the task as done
     * 
     * @param str The input command
     * @return The output that you want to be printed out in the console
     */
    public String markDone(String str) {
        int completedTaskIndex = Integer.parseInt(str.substring(5)) - 1;
        tasks.get(completedTaskIndex).taskDone();
        Storage.getAllTasks(tasks);
        return "Nice! I've marked this task as done: \n" + tasks.get(completedTaskIndex);
    }

    /**
     * A method to create & add a to-do task
     * 
     * @param str The input command
     * @return A response from the bot to indicate a to-do task has been created
     * and added to the task list
     */
    public String todoTask(String str) throws DukeException {
        try {
            str = str.substring(5);
            Task task = new Todo(str);
            tasks.add(task);
            Storage.writeLine(task);
            return "Got it. I've added this task: \n"
                    + task
                    + "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (StringIndexOutOfBoundsException e) {
            return "☹ OOPS!!! The description of a todo cannot be empty.";
        }

    }

    /**
     * A method to create & add a deadline task
     *
     * @param str The input command
     * @return A response from the bot to indicate a deadline task has been created
     * and added to the task list
     */
    public String deadlineTask(String str) {
        try {
            int slashIndex = str.indexOf("/");
            String day = str.substring(slashIndex + 4, slashIndex + 14);
            String time = str.substring(slashIndex + 14);
            Task task = new Deadline(str.substring(0, slashIndex), Storage.formatDate(day) + time);
            tasks.add(task);
            Storage.writeLine(task);
            return "Got it. I've added this task: \n"
                    + task
                    + "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (StringIndexOutOfBoundsException e) {
            return "☹ OOPS!!! The description of a deadline cannot be empty.\n" + 
                    "If you have entered a day instead of a date, please enter a date in the format: yyyy-mm-dd.";
        } catch (DateTimeParseException e) {
            return "☹ OOPS!!! Please use the date format: yyyy-mm-dd.";
        }
    }

    /**
     * A method to create & add an event
     *
     * @param str The input command
     * @return A response from the bot to indicate a event has been created
     * and added to the task list
     */
    public String eventsTask(String str) {
        try {
            int slashIndex = str.indexOf("/");
            String day = str.substring(slashIndex + 4, slashIndex + 14);
            Task task = new Events(str.substring(0, slashIndex), day);
            tasks.add(task);
            Storage.writeLine(task);
            return "Got it. I've added this task: \n"
                    + task
                    + "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (StringIndexOutOfBoundsException e) {
            return "☹ OOPS!!! The description of a deadline cannot be empty.\n" +
                    "If you have entered a day instead of a date, please enter a date in the format: yyyy-mm-dd.";
        } catch (DateTimeParseException e) {
            return "☹ OOPS!!! Please use the date format: yyyy-mm-dd.";
        }
    }

    /**
     * A method to delete a task
     *
     * @param str The input command
     * @return A response from the bot to indicate a task has been deleted
     * and added to the task list
     */
    public String deleteTask(String str) {
        int indexOfTaskToDelete = Integer.parseInt(str.substring(7)) - 1;
        Task t = tasks.get(indexOfTaskToDelete);
        tasks.remove(indexOfTaskToDelete);
        Storage.getAllTasks(tasks);
        return "Got it. I've deleted this task: \n"
                + t
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Command to find and display all tasks containing keyword 
     * @param keyword word to search for
     * @return A string of all the results presented in a list view
     */
    public String findTask(String keyword) {
        String results = "Here are the matching tasks in your list: \n";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).description.contains(keyword))
                results += (i+1) + ". " + tasks.get(i);
            if (i != tasks.size() - 1) {
                results += "\n";
            }
        }
        return results;
    } 
}