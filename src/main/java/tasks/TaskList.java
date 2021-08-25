package tasks;

import duke.DukeException;
import duke.Storage;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;



public class TaskList {
    private final List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }


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
     * @param str The input String
     * @return The output that you want to be printed out in the console
     */
    public String markDone(String str) {
        int a = Integer.parseInt(str.substring(5)) - 1;
        tasks.get(a).taskDone();
        Storage.getAllTasks(tasks);
        return "Nice! I've marked this task as done: \n" + tasks.get(a);
    }

    /**
     * A method to add a to-do task
     * @param str A string the that is the command
     * @return The output that you want to be printed out in the console
     */
    public String todoTask(String str) throws DukeException {
        try {
            str = str.substring(5);
            Task t = new Todo(str);
            tasks.add(t);
            Storage.writeLine(t);
            return "Got it. I've added this task: \n"
                    + t
                    + "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (StringIndexOutOfBoundsException e) {
            return "☹ OOPS!!! The description of a todo cannot be empty.";
        }

    }

    /**
     * A method to add a create deadline task
     * @param str A string the that is the command
     * @return The output that you want to be printed out in the console
     */
    public String deadlineTask(String str) {
        try {
            int i = str.indexOf("/");
            String day = str.substring(i + 4, i + 14);
            String time = str.substring(i + 14);
            Task t = new Deadline(str.substring(0, i), Storage.formatDate(day) + time);
            tasks.add(t);
            Storage.writeLine(t);
            return "Got it. I've added this task: \n"
                    + t
                    + "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (StringIndexOutOfBoundsException e) {
            return "☹ OOPS!!! The description of a deadline cannot be empty.\n" + 
                    "If you have entered a day instead of a date, please enter a date in the format: yyyy-mm-dd.";
        } catch (DateTimeParseException e) {
            return "☹ OOPS!!! Please use the date format: yyyy-mm-dd.";
        }
    }

    /**
     * A method to add a create task
     * @param str A string the that is the command
     * @return The output that you want to be printed out in the console
     */
    public String eventsTask(String str) {
        try {
            int i = str.indexOf("/");
            String day = str.substring(i + 4, i + 14);
            Task t = new Events(str.substring(0, i), day);
            tasks.add(t);
            Storage.writeLine(t);
            return "Got it. I've added this task: \n"
                    + t
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
     * @param str A string the that is the command
     * @return The output that you want to be printed out in the console
     */
    public String deleteTask(String str) {
        int index = Integer.parseInt(str.substring(7)) - 1;
        Task t = tasks.get(index);
        tasks.remove(index);
        Storage.getAllTasks(tasks);
        return "Got it. I've deleted this task: \n"
                + t
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

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