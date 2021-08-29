package duke;

import java.util.List;

/**
 * Specifies the type of action to be taken by the duke.
 */
enum ActionType {
    LIST, TODO, DEADLINE, EVENT, DONE, DELETE, BYE, FIND, UNRECOGNIZED
}

/**
 * A duke that creates todo list.
 */
public class Duke {

    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructor for a duke.
     */
    public Duke() {
        parser = new Parser();
        storage = new Storage();
        taskList = new TaskList(storage.loadFromDisk());
        ui = new Ui(this.taskList);
    }


    /**
     * Gets the response from the duke given a input.
     *
     * @param input input command
     * @return duke's response
     */
    public String getResponse(String input) {
        try {
            return processCommand(input);
        } catch (DukeException dukeException) {
            return dukeException.toString();
        }
    }

    /**
     * Processes the command input by user.
     *
     * @param command command input of the user.
     * @return duke's response
     * @throws DukeException exceptions when processing the command
     */
    public String processCommand(String command) throws DukeException {
        ActionType actionType = parser.parseActionType(command);
        switch (actionType) {
        case LIST:
            return ui.displayTasks();
        case TODO: {
            String task = parser.parseTask(command);
            ToDo toDo = taskList.createTodo(task);
            taskList.addTask(toDo);
            storage.saveProgress();
            return ui.showAddedTask(toDo);
        }
        case DEADLINE: {
            String task = parser.parseTask(command);
            DeadLine ddl = taskList.createDeadLine(task);
            taskList.addTask(ddl);
            storage.saveProgress();
            return ui.showAddedTask(ddl);
        }
        case EVENT: {
            String task = parser.parseTask(command);
            Event event = taskList.createEvent(task);
            taskList.addTask(event);
            storage.saveProgress();
            return ui.showAddedTask(event);
        }
        case DONE: {
            int taskIdx = parser.getTaskIdx(command, taskList.size());
            Task toBeMarked = taskList.get(taskIdx - 1);
            taskList.markTaskAsDone(taskIdx - 1);
            storage.saveProgress();
            return ui.showMarkedAsDone(toBeMarked);
        }
        case DELETE: {
            int taskIdx = parser.getTaskIdx(command, taskList.size());
            Task toBeDeleted = taskList.get(taskIdx - 1);
            taskList.deleteTask(toBeDeleted);
            storage.saveProgress();
            return ui.showDeletedTask(toBeDeleted);
        }
        case FIND: {
            String keyword = parser.parseTask(command);
            List<Task> matchedTasks = taskList.findTask(keyword);
            return ui.showFoundTask(matchedTasks);
        }
        case BYE: {
            storage.saveProgress();
            return ui.bye();
        }
        default:
            throw new UnrecognizableCommandException();
        }
    }
}
