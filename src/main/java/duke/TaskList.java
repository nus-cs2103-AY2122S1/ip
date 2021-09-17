package duke;

import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Creates TaskList objects and handles all task list operations.
 */
public class TaskList {

    private static final String DEADLINE_DATETIME_MARKER = "/by ";
    private static final String DEADLINE_DATETIME_MARKER_WITH_SPACE = " /by ";
    private static final String EVENT_DATETIME_MARKER = "/at ";
    private static final String EVENT_DATETIME_MARKER_WITH_SPACE = " /at ";

    private ArrayList<Task> taskList;

    /**
     * Converts the ArrayList of task strings to Task objects stored in the ArrayList of tasks.
     *
     * @param taskListStrings ArrayList of task strings taken from the disk file representing Task objects.
     */
    public TaskList(ArrayList<String> taskListStrings) {
        this.taskList = convertToTasks(taskListStrings);
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Finds tasks that match user's search term.
     *
     * @param searchTerm user wants to find all tasks that have descriptions matching user's search term.
     * @param ui handles interactions with user by printing the appropriate messages.
     * @return all tasks that match user's search term.
     * @throws DukeException if search term is not given alongside the find command.
     * in the following format: find searchterm.
     */
    public String find(String searchTerm, Ui ui) throws DukeException {
        if (searchTerm.isEmpty()) {
            throw new DukeException("Please provide a search term after the find command in the following format:"
                    + " find term");
        }
        try {
            //@@author kaushikkrdy-reused
            //Reused from https://stackoverflow.com/a/46074920
            List<Task> matchingTaskList = this.taskList.stream()
                    .filter(task -> task.toString().contains(searchTerm))
                    .collect(Collectors.toList());
            //@@author
            return ui.showMatchingTasks(new ArrayList<>(matchingTaskList));
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Please provide a search term after the find command in the following format:"
                    + " find term");
        }
    }

