/**
 * This class performs the commands in Duke.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke.command;

import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.NotDoneRightException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.ui.Ui;
import java.time.LocalDate;
import java.io.IOException;

public class Command {

    private final Storage storage;
    private final Ui ui;

    /**
     * Constructs a Command object.
     *
     * @param storage The Storage object handling the information.
     * @param ui The Ui object handling user interactions.
     */
    public Command(Storage storage, Ui ui) {
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Adds a deadline to the task list.
     *
     * @param userCommand The command inputted from the user
     * @param cmd The type of task added to the task list
     * @param taskList The task list which contains the tasks
     * @throws EmptyDescriptionException
     * @throws IOException
     */
    public String addDeadline(String userCommand, String cmd, TaskList taskList)
            throws EmptyDescriptionException, IOException {
        Parser parser = new Parser(userCommand);
        String deadlineInfo = parser.getDeadlineInfo();
        LocalDate date = parser.getDeadlineDate();

        taskList.add(new Deadline(deadlineInfo, date));
        storage.addTask(taskList.getLastStatusString());
        return ui.showAddition(cmd, userCommand);
    }

    /**
     * Adds an event to the task list.
     *
     * @param userCommand The command inputted from the user
     * @param cmd The type of task added to the task list
     * @param taskList The task list which contains the tasks
     * @throws EmptyDescriptionException
     * @throws IOException
     */
    public String addEvent(String userCommand, String cmd, TaskList taskList)
            throws EmptyDescriptionException, IOException {
        Parser parser = new Parser(userCommand);
        String eventInfo = parser.getEventInfo();
        String eventDetails = parser.getEventLocation();

        taskList.add(new Event(eventInfo, eventDetails));
        storage.addTask(taskList.getLastStatusString());
        return ui.showAddition(cmd, userCommand);
    }

    /**
     * Adds a todo to the task list.
     *
     * @param userCommand The command inputted from the user
     * @param cmd The type of task added to the task list
     * @param taskList The task list which contains the tasks
     * @throws EmptyDescriptionException
     * @throws IOException
     */
    public String addTodo(String userCommand, String cmd, TaskList taskList)
            throws EmptyDescriptionException, IOException {
        Parser parser = new Parser(userCommand);
        String taskInfo = parser.getTodoInfo();

        taskList.add(new Todo(taskInfo));
        storage.addTask(taskList.getLastStatusString());
        return ui.showAddition(cmd, userCommand);
    }

    /**
     * Logs the goodbye message.
     */
    public String bye() {
        return ui.showBye();
    }

    /**
     * Deletes a specified item from the task list.
     *
     * @param userCommand The command inputted from the user
     * @param taskList The task list which contains the tasks
     * @throws NotDoneRightException
     * @throws IOException
     */
    public String delete(String userCommand, TaskList taskList) throws NotDoneRightException, IOException {
        Parser parser = new Parser(userCommand);
        int ref = parser.getSecondInteger(taskList.size()) - 1;
        String taskRemoved = taskList.get(ref).toString();
        taskList.remove(ref);
        storage.removeTask(ref);
        return ui.showRemoval(taskRemoved, taskList.size());
    }

    /**
     * Marks a specified item from the task list as done.
     *
     * @param userCommand The command inputted from the user
     * @param taskList The task list which contains the tasks
     * @throws NotDoneRightException
     * @throws IOException
     */
    public String done(String userCommand, TaskList taskList) throws IOException, NotDoneRightException {
        Parser parser = new Parser(userCommand);
        // Throws exception if there is error accessing the integer following "done"
        // Marks the task as done and prints statements as proof
        int ref = parser.getSecondInteger(taskList.size()) - 1;
        Task task = taskList.get(ref);

        if (task.getStatusIcon().equals("X")) {
            return "You've already done this!";

        } else {
            task.markAsDone();
            storage.updateDone(ref, task.getStatusString());
            return ui.showCompletion(taskList.get(ref).toString());
        }
    }

    /**
     * Finds all items from the task list that match a given string.
     *
     * @param userCommand The command inputted from the user
     * @param taskList The task list which contains the tasks
     * @throws EmptyDescriptionException
     */
    public String find(String userCommand, TaskList taskList) throws EmptyDescriptionException {
        Parser parser = new Parser(userCommand);
        String wordSearch = parser.getSecondWord().toLowerCase();
        return ui.showSearch(taskList.search(wordSearch));
    }

    /**
     * Lists all the items in the task list.
     *
     * @param taskList The task list which contains the tasks.
     */
    public String list(TaskList taskList) {
        return ui.showList(taskList);
    }
}
