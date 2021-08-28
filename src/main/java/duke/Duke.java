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

    private boolean isLive = true;
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
        ui.greet();
    }

    /**
     * Shows if the duke is still active.
     *
     * @return whether the duke is still active.
     */
    public boolean isLive() {
        return isLive;
    }

    /**
     * Processes the command input by user.
     *
     * @param command command input of the user.
     * @throws DukeException exceptions when processing the command
     */
    public void processCommand(String command) throws DukeException {
        ActionType actionType = parser.parseActionType(command);
        switch (actionType) {
        case LIST:
            ui.displayTasks();
            break;
        case TODO: {
            String task = parser.parseTask(command);
            ToDo toDo = taskList.createTodo(task);
            taskList.addTask(toDo);
            ui.showAddedTask(toDo);
            storage.saveProgress();
            break;
        }
        case DEADLINE: {
            String task = parser.parseTask(command);
            DeadLine ddl = taskList.createDeadLine(task);
            taskList.addTask(ddl);
            ui.showAddedTask(ddl);
            storage.saveProgress();
            break;
        }
        case EVENT: {
            String task = parser.parseTask(command);
            Event event = taskList.createEvent(task);
            taskList.addTask(event);
            ui.showAddedTask(event);
            storage.saveProgress();
            break;
        }
        case DONE: {
            int taskIdx = parser.getTaskIdx(command, taskList.size());
            Task toBeMarked = taskList.get(taskIdx - 1);
            taskList.markTaskAsDone(taskIdx - 1);
            ui.showMarkedAsDone(toBeMarked);
            storage.saveProgress();
            break;
        }
        case DELETE: {
            int taskIdx = parser.getTaskIdx(command, taskList.size());
            Task toBeDeleted = taskList.get(taskIdx - 1);
            taskList.deleteTask(toBeDeleted);
            ui.showDeletedTask(toBeDeleted);
            storage.saveProgress();
            break;
        }
        case FIND: {
            String keyword = parser.parseTask(command);
            List<Task> matchedTasks = taskList.findTask(keyword);
            ui.showFoundTask(matchedTasks);
            break;
        }
        case BYE: {
            storage.saveProgress();
            ui.bye();
            this.isLive = false;
            break;
        }
        default:
            throw new UnrecognizableCommandException();
        }
    }
}
