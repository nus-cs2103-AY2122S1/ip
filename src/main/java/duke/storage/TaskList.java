package duke.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import duke.DukeExceptions;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * List class to hold the listed Tasks.
 */

public class TaskList extends ArrayList<Task> {

    private static final String DIVIDER = "%%";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Here are the tasks in your list! \n");
        int index = 1;
        for (Task task : this) {
            result.append(index)
                    .append(". ")
                    .append(task.toString())
                    .append('\n');
            index++;
        }
        return (result.toString().stripTrailing());
    }

    /**
     * Add all the task in the List of String.
     *
     * @param inputString The list of lines of string.
     */
    public void importFromList(List<String> inputString) {
        this.clear();
        for (String line : inputString) {
            String[] splitString = line.split(DIVIDER, 0);
            Task task;
            if (splitString[0].equals("T")) {
                task = ToDo.create(splitString[2]);
            } else if (splitString[0].equals("E")) {
                task = Event.create(splitString[2], LocalDateTime.parse(splitString[3],
                        formatter));
            } else {
                task = Deadline.create(splitString[2], LocalDateTime.parse(splitString[3],
                        formatter));
            }
            task.markFinished(splitString[1].equals("X"));
            this.add(task);
        }
    }

    /**
     * Exports the current list into a format to be written into a file.
     *
     * @return Formatted string using specified divider.
     */
    public String exportToText() {
        StringBuilder result = new StringBuilder();
        for (Task currTask : this) {
            result.append(currTask.getTaskType())
                    .append(DIVIDER)
                    .append(currTask.getStatusIcon())
                    .append(DIVIDER)
                    .append(currTask.getDescription());

            if (!(currTask.getTaskType().equals("T"))) {
                result.append(DIVIDER)
                        .append(currTask.getTime());
            }
            result.append('\n');
        }
        return result.toString();
    }

    /**
     * Marks specified task as finished.
     *
     * @param index Index of task to be marked as finished from the list.
     * @throws DukeExceptions if the task is already marked as finished.
     */
    public void markAsFinished(int index) throws DukeExceptions {
        Task task = this.get(index);
        if (task.getStatusIcon().equals("X")) {
            throw new DukeExceptions("That task was already marked as done");
        } else {
            task.markFinished(true);
        }
    }

    /**
     * Looks through all the task and outputs the tasks which contains the keyword.
     *
     * @param keyword The keyword to look for.
     * @return The list of tasks which contains the keyword.
     */
    public String find(String keyword) {
        StringBuilder result = new StringBuilder();
        result.append("Here are the matching tasks in your list! \n");
        for (Task task : this) {
            if (task.getDescription().contains(keyword)) {
                result.append(indexOf(task) + 1)
                        .append(". ")
                        .append(task)
                        .append('\n');
            }
        }
        return (result.toString().stripTrailing());
    }
}
