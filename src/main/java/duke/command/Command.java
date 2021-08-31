package duke.command;

import duke.TaskList;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Duke uses the methods in Command in order to get Corresponding message, read, store and update data.
 */
public class Command {
    private final TaskList taskList;
    private final Response response;
    private final Storage storage;

    /**
     * Constructor of Command class.
     *
     * @param taskList The TaskList stored tasks.
     * @param response The response object created by Duke.
     * @param storage The storage object manipulate storage process of Duke
     */
    public Command(TaskList taskList, Response response, Storage storage) {
        this.taskList = taskList;
        this.response = response;
        this.storage = storage;
    }

    /**
     * Ask storage to load tasks in the txt file.
     */
    public void loadSavedTasks() {
        storage.loadSavedTasks();
    }

    /**
     * Get the welcome message.
     *
     * @return A StringBuilder containing the welcome message
     */
    public StringBuilder welcomeToUser() {
        return response.getWelcomeMessage();
    }

    /**
     * Get the GoodBye message.
     *
     * @return A StringBuilder containing the GoodBye message
     */
    public StringBuilder goodByeToUser() {
        return response.getGoodByeMessage();
    }

    /**
     * Get the Corresponding message with the input String.
     *
     * @param task The input String containing the task user want Duke do.
     * @return A StringBuilder containing the task information.
     */
    public StringBuilder getCorrespondingMessage(String task) {
        try {
            switch (Parser.judgeType(task)) {
            case TODO:
                return addNewTodo(task);
            case EVENT:
                return addNewEvent(task);
            case DEADLINE:
                return addNewDeadline(task);
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new StringBuilder("Cannot understand");
    }

    private StringBuilder addNewTodo(String task) throws Exception {
        Task todoTask = Parser.isValidTodoTask(task);
        taskList.add(todoTask);
        storage.saveNewTask(todoTask);
        return response.getAddNewTaskMessage();
    }

    private StringBuilder addNewDeadline(String task) throws Exception {
        Task deadlineTask = Parser.isValidDeadlineTask(task);
        taskList.add(deadlineTask);
        storage.saveNewTask(deadlineTask);
        return response.getAddNewTaskMessage();
    }

    private StringBuilder addNewEvent(String task) throws Exception {
        Task eventTask = Parser.isValidEventTask(task);
        taskList.add(eventTask);
        storage.saveNewTask(eventTask);
        return response.getAddNewTaskMessage();
    }

    private StringBuilder setTaskDone(String task) throws Exception {
        int itemDone = Parser.findFinishedItem(task);
        taskList.get(itemDone - 1).setToDone();
        storage.modifyTasks();
        return response.getMarkTaskDoneMessage(itemDone);
    }

    private StringBuilder deleteTask(String task) throws Exception {
        int itemDeleted = Parser.findDeleteItem(task);
        Task deletedTask = taskList.remove(itemDeleted - 1);
        storage.modifyTasks();
        return response.getDeleteMessage(deletedTask);
    }

    private StringBuilder findTask(String task) {
        String target = task.substring(5);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task cur = taskList.get(i);
            if (cur.getTaskName().contains(target)) {
                result.add(i);
            }
        }
        return response.getFoundTasks(result);
    }

    private StringBuilder getTasks() {
        return response.getListMessage();
    }
}
