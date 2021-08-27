package duke;

import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Creates TaskList objects and handles all task list operations.
 */
public class TaskList {

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
     * @throws DukeException if search term is not given alongside the find command in the following format: find searchterm.
     */
    public void find(String searchTerm, Ui ui) throws DukeException {
        if (searchTerm.isEmpty()) {
            throw new DukeException("Please provide a search term after the find command in the following format: find searchterm");
        }
        try {
            List<Task> matchingTaskList = this.taskList.stream()
                    .filter(task -> task.toString().contains(searchTerm))
                    .collect(Collectors.toList());
            ui.showMatchingTasks(new ArrayList<Task>(matchingTaskList));
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Please provide a search term after the find command in the following format: find searchterm");
        }
    }

    /**
     * Marks a task in the task list done.
     * Updates the task as done in the disk using storage and prints appropriate messages using ui.
     *
     * @param num denotes the position of the task in the task list (if counting from 1).
     * @param storage handles updating the specified task from the disk as done.
     * @param ui handles interactions with user by printing the appropriate messages.
     * @throws DukeException if number given is not within the total number of tasks or done command does not follow the format: done number.
     */
    public void done(String num, Storage storage, Ui ui) throws DukeException {

        try {
            int listNum = Integer.parseInt(num);
            this.taskList.get(listNum - 1).setIsDone(true);
            Task doneTask = this.taskList.get(listNum - 1);
            storage.replaceFileLine(doneTask.getFileString(), listNum - 1);
            ui.printDoneMessage(doneTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please provide a number after the done command that is within the total number of tasks: "
                    + this.taskList.size());
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! The done command needs a number after it in the following format:"
                    + " done number");
        }
    }

    /**
     * Deletes a task from the task list.
     * Deletes task from disk using storage and prints appropriate messages using ui.
     *
     * @param num denotes the position of the task in the task list (if counting from 1).
     * @param storage handles deleting the specified task from the disk.
     * @param ui handles interactions with user by printing the appropriate messages.
     * @throws DukeException if number given is not within the total number of tasks or delete command
     * does not follow the format: delete number.
     */
    public void delete(String num, Storage storage, Ui ui) throws DukeException {
        try {
            int listNum = Integer.parseInt(num);
            Task deletedTask = this.taskList.remove(listNum - 1);
            storage.deleteFileLine(listNum - 1);
            ui.printDeleteMessage(deletedTask);
            ui.printTaskListSize(this.taskList.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please provide a number after the delete command that is within the total number of tasks: "
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
     */
    public void createTodo(String description, Storage storage, Ui ui) {
        Task addedTask = new Todo(description);
        this.taskList.add(addedTask);
        storage.appendToFile(addedTask.getFileString());
        ui.printAddMessage(addedTask);
        ui.printTaskListSize(this.taskList.size());
    }

    /**
     * Creates a Deadline task with the appropriate description.
     * Stores task in disk using storage and prints appropriate messages using ui.
     *
     * @param description describes the nature of the Deadline task.
     * @param storage handles storing the new task in the disk.
     * @param ui handles interactions with user by printing the appropriate messages.
     * @throws DukeException if description contains incorrectly formatted date/time or does not follow the format:
     * deadline task /by datetime
     */
    public void createDeadline(String description, Storage storage, Ui ui) throws DukeException {
        try {
            String newDescription = description.substring(0, description.indexOf(" /by "));
            String by = description.substring(description.indexOf("/by ") + 4);
            Task addedTask = new Deadline(newDescription, by);
            this.taskList.add(addedTask);
            storage.appendToFile(addedTask.getFileString());
            ui.printAddMessage(addedTask);
            ui.printTaskListSize(this.taskList.size());
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
     * @throws DukeException if description contains incorrectly formatted date/time or does not follow the format:
     * event task /at datetime
     */
    public void createEvent(String description, Storage storage, Ui ui) throws DukeException {
        try {
            String newDescription = description.substring(0, description.indexOf(" /at "));
            String at = description.substring(description.indexOf("/at ") + 4);
            Task addedTask = new Event(newDescription, at);
            this.taskList.add(addedTask);
            storage.appendToFile(addedTask.getFileString());
            ui.printAddMessage(addedTask);
            ui.printTaskListSize(this.taskList.size());
        } catch (DateTimeParseException e) {
            throw new DukeException("Please write your date and time in the following format: " +
                    "D/MM/YYYY HHMM");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Please write your event command in the following format: " +
                    "event task /at datetime");
        }
    }

    /**
     * Converts ArrayList of strings representing Task objects to ArrayList of Task objects.
     *
     * @param taskListStrings ArrayList of strings representing Task objects.
     * @return ArrayList of Task objects
     */
    private ArrayList<Task> convertToTasks(ArrayList<String> taskListStrings) {
        ArrayList<Task> tasks = new ArrayList<>();

        for (String ts : taskListStrings) {
            String[] tsData = ts.split(" \\| ");
            String taskType = tsData[0];

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
            }
        }

        return tasks;
    }
}
