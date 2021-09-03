package duke;

import java.util.ArrayList;
import java.util.function.Function;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


/**
 *  A class that encapsulates the list of task inputted by Duke
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds the new Task into list and return the corresponding String reply.
     *
     * @param type The type of the task that will be added
     * @param input The corresponding description provided for the task
     * @param isDone The boolean if the task is done
     * @return The String of the reply after adding a task
     * @throws DukeException Exceptions specific to Duke's input
     */
    public String addTask(Task.TaskName type, String input, Boolean isDone) throws DukeException {
        Task task;
        String[] inputArray;

        switch (type) {
        case TODO:
            task = new ToDo(input, isDone);
            break;

        case EVENT:
            // Fallthrough
        case DEADLINE:
            inputArray = input.split(type.getSplit());
            if (inputArray.length < 2) {
                throw new DukeException("The format for " + type + " is wrong.");
            } else if (inputArray[0].isBlank()) {
                throw new DukeException("The description of " + type + " cannot be empty.");
            } else if (inputArray[1].isBlank()) {
                throw new DukeException("The date/time is missing from " + type + ".");
            }
            task = type == Task.TaskName.EVENT ? new Event(inputArray[0], inputArray[1], isDone)
                    : new Deadline(inputArray[0], inputArray[1], isDone);
            break;

        default:
            throw new DukeException("Unexpected value: " + type);
        }

        this.tasks.add(task);
        return "\tGot it. I've added this task:\n\t\t " + task
                + "\n\tNow you have " + tasks.size() + " tasks in the list.\n";
    }

    /**
     * Adds the new Task into list and return the corresponding String reply.
     *
     * @param type The type of the task that will be added
     * @param input The corresponding description provided for the task
     * @return The String of the reply after adding a task
     * @throws DukeException Exceptions specific to Duke's input
     */
    public String addTask(Task.TaskName type, String input) throws DukeException {
        return addTask(type, input, false);
    }


    /**
     * Returns the list of all the Tasks.
     *
     * @return The String of the list of all the tasks formatted
     */
    public String displayTask() {
        StringBuilder output = new StringBuilder("\tHere are the tasks in your list:\n");
        int i = 1;
        for (Task task: this.tasks) {
            output.append("\t").append(i).append(".").append(task).append("\n");
            i++;
        }
        return output.toString();
    }

    /**
     * Marks the Task at the index of the list to be done.
     *
     * @param input The index of the Task in the list that is to be mark as done
     * @return The corresponding String reply after marking a task done
     * @throws DukeException Exceptions specific to Duke's input
     */
    public String markTask(String input) throws DukeException {
        int index;

        try {
            index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DukeException("The index provided is not a number.");
        }
        if (index > this.tasks.size() || index < 1) {
            throw new DukeException("The index provided is not within the valid range");
        }

        return "\tNice! I've marked this task as done:\n\t\t" + tasks.get(index - 1).markDone() + "\n";
    }

    /**
     * Deletes the task at the specific index.
     *
     * @param input The index of the task in the list that is to be deleted
     * @return The corresponding String reply after deleting a task
     * @throws DukeException Exceptions specific to Duke's input
     */
    public String deleteTask(String input) throws DukeException {
        int index;

        try {
            index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DukeException("The index provided is not a number.");
        }
        if (index > this.tasks.size() || index < 1) {
            throw new DukeException("The index provided is not within the valid range");
        }

        Task deleted = this.tasks.remove(index - 1);
        return "\tNoted. I've removed this task:\n\t\t" + deleted.toString()
                + "\n\tNow you have " + this.tasks.size() + " tasks in the list.\n";
    }

    /**
     * Returns the list of all tasks whose description which matches the provided keyword.
     *
     * @param input The String of the keyword that will be used to search the TaskList
     * @return The reply for Duke containing all the task matching the keyword
     */
    public String findTask(String input) {
        StringBuilder reply = new StringBuilder("Here are the tasks matching the keyword: ")
                .append(input).append("\n");
        int i = 1;

        for (Task task: this.tasks) {
            if (task.matchKeyword(input)) {
                reply.append(i).append(".").append(task).append("\n");
                i++;
            }
        }

        if (i == 1) {
            reply = new StringBuilder("No task matching the keyword: ")
                    .append(input).append("\n");
        }

        return reply.toString();
    }

    /**
     * Iterates the list and perform a function on each task.
     *
     * @param f the function to be applied on each task
     */
    public void iterateList(Function<Task, Void> f) {
        for (Task t: this.tasks) {
            f.apply(t);
        }
    }
}
