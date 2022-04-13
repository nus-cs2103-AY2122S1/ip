package duke;

import java.util.ArrayList;

/**
 * Represents an array of tasks that the user inputs in.
 */
public class TaskList {
    protected ArrayList<Task> listOfTasks;

    TaskList(ArrayList<Task> loaded) {
        listOfTasks = loaded;
    }

    TaskList() {
        this(new ArrayList<>(100));
    }

    /**
     * Returns a list of tasks that matches the word to be searched.
     * @param word Word to be searched in all the tasks in this task list.
     * @return String of the list of all the tasks that contains this word.
     */
    public String listMatchingTasks(String word) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        int counter = 1;
        for (Task t : listOfTasks) {
            if (t.containWord(word)) {
                sb.append(counter + ". " + t + "\n");
                counter += 1;
            }
        }
        if (counter == 1) {
            sb = new StringBuilder();
            sb.append("No matching tasks found!");
        }
        return sb.toString();

    }
    /**
     * Lists all the tasks within the array.
     * Numbered from 1 onwards.
     *
     * @return A string containing list of tasks.
     */
    public String listTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list: \n");
        int counter = 1;
        for (Task t : listOfTasks) {
            sb.append(counter + ". " + t + "\n");
            counter += 1;
        }

        return sb.toString();
    }

    /**
     * Adds a task to the internal array.
     *
     * @param command Command to be parsed.
     * @param parameter Description of task.
     * @param date Provided date of task.
     */
    public String addTask(Command command, String parameter, String date) {
        int prevLength = listOfTasks.size();
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task: \n");
        String parsedDate = Parser.parseDate(date).equals("") ? date : Parser.parseDate(date);
        Task task;

        if (command.equals(Command.TODO)) {
            task = new Todo(parameter);
        } else if (command.equals(Command.DEADLINE)) {
            task = new Deadline(parameter, parsedDate);
        } else if (command.equals(Command.EVENT)) {
            task = new Event(parameter, parsedDate);
        } else {
            throw new DukeException("Invalid command");
        }
        listOfTasks.add(task);
        sb.append(task + "\n");
        sb.append("Now you have " + String.valueOf(listOfTasks.size()) + " tasks in the list.");
        assert (listOfTasks.size() - prevLength == 1);
        return sb.toString();



    }

    public String addTask(Command command, String parameter) {
        return addTask(command, parameter, "");
    }

    /**
     * Removes task from task list.
     * @param index Index of task to be removed.
     */
    public String removeTask(int index) {
        int taskSizeInit = listOfTasks.size();
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:\n");
        sb.append(listOfTasks.get(index).toString() + "\n");
        listOfTasks.remove(index);
        sb.append("Now you have " + listOfTasks.size() + " tasks in the list.");
        assert (listOfTasks.size() - taskSizeInit == -1);
        return sb.toString();
    }

    /**
     * Returns size of task list.
     * @return Integer showing size of task list.
     */
    protected int getSize() {
        return listOfTasks.size();
    }

    /**
     * Shows a task as done.
     *
     * @param i Index of task to be done.
     */
    public String markAsDone(Integer i) {
        Task task = listOfTasks.get(i);
        task.markAsDone();
        return "Nice! I've marked this task as done:\n" + task;
    }
}
