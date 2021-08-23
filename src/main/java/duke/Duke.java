package duke;

/**
 * Specifies the type of action to be taken by the duke.
 */
enum ActionType {
    LIST, TODO, DEADLINE, EVENT, DONE, DELETE, BYE, UNRECOGNIZED
}

/**
 * A duke that creates todo list.
 */
public class Duke {

    private boolean isLive = true;
    private final Storage STORAGE;
    private final TaskList TASKLIST;
    private final Ui UI;
    private final Parser PARSER;

    /**
     * Constructor for a duke.
     */
    public Duke() {
        PARSER = new Parser();
        STORAGE = new Storage();
        TASKLIST = new TaskList(STORAGE.loadFromDisk());
        UI = new Ui(this.TASKLIST);
        UI.greet();
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
        ActionType actionType = PARSER.parseActionType(command);
        switch (actionType) {
        case LIST:
            UI.displayTasks();
            break;
        case TODO: {
            String task = PARSER.parseTask(command);
            ToDo toDo = TASKLIST.createTodo(task);
            TASKLIST.addTask(toDo);
            UI.showAddedTask(toDo);
            STORAGE.saveProgress();
            break;
        }
        case DEADLINE: {
            String task = PARSER.parseTask(command);
            DeadLine ddl = TASKLIST.createDeadLine(task);
            TASKLIST.addTask(ddl);
            UI.showAddedTask(ddl);
            STORAGE.saveProgress();
            break;
        }
        case EVENT: {
            String task = PARSER.parseTask(command);
            Event event = TASKLIST.createEvent(task);
            TASKLIST.addTask(event);
            UI.showAddedTask(event);
            STORAGE.saveProgress();
            break;
        }
        case DONE: {
            int taskIdx = PARSER.getTaskIdx(command, TASKLIST.size());
            Task toBeMarked = TASKLIST.get(taskIdx - 1);
            TASKLIST.markTaskAsDone(taskIdx - 1);
            UI.showMarkedAsDone(toBeMarked);
            STORAGE.saveProgress();
            break;
        }
        case DELETE: {
            int taskIdx = PARSER.getTaskIdx(command, TASKLIST.size());
            Task toBeDeleted = TASKLIST.get(taskIdx - 1);
            TASKLIST.deleteTask(toBeDeleted);
            UI.showDeletedTask(toBeDeleted);
            STORAGE.saveProgress();
            break;
        }
        case BYE: {
            STORAGE.saveProgress();
            UI.bye();
            this.isLive = false;
            break;
        }
        default:
            throw new UnrecognizableCommandException();
        }
    }
}
