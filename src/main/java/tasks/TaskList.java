package tasks;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * A constructor for TaskList specifying list of saved tasks.
     *
     * @param tasks array list of Task.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * A constructor for TaskList.
     */
    public TaskList() {
    }

    /**
     * Returns a list of current tasks.
     *
     * @return a string of current tasks.
     */
    public String list() {
        int count = 1;
        String str = "Here are the tasks in your list:";
        for (Task t : tasks) {
            str += "\n" + count + "." + t.getTask();
            count += 1;
        }
        return str;
    }

    /**
     * Marks the task done and returns the task description.
     *
     * @param str user input to indicate a task is done.
     * @return description of task that is marked done.
     */
    public String done(String str) {
        int index = Integer.parseInt(str.substring(5)) - 1;
        return tasks.get(index).markDone();
    }

    /**
     * Deletes the task and returns the task description.
     *
     * @param str user input to indicate deletion of a task.
     * @return description of task that is deleted.
     */
    public String delete(String str) {
        int index = Integer.parseInt(str.substring(7)) - 1;
        String result = "Noted. I've removed this task: \n" + tasks.get(index).delete() +
                "\nNow you have " + (tasks.size() - 1) + " tasks in the list.";
        tasks.remove(index);
        return result;
    }

    /**
     * Returns the task description if it is successfully added,
     * or return error message if the user input is invalid.
     *
     * @param str user input to add task.
     * @return description of task added or error message.
     */
    public String addTask(String str) {
        String type = "";
        try {
            String[] words = str.split(" ");
            if (words.length == 1) {
                return "☹ OOPS!!! The description of a task cannot be empty.";
            } else {
                words = str.split(" ", 2);
                Task task;
                type = words[0];
                String text = words[1];

                switch (type) {
                    case "todo":
                        task = new Todo(text, false);
                        break;
                    case "deadline":
                        task = new Deadline(text, false);
                        break;
                    case "event":
                        task = new Event(text, false);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected type: " + type);
                }
                String description = task.getTask();
                tasks.add(task);
                return "Got it. I've added this task: \n" + description + "\nNow you have " +
                        tasks.size() + " tasks in the list.";
            }
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            String message = "";
            switch (type) {
                case "deadline":
                    message = "☹ OOPS!!! Please use the format: deadline <description> /by yyyy-mm-ddTHH:mm";
                    break;
                case "event":
                    message = "☹ OOPS!!! Please use the format: event <description> /from yyyy-mm-ddTHH:mm /to yyyy-mm-ddTHH:mm";
                    break;
                default:
                    throw new IllegalStateException("Unexpected type: " + type);
            }
            return message;
        }
    }

    /**
     * Returns the size of the current task list.
     *
     * @return an integer of task size.
     */
    public int getSize() {
        return tasks.size();
    }
}