    /**
     * Marks a task in the task list done.
     * Updates the task as done in the disk using storage and prints appropriate messages using ui.
     *
     * @param num denotes the position of the task in the task list (if counting from 1).
     * @param storage handles updating the specified task from the disk as done.
     * @param ui handles interactions with user by printing the appropriate messages.
     * @return task that was marked done.
     * @throws DukeException if number given is not within the total number of tasks or done command does not follow
     * the format: done number.
     */
    public String done(String num, Storage storage, Ui ui) throws DukeException {

        try {
            int listIndex = Integer.parseInt(num) - 1;
            this.taskList.get(listIndex).setDone(true);
            Task doneTask = this.taskList.get(listIndex);
            storage.replaceFileLine(doneTask.getFileString(), listIndex);
            return ui.showDoneMessage(doneTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please provide a number after the done command that is within"
                    + " the total number of tasks: "
                    + this.taskList.size());
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! The done command needs a number after it in the following format:"
                    + " done number");
        }
    }

    /**r
     * Deletes a task from the task list.
     * Deletes task from disk using storage and prints appropriate messages using ui.
     *
     * @param num denotes the position of the task in the task list (if counting from 1).
     * @param storage handles deleting the specified task from the disk.
     * @param ui handles interactions with user by printing the appropriate messages.
     * @return task that was deleted and the number of remaining tasks in the task list.
     * @throws DukeException if number given is not within the total number of tasks or delete command.
     * does not follow the format: delete number.
     */
    public String delete(String num, Storage storage, Ui ui) throws DukeException {
        try {
            int listIndex = Integer.parseInt(num) - 1;
            Task deletedTask = this.taskList.remove(listIndex);
            storage.deleteFileLine(listIndex);
            return String.format("%s %s", ui.showDeletedMessage(deletedTask),
                    ui.showTaskListSize(this.taskList.size()));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please provide a number after the delete command that is within"
                    + " the total number of tasks: "
                    + this.taskList.size());
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! The delete command needs a number after it in the following format:"
                    + " delete number");
        }
    }

    /**
     * Creates a Todo task with the appropriate description.
     * Stores task in disk using storage and prints appropriate messages using ui.
     *
     * @param description describes the nature of the Deadline task.
     * @param storage handles storing the new task in the disk.
     * @param ui handles interactions with user by printing the appropriate messages.
     * @return task that was created and the new size of the task list
     */
    public String createTodo(String description, Storage storage, Ui ui) {
        Task taskToAdd = new Todo(description);
        this.taskList.add(taskToAdd);
        storage.appendToFile(taskToAdd.getFileString());
        return String.format("%s %s", ui.showAddMessage(taskToAdd), ui.showTaskListSize(this.taskList.size()));
    }

    /**
     * Creates a Deadline task with the appropriate description.
     * Stores task in disk using storage and prints appropriate messages using ui.
     *
     * @param description describes the nature of the Deadline task.
     * @param storage handles storing the new task in the disk.
     * @param ui handles interactions with user by printing the appropriate messages.
     * @return deadline that was created and the new size of the task list
     * @throws DukeException if description contains incorrectly formatted date/time or does not follow the format:
     * deadline task /by datetime
     */
    public String createDeadline(String description, Storage storage, Ui ui) throws DukeException {
        try {
            String newDescription = description.substring(0, description.indexOf(DEADLINE_DATETIME_MARKER_WITH_SPACE));
            String by = description.substring(description.indexOf(DEADLINE_DATETIME_MARKER)
                    + DEADLINE_DATETIME_MARKER.length());
            Task addedTask = new Deadline(newDescription, by);
            this.taskList.add(addedTask);
            storage.appendToFile(addedTask.getFileString());
            return String.format("%s %s", ui.showAddMessage(addedTask), ui.showTaskListSize(this.taskList.size()));
        } catch (DateTimeParseException e) {
            throw new DukeException("Please write your date and time in the following format: "
                    + "D/MM/YYYY HHMM");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Please write your deadline command in the following format: "
                    + "deadline task /by datetime");
        }
    }

    /**
     * Creates an Event task with the appropriate description.
     * Stores task in disk using storage and prints appropriate messages using ui.
     *
     * @param description describes the nature of the Event task.
     * @param storage handles storing the new task in the disk.
     * @param ui handles interactions with user by printing the appropriate messages.
     * @return event that was created and the new size of the task list
     * @throws DukeException if description contains incorrectly formatted date/time or does not follow the format:
     * event task /at datetime
     */
    public String createEvent(String description, Storage storage, Ui ui) throws DukeException {
        try {
            String newDescription = description.substring(0, description.indexOf(EVENT_DATETIME_MARKER_WITH_SPACE));
            String at = description.substring(description.indexOf(EVENT_DATETIME_MARKER)
                    + EVENT_DATETIME_MARKER.length());
            Task addedTask = new Event(newDescription, at);
            this.taskList.add(addedTask);
            storage.appendToFile(addedTask.getFileString());
            return String.format("%s %s", ui.showAddMessage(addedTask), ui.showTaskListSize(this.taskList.size()));
        } catch (DateTimeParseException e) {
            throw new DukeException("Please write your date and time in the following format: "
                    + "D/MM/YYYY HHMM");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Please write your event command in the following format: "
                    + "event task /at datetime");
        }
    }

    /**
     * Converts ArrayList of strings representing Task objects to ArrayList of Task objects.
     * String will be in the form of Task Type | 1 or 0 | Task description | date and time.
     *
     * @param taskListStrings ArrayList of strings representing Task objects.
     * @return ArrayList of Task objects
     */
    private ArrayList<Task> convertToTasks(ArrayList<String> taskListStrings) {
        ArrayList<Task> tasks = new ArrayList<>();

        for (String ts : taskListStrings) {
            String[] tsData = ts.split(" \\| ");
            String taskType = tsData[0];

            assert taskType.equals("T") || taskType.equals("D") || taskType.equals("E") : "Invalid task type!";

            switch (taskType) {
            case "T":
                tasks.add(new Todo(tsData[1], tsData[2]));
                break;
            case "D":
                tasks.add(new Deadline(tsData[1], tsData[2], tsData[3]));
                break;
            case "E":
                tasks.add(new Event(tsData[1], tsData[2], tsData[3]));
                break;
            default:
                break;
            }

        }

        return tasks;
    }
}
