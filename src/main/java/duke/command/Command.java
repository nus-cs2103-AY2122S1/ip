package duke.command;

import duke.exception.DukeException;
import duke.exception.DuplicateException;
import duke.exception.IndexOutOfBoundException;
import duke.exception.InvalidTaskException;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Duke uses the methods in Command in order to get Corresponding message, read, store and update data.
 */
public class Command {
    private final TaskList taskList;
    private final Response response;
    private final Storage storage;
    private static final StringBuilder UNKNOWN_MESSAGE_RESPONSE =
            new StringBuilder("Sorry, I don't understand what do you want me to do :( Can you give" +
                    "me a more specific instruction?");

    /**
     * Constructor of Command class.
     *
     * @param taskList The TaskList stored tasks.
     * @param response The response object created by Duke.
     * @param storage The storage object manipulate storage process of Duke.
     */
    public Command(TaskList taskList, Response response, Storage storage) {
        this.taskList = taskList;
        this.response = response;
        this.storage = storage;
    }

    /**
     * Asks storage to load tasks in the txt file.
     *
     * @throws DuplicateException Throws when there are duplicate tasks detected.
     * @throws InvalidTaskException Throws when the task format is invalid.
     * @throws IOException Throws when there is something wrong during the IO process.
     */
    public void loadSavedTasks() throws DuplicateException, InvalidTaskException, IOException {
        storage.loadSavedTasks();
    }

    /**
     * Gets the welcome message.
     *
     * @return A StringBuilder containing the welcome message.
     */
    public StringBuilder welcomeToUser() {
        return response.getWelcomeMessage();
    }

    /**
     * Gets the GoodBye message.
     *
     * @return A StringBuilder containing the GoodBye message.
     */
    public StringBuilder goodByeToUser() {
        return response.getGoodByeMessage();
    }

    /**
     * Gets the Corresponding message with the input String.
     *
     * @param task The input String containing the task user want Duke do.
     * @return A StringBuilder containing the task information.
     */
    public StringBuilder getCorrespondingMessage(String task) {
        try {
            switch (Parser.judgeType(task)) {
            case TODO:
                return addNewTodoTask(task);
            case EVENT:
                return addNewEventTask(task);
            case DEADLINE:
                return addNewDeadlineTask(task);
            case DONE:
                return setTaskDone(task);
            case LIST:
                return getTasks();
            case DELETE:
                return deleteTask(task);
            case FIND:
                return findTask(task);
            case BYE:
                return goodByeToUser();
            case HELP:
                return getHelpMessage();
            default:
                return UNKNOWN_MESSAGE_RESPONSE;
            }
        } catch (DukeException dukeException) {
            return new StringBuilder(dukeException.getMessage());
        } catch (IOException ioException) {
            return new StringBuilder("Sorry, something goes wrong with the IO process.");
        }
    }

    private StringBuilder addNewTodoTask(String task) throws DuplicateException, InvalidTaskException, IOException {
        Task todoTask = Parser.testTodoValidity(task);
        taskList.addTask(todoTask);
        storage.saveNewTask(todoTask);
        return response.getAddNewTaskMessage();
    }

    private StringBuilder addNewDeadlineTask(String task) throws DuplicateException, InvalidTaskException, IOException {
        Task deadlineTask = Parser.testDeadlineValidity(task);
        taskList.addTask(deadlineTask);
        storage.saveNewTask(deadlineTask);
        return response.getAddNewTaskMessage();
    }

    private StringBuilder addNewEventTask(String task) throws DuplicateException, InvalidTaskException, IOException {
        Task eventTask = Parser.testEventValidity(task);
        taskList.addTask(eventTask);
        storage.saveNewTask(eventTask);
        return response.getAddNewTaskMessage();
    }

    private StringBuilder setTaskDone(String task) throws DukeException, IOException {
        int itemDone = Parser.findFinishedItem(task);
        if (itemDone > taskList.size()) {
            throw new IndexOutOfBoundException("mark item done", taskList.size());
        }
        taskList.getTask(itemDone - 1).setToDone();
        storage.modifyTasks();
        return response.getMarkTaskDoneMessage(itemDone);
    }

    private StringBuilder deleteTask(String task) throws DukeException, IOException {
        int itemDeleted = Parser.findDeleteItem(task);
        if (itemDeleted > taskList.size()) {
            throw new IndexOutOfBoundException("delete item", taskList.size());
        }
        Task deletedTask = taskList.removeTask(itemDeleted - 1);
        storage.modifyTasks();
        return response.getDeleteMessage(deletedTask);
    }

    private StringBuilder findTask(String task) {
        String target = task.substring(5);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.getTask(i);
            if (currentTask.getTaskName().contains(target)) {
                result.add(i);
            }
        }
        return response.getFoundTasks(result);
    }

    private StringBuilder getTasks() {
        return response.getListMessage();
    }

    private StringBuilder getHelpMessage() {
        return response.getHelpMessage();
    }
}
