/**
 * This class performs the commands in Duke.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke.command;

import java.io.IOException;
import java.time.LocalDate;

import duke.exceptions.DateNotAcceptedException;
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
     * @param taskList The task list which contains the tasks
     * @throws EmptyDescriptionException
     * @throws IOException
     */
    public String addDeadline(String userCommand, TaskList taskList)
            throws EmptyDescriptionException, IOException, DateNotAcceptedException {
        Parser parser = new Parser(userCommand);
        String deadlineInfo = parser.getDeadlineInfo();
        LocalDate date = parser.getDeadlineDate();
        Deadline deadline = new Deadline(deadlineInfo, date);

        taskList.add(deadline);
        storage.addTask(taskList.getLastStatusString());
        return ui.showAddition(deadline.toString(), taskList.size());
    }

    /**
     * Adds an event to the task list.
     *
     * @param userCommand The command inputted from the user
     * @param taskList The task list which contains the tasks
     * @throws EmptyDescriptionException
     * @throws IOException
     */
    public String addEvent(String userCommand, TaskList taskList)
            throws EmptyDescriptionException, IOException {
        Parser parser = new Parser(userCommand);
        String eventInfo = parser.getEventInfo();
        String eventDetails = parser.getEventLocation();
        Event event = new Event(eventInfo, eventDetails);

        taskList.add(event);
        storage.addTask(taskList.getLastStatusString());
        return ui.showAddition(event.toString(), taskList.size());
    }

    /**
     * Adds a todo to the task list.
     *
     * @param userCommand The command inputted from the user
     * @param taskList The task list which contains the tasks
     * @throws EmptyDescriptionException
     * @throws IOException
     */
    public String addTodo(String userCommand, TaskList taskList)
            throws EmptyDescriptionException, IOException {
        Parser parser = new Parser(userCommand);
        String taskInfo = parser.getTodoInfo();
        Todo todo = new Todo(taskInfo);

        taskList.add(todo);
        storage.addTask(taskList.getLastStatusString());
        return ui.showAddition(todo.toString(), taskList.size());
    }

    /**
     * Logs the goodbye message.
     *
     * @return The bot's output for the bye command.
     */
    public String bye() {
        return ui.showBye();
    }

    /**
     * Deletes a specified item from the task list.
     *
     * @param userCommand The command inputted from the user
     * @param taskList The task list which contains the tasks
     * @return The bot's output for the delete command.
     * @throws NotDoneRightException If there are errors processing the "done" command.
     * @throws IOException If there are errors processing the file.
     */
    public String delete(String userCommand, TaskList taskList) throws NotDoneRightException, IOException {
        Parser parser = new Parser(userCommand);
        int ref = parser.getSecondInteger(taskList.size()) - 1;
        String taskRemoved = taskList.get(ref).toString();
        taskList.remove(ref);
        storage.removeTask(ref);
        return ui.showDeletion(taskRemoved, taskList.size());
    }

    /**
     * Marks a specified item from the task list as done.
     *
     * @param userCommand The command inputted from the user
     * @param taskList The task list which contains the tasks
     * @return The bot's output for the done command.
     * @throws NotDoneRightException If there are errors processing the "done" command.
     * @throws IOException If there are errors processing the file.
     */
    public String done(String userCommand, TaskList taskList) throws IOException, NotDoneRightException {
        Parser parser = new Parser(userCommand);
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
     * @return The bot's output for the find command.
     * @throws EmptyDescriptionException If the user input is missing extra information after the command.
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
     * @return The bot's output for the list command.
     */
    public String list(TaskList taskList) {
        return ui.showList(taskList);
    }

    /**
     * Finds all items from the task list that match a given string.
     *
     * @param userCommand The command inputted from the user
     * @param taskList The task list which contains the tasks
     * @return The bot's output for the tag command.
     * @throws EmptyDescriptionException If the user input is missing extra information after the command.
     * @throws NotDoneRightException If there are errors processing the "done" command.
     */
    public String tag(String userCommand, TaskList taskList) throws EmptyDescriptionException, NotDoneRightException,
            IOException {
        Parser parser = new Parser(userCommand);
        int ref = parser.getSecondInteger(taskList.size()) - 1;
        String tagInfo = parser.getTagInfo();
        taskList.get(ref).addTag(tagInfo);
        storage.addTag(ref, taskList.get(ref).getStatusString());

        return ui.showTag(tagInfo, taskList.get(ref));
    }
}
