package duke.command;

import duke.TaskList;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class Command {
    private TaskList taskList;
    private Response response;
    private Storage storage;

    public Command(TaskList taskList, Response response, Storage storage) {
        this.taskList = taskList;
        this.response = response;
        this.storage = storage;
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
        taskList.get(itemDone - 1).finished();
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

    public void loadSavedTasks() {
        storage.loadSavedTasks();
    }

    public StringBuilder welcomeUser() {
        return response.getWelcomeMessage();
    }

    public StringBuilder goodByeUser() {
        return response.getGoodByeMessage();
    }

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
                return goodByeUser();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new StringBuilder("Cannot understand");
    }
}
