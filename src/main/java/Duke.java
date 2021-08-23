/**
 * Specifies the type of action to be taken by the duke.
 */
enum ActionType {
    LIST, TODO, DEADLINE, EVENT, DONE, DELETE, BYE, UNRECOGNIZED
}

public class Duke {

    private boolean isLive = true;
    private final Storage storage;
    private final TaskList tasksLst;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructor for a duke.
     */
    public Duke() {
        parser = new Parser();
        storage = new Storage();
        tasksLst = new TaskList(storage.loadFromDisk());
        ui = new Ui(this.tasksLst);
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
            ToDo toDo = tasksLst.createTodo(task);
            tasksLst.addTask(toDo);
            ui.showAddedTask(toDo);
            storage.saveProgress();
            break;
        }
        case DEADLINE: {
            String task = parser.parseTask(command);
            DeadLine ddl = tasksLst.createDeadLine(task);
            tasksLst.addTask(ddl);
            ui.showAddedTask(ddl);
            storage.saveProgress();
            break;
        }
        case EVENT: {
            String task = parser.parseTask(command);
            Event event = tasksLst.createEvent(task);
            tasksLst.addTask(event);
            ui.showAddedTask(event);
            storage.saveProgress();
            break;
        }
        case DONE: {
            int taskIdx = parser.getTaskIdx(command, tasksLst.size());
            Task toBeMarked = tasksLst.get(taskIdx - 1);
            tasksLst.markTaskAsDone(taskIdx - 1);
            ui.showMarkedAsDone(toBeMarked);
            storage.saveProgress();
            break;
        }
        case DELETE: {
            int taskIdx = parser.getTaskIdx(command,tasksLst.size());
            Task toBeDeleted = tasksLst.get(taskIdx - 1);
            tasksLst.deleteTask(toBeDeleted);
            ui.showDeletedTask(toBeDeleted);
            storage.saveProgress();
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
