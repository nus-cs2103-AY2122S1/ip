package duke.task;

import duke.util.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidDateTimeException;
import duke.util.Parser;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Encapsulates task list and its operations.
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private Ui ui = new Ui();

    /**
     * Constructor for loading old task list.
     *
     * @param taskList List of tasks entered previously
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructor for new task list.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Returns number of tasks in list.
     *
     * @return length of list
     */
    public int listLength() {
        return taskList.size();
    }

    /**
     * Returns string showing task added.
     *
     * @param task Task to be added to list
     * @return String representation of task added
     */
    public String addTaskToList(Task task) {
        taskList.add(task);
        return ui.showTaskAdded(task, taskList.size());
    }

    /**
     * Prints all the tasks in list.
     */
    public void printTasksInList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("\t%s."+ taskList.get(i).toString() + "%n", i + 1);
        }
    }

    /**
     * Returns string showing task deleted.
     *
     * @param deleteNumber Index of task in list
     * @return String representation of task deleted
     */
    public String deleteFromList(int deleteNumber) {
        Task task = taskList.get(deleteNumber);
        taskList.remove(deleteNumber);
        return ui.showTaskDeleted(task, taskList.size());
    }


    /**
     * Returns string of task set as done.
     *
     * @param doneNumber Index of task in list
     * @return String representation of task set as done
     */
    public String setTaskAsDone(int doneNumber) {
        Task task = taskList.get(doneNumber);
        task.setDone();
        return ui.showTaskDone(task);
    }


    /**
     * Returns string of deadline added to list.
     *
     * @param description Details about deadline
     * @param by Date of completion of deadline
     * @return String representation of deadline added
     * @throws DukeException If date entered is not of correct format
     */
    public String addDeadlineToList(String description, String by) throws DukeException {
        try {
            LocalDate date = Parser.parseDate(by);
            Deadline deadline = new Deadline(description, date);
            return addTaskToList(deadline);
        } catch (DateTimeParseException | ParseException e) {
            throw new InvalidDateTimeException();
        }
    }

    /**
     * Returns string of event added to list.
     *
     * @param description Details about event
     * @param at Date of event
     * @return String representation of event added
     * @throws DukeException If date entered is not of correct format
     */
    public String addEventToList(String description, String at) throws DukeException {
        try {
            LocalDate date = Parser.parseDate(at);
            Event event = new Event(description, date);
            return addTaskToList(event);
        } catch (DateTimeParseException | ParseException e) {
            throw new InvalidDateTimeException();
        }
    }

    /**
     * Returns string of todo added to list.
     *
     * @param description Details of todo
     * @return String representation of todo added
     */
    public String addTodoToList(String description) {
        Todo todo = new Todo(description);
        return addTaskToList(todo);
    }

    /**
     * Saves tasks in list to hard disk.
     *
     * @param writer Filewriter that write tasks to hard disk
     * @throws IOException If file is not found
     */
    public void saveTasksInStorage(FileWriter writer) throws IOException {
        for (Task tasks : taskList) {
            writer.write(tasks.saveTaskFormat() + System.lineSeparator());
        }
    }

    /**
     * Returns string of tasks in list.
     * @return String representation of tasks in list
     */
    public String getTasks() {
        String tasks = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            tasks += String.format("\t%s.%s\n", i + 1, taskList.get(i).toString());
        }
        return tasks;
    }

}
