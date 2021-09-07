package duke;

import java.util.ArrayList;

/**
 * Wrapper for the Tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Creates a TaskList object.
     *
     * @param storage Provides access to hard drive.
     * @param ui Allows for interaction with user.
     */
    public TaskList(Storage storage, Ui ui) {
        this.tasks = new ArrayList<>(100);
        this.storage = storage;
        this.ui = ui;

        this.storage.initialiseTasks(tasks);
    }

    public int sizeOf() {
        return tasks.size();
    }

    /**
     * Displays contents of list to screen.
     */
    public String displayList() {
        String out = "";

        if (tasks.size() == 0) {
            out += "You currently have no tasks.";
            return out;
        }

        out += "Here are the tasks in your list: \n";
        for (int i = 0; i < tasks.size(); i++) {
            out += Ui.INDENT + (i + 1) + "." + tasks.get(i).toString() + "\n";
        }

        return out;
    }

    /**
     * Marks task at given index as done.
     *
     * @param index Index of task.
     */
    public String markTaskDone(int index) {
        String out = "";
        try {
            Task finishedTask = tasks.get(index);
            finishedTask.markAsDone();
            storage.updateFile(tasks);
            out += "Nice! I've marked the following task as done: \n"
                    + Ui.INDENT + finishedTask.toString();
            return out;
        } catch (IndexOutOfBoundsException e) {
            return "Task could not be marked as done.\nPlease input valid task index.";
        }
    }

    /**
     * Deletes task at given index.
     *
     * @param index Index of task.
     */
    public String deleteTask(int index) {
        String out = "";
        try {
            Task deletedTask = tasks.get(index);
            tasks.remove(index);
            storage.updateFile(tasks);
            out += "Ok, I've deleted the following task: \n"
                    + Ui.INDENT + deletedTask.toString();
            return out;
        } catch (IndexOutOfBoundsException e) {
            return "Task could not be deleted.\nPlease input valid task index.";
        }
    }

    /**
     * Adds a Todo to the list of tasks.
     *
     * @param desc Description of Todo.
     */
    public String addTodo(String desc) {
        String out = "";
        try {
            if (desc.isEmpty()) {
                throw new DukeException();
            }
            Task temp = new Todo(desc);
            tasks.add(temp);
            storage.writeTask(temp);
            out += "Ok! A new task has been added:\n"
                    + Ui.INDENT + temp.toString() + "\n"
                    + "You now have " + tasks.size() + " task(s) in total.";
            return out;
        } catch (DukeException de) {
            return "Sorry, the Duke.Todo task could not be added.\nPlease include the description for this task.";
        }
    }

    /**
     * Adds Deadline to list of tasks.
     *
     * @param req Array of Strings containing the description and the deadline.
     */
    public String addDeadline(String[] req) {
        String out = "";
        try {
            if (req.length != 2) {
                throw new DukeException();
            }
            String desc = req[0];
            String deadline = req[1];
            Task temp = new Deadline(desc, deadline);
            tasks.add(temp);
            storage.writeTask(temp);
            out += "Ok! A new task has been added:\n"
                    + Ui.INDENT + temp.toString() + "\n"
                    + "You now have " + tasks.size() + " task(s) in total.";
            return out;
        } catch (DukeException de) {
            return "Sorry, the Duke.Deadline task could not be added.\n"
                    + "Please include the description and deadline for this task with /by.\n" +
                    "(Date and time format: dd/MM/yyyy HHmm)";
        }
    }

    /**
     * Adds Event to list of tasks.
     *
     * @param req Array of Strings containing the description and the time frame.
     */
    public String addEvent(String[] req) {
        String out = "";
        try {
            if (req.length != 2) {
                throw new DukeException("oops");
            }
            String desc = req[0];
            String time = req[1];
            Task temp = new Event(desc, time);
            tasks.add(temp);
            storage.writeTask(temp);
            out += "Ok! A new task has been added:\n"
                    + Ui.INDENT + temp.toString() + "\n"
                    + "You now have " + tasks.size() + " task(s) in total.";
            return out;
        } catch (DukeException de) {
            return "Sorry, the Duke.Event task could not be added.\n"
                    + "Please include the description and time of this task with /at.";
        }
    }

    /**
     * Displays tasks that contain the given key to screen.
     *
     * @param key THe given String to match.
     */
    public String findTask(String key) {
        String out = "";
        if (key.isEmpty()) {
            return "Please include a search word.";
        }

        ArrayList<Task> temp = new ArrayList<>();
        for (Task t : tasks) {
            if (t.toString().contains(key)) {
                temp.add(t);
            }
        }

        if (temp.size() == 0) {
            return "No tasks match this search, please try again.";
        }

        out += "Here are the tasks that match your search: \n";
        for (int i = 0; i < temp.size(); i++) {
            out += Ui.INDENT + (i + 1) + "." + temp.get(i).toString() + "\n";
        }

        return out;
    }

    /**
     * Deletes all tasks in list and hard drive.
     */
    public String clearTasks() {
        tasks = new ArrayList<>(100);
        storage.updateFile(tasks);
        ui.toScreen("All tasks have been deleted.");
        return "All tasks have been deleted.";
    }
}
