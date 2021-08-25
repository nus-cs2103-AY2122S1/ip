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

import java.io.IOException;
import java.time.LocalDate;

public class Command {

    private final Storage storage;
    private final Ui ui;

    public Command(Storage storage, Ui ui) {
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Adds a deadline to the task list.
     *
     * @param user_command The command inputted from the user
     * @param cmd The type of task added to the task list
     * @param taskList The task list which contains the tasks
     * @throws EmptyDescriptionException
     * @throws IOException
     */
    public void addDeadline(String user_command, String cmd, TaskList taskList)
            throws EmptyDescriptionException, IOException {
        Parser parser = new Parser(user_command);
        String deadlineInfo = parser.getDeadlineInfo();
        LocalDate date = parser.getDeadlineDate();

        taskList.add(new Deadline(deadlineInfo, date));
        storage.addTask(taskList.getLastStatusString());
        ui.showAddition(cmd, user_command);
        ui.showLine();
    }

    /**
     * Adds an event to the task list.
     *
     * @param user_command The command inputted from the user
     * @param cmd The type of task added to the task list
     * @param taskList The task list which contains the tasks
     * @throws EmptyDescriptionException
     * @throws IOException
     */
    public void addEvent(String user_command, String cmd, TaskList taskList)
            throws EmptyDescriptionException, IOException {
        Parser parser = new Parser(user_command);
        String eventInfo = parser.getEventInfo();
        String eventDetails = parser.getEventLocation();

        taskList.add(new Event(eventInfo, eventDetails));
        storage.addTask(taskList.getLastStatusString());
        ui.showAddition(cmd, user_command);
        ui.showLine();
    }

    /**
     * Adds a todo to the task list.
     *
     * @param user_command The command inputted from the user
     * @param cmd The type of task added to the task list
     * @param taskList The task list which contains the tasks
     * @throws EmptyDescriptionException
     * @throws IOException
     */
    public void addTodo(String user_command, String cmd, TaskList taskList)
            throws EmptyDescriptionException, IOException {
        Parser parser = new Parser(user_command);
        String taskInfo = parser.getTodoInfo();

        taskList.add(new Todo(taskInfo));
        storage.addTask(taskList.getLastStatusString());
        ui.showAddition(cmd, user_command);
        ui.showLine();
    }

    /**
     * Logs the goodbye message.
     */
    public void bye() {
        ui.showBye();
    }

    /**
     * Deletes a specified item from the task list.
     *
     * @param user_command The command inputted from the user
     * @param taskList The task list which contains the tasks
     * @throws NotDoneRightException
     * @throws IOException
     */
    public void delete(String user_command, TaskList taskList) throws NotDoneRightException, IOException {
        Parser parser = new Parser(user_command);
        int ref = parser.getSecondInteger(taskList.size()) - 1;

        ui.showRemoval(taskList.get(ref).toString(), taskList.size() - 1);
        taskList.remove(ref);
        storage.removeTask(ref);
        ui.showLine();
    }

    /**
     * Marks a specified item from the task list as done.
     *
     * @param user_command The command inputted from the user
     * @param taskList The task list which contains the tasks
     * @throws NotDoneRightException
     * @throws IOException
     */
    public void done(String user_command, TaskList taskList) throws IOException, NotDoneRightException {
        Parser parser = new Parser(user_command);
        // Throws exception if there is error accessing the integer following "done"
        // Marks the task as done and prints statements as proof
        int ref = parser.getSecondInteger(taskList.size()) - 1;
        Task task = taskList.get(ref);

        if (task.getStatusIcon().equals("X")) {
            System.out.println("You've already done this!");

        } else {
            task.markAsDone();
            storage.updateDone(ref, task.getStatusString());
            ui.showCompletion(taskList.get(ref).toString());
        }

        ui.showLine();
    }

    /**
     * Finds all items from the task list that match a given string.
     *
     * @param user_command The command inputted from the user
     * @param taskList The task list which contains the tasks
     * @throws EmptyDescriptionException
     */
    public void find(String user_command, TaskList taskList) throws EmptyDescriptionException {
        Parser parser = new Parser(user_command);
        String wordSearch = parser.getSecondWord().toLowerCase();
        ui.showSearch(taskList.search(wordSearch));
        ui.showLine();
    }

    /**
     * Lists all the items in the task list.
     *
     * @param taskList The task list which contains the tasks.
     */
    public void list(TaskList taskList) {
        ui.showList(taskList);
        ui.showLine();
    }
}
