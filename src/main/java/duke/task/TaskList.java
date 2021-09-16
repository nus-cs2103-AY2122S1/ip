package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.command.CommandKeyword;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Contains the task list. It has operations to change the tasks in the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    /**
     * Constructs a taskList.
     *
     * @param tasksInfo Arraylist of tasks loaded from storage.
     * @param ui An Ui instance to deal with interactions with the user.
     */
    public TaskList(ArrayList<String> tasksInfo, Ui ui) {
        List<Task> list = tasksInfo.stream().map(Task::convertToTask).collect(Collectors.toList());
        this.tasks = new ArrayList<>(list);
        this.ui = ui;
    }

    /**
     * Displays the list of tasks to the user.
     */
    public void display() {
        ui.showTasks(this.tasks);
    }

    /**
     * Marks the task at the given index.
     *
     * @param index The index of the arraylist that contains the task.
     * @param storage The storage to update the task marked.
     */
    public void markTask(int index, Storage storage) {
        try {
            Task task = this.tasks.get(index);
            task.markAsDone();
            this.ui.showMarkedTask(task);
            storage.saveTasks(this.tasks);
            assert index >= 0;
        } catch (IndexOutOfBoundsException e) {
            this.ui.showMarkedTask(null);
        }
    }

    /**
     * Adds the task to the task list.
     *
     * @param description The description of the task.
     * @param keyword The command keyword to identify the type of task to be added.
     * @param storage The storage to save the task added.
     * @throws DukeException If the task fails to be added to the list.
     */
    public void addTask(String description, CommandKeyword keyword, Storage storage) throws DukeException {
        Task task;
        switch (keyword) {
        case TODO:
            task = Todo.create(description, false);
            break;
        case DEADLINE:
            task = Deadline.create(description, false);
            break;
        case EVENT:
            task = Event.create(description, false);
            break;
        default:
            // checked for command validity in duke.parser.Parser class, so this should not execute at all
            throw new InvalidCommandException();
        }
        assert task != null : "Task not added successfully but error is not caught";
        tasks.add(task);
        this.ui.showAddTask(task, this.tasks.size());
        storage.saveTasks(this.tasks);
    }

    /**
     * Deletes the task at the given index.
     * @param index The index of the arraylist that contains the task.
     * @param storage The storage to update the task marked.
     */
    public void deleteTask(int index, Storage storage) {
        try {
            Task task = this.tasks.get(index);
            this.tasks.remove(index);
            this.ui.showDeletedTask(task, this.tasks.size());
            storage.saveTasks(this.tasks);
            assert index >= 0;
        } catch (IndexOutOfBoundsException e) {
            this.ui.showDeletedTask(null, -1);
        }
    }

    /**
     * Finds tasks by searching for a keyword.
     *
     * @param keyword The keyword to match to.
     */
    public void find(String keyword) {
        if (keyword.equals("")) {
            this.ui.showNoKeyword();
        } else {
            List<Task> filteredList = this.tasks.stream()
                    .filter(task -> task.toString().contains(keyword)).collect(Collectors.toList());
            this.ui.showFilteredTasks(new ArrayList<>(filteredList));
        }
    }
}
