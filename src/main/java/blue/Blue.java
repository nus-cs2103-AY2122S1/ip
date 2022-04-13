package blue;

import java.util.HashMap;

import blue.handler.CommandHandler;
import blue.handler.DeadlineHandler;
import blue.handler.DeleteHandler;
import blue.handler.DoneHandler;
import blue.handler.EventHandler;
import blue.handler.ExitHandler;
import blue.handler.FindHandler;
import blue.handler.ListHandler;
import blue.handler.StatsHandler;
import blue.handler.ToDoHandler;

/**
 * Entry point of the Blue application.
 * Initializes the application and interacts with the user.
 */
public class Blue {
    private final Storage storage;
    private TaskList tasks;
    private HashMap<Command, CommandHandler> commandHandlers;

    /**
     * Constructs a Blue instance.
     *
     * @param filePath Path to save tasks.
     */
    public Blue(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BlueException e) {
            tasks = new TaskList();
        }
        initCommandHandlers();
    }

    private void initCommandHandlers() {
        // Construct the handlers
        ListHandler listHandler = new ListHandler(tasks);
        ToDoHandler toDoHandler = new ToDoHandler(tasks);
        DeadlineHandler deadlineHandler = new DeadlineHandler(tasks);
        EventHandler eventHandler = new EventHandler(tasks);
        DoneHandler doneHandler = new DoneHandler(tasks);
        DeleteHandler deleteHandler = new DeleteHandler(tasks);
        FindHandler findHandler = new FindHandler(tasks);
        StatsHandler statsHandler = new StatsHandler(tasks);
        ExitHandler exitHandler = new ExitHandler(tasks);

        // Put the handlers into HashMap
        commandHandlers = new HashMap<>();
        commandHandlers.put(Command.LIST, listHandler);
        commandHandlers.put(Command.TODO, toDoHandler);
        commandHandlers.put(Command.DEADLINE, deadlineHandler);
        commandHandlers.put(Command.EVENT, eventHandler);
        commandHandlers.put(Command.DONE, doneHandler);
        commandHandlers.put(Command.DELETE, deleteHandler);
        commandHandlers.put(Command.FIND, findHandler);
        commandHandlers.put(Command.STATS, statsHandler);
        commandHandlers.put(Command.EXIT, exitHandler);
    }

    /**
     * Handles user input and returns Blue's response.
     *
     * @param input User input.
     * @return Response from Blue.
     */
    public String getResponse(String input) {
        assert input != null : "Input should be String (possible empty)";
        try {
            Command command = Parser.getCommand(input);
            CommandHandler commandHandler = commandHandlers.get(command);
            return commandHandler.handle(input);
        } catch (BlueException e) {
            return e.getMessage();
        }
    }

    void save() {
        storage.save(tasks);
    }
}
