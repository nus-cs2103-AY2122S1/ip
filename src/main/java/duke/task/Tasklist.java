package duke.task;

import duke.*;
import duke.command.CommandKeyword;
import duke.exception.DukeException;
import duke.exception.IncompleteTaskDescriptionException;
import duke.exception.InvalidCommandException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    /**
     * Constructs a task list.
     *
     * @param tasksInfo Arraylist of tasks loaded from storage.
     * @param ui An Ui instance to deal with interactions with the user.
     */
    public TaskList(ArrayList<String> tasksInfo, Ui ui) {
        List<Task> list = tasksInfo.stream().map(Task::convertToTask).collect(Collectors.toList());
        this.tasks = new ArrayList<>(list);
        this.ui = ui;
    }

    public void display() {
        ui.showTasks(this.tasks);
    }

    public ArrayList<Task> markTask(int index) {
        try {
            Task task = this.tasks.get(index);
            task.MarkAsDone();
            this.ui.showMarkedTask(task);
            return this.tasks;
        } catch (IndexOutOfBoundsException e) {
            this.ui.showMarkedTask(null);
            return null;
        }
    }

    public ArrayList<Task> addTask(String description, CommandKeyword keyword) throws DukeException {
        Task task = null;
        switch (keyword) {
            case TODO:
                if (!description.equals("")) {
                    task = new Todo(description);
                    break;
                } else {
                    throw new IncompleteTaskDescriptionException("todo");
                }
            case DEADLINE:
                if (description.matches("[^ ].* /by *[^ ].* [^ ].*")) {
                    int separator = description.indexOf("/by");
                    String taskDetail = description.substring(0, separator).trim();
                    String[] by = description.substring(separator + 3).trim().split(" ");
                    try {
                        LocalDate date = LocalDate.parse(by[0].trim());
                        LocalTime time = LocalTime.parse(by[1].trim());
                        task = new Deadline(taskDetail, date, time);
                    } catch (DateTimeParseException e) {
                        throw new IncompleteTaskDescriptionException("deadline");
                    }
                    break;
                } else {
                    throw new IncompleteTaskDescriptionException("deadline");
                }
            case EVENT:
                if(description.matches("[^ ].* /at *[^ ].* [^ ].*")) {
                    int separator = description.indexOf("/at");
                    String taskDetail = description.substring(0, separator).trim();
                    String at = description.substring(separator + 3).trim();
                    try {
                        int index = at.indexOf(' ');
                        LocalDate date = LocalDate.parse(at.substring(0, index));
                        String timeRange = at.substring(index + 1).trim();
                        task = new Event(taskDetail, date, timeRange);
                    } catch (DateTimeParseException e) {
                        throw new IncompleteTaskDescriptionException("event");
                    }
                    break;
                } else {
                    throw new IncompleteTaskDescriptionException("event");
                }
            default:
                // checked for command validity in duke.Parser class, so this should not execute at all
                throw new InvalidCommandException();
        }
        tasks.add(task);
        this.ui.showAddTask(task, this.tasks.size());
        return this.tasks;
    }

    public ArrayList<Task> deleteTask(int index) {
        try {
            Task task = this.tasks.get(index);
            this.tasks.remove(index);
            this.ui.showDeletedTask(task, this.tasks.size());
            return this.tasks;
        } catch (IndexOutOfBoundsException e) {
            this.ui.showDeletedTask(null, -1);
            return null;
        }
    }
}
