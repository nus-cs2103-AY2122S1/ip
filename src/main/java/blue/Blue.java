package blue;

import java.util.HashMap;
import java.util.Scanner;

import blue.handler.CommandHandler;
import blue.handler.DeadlineHandler;
import blue.handler.DeleteHandler;
import blue.handler.DoneHandler;
import blue.handler.EventHandler;
import blue.handler.ExitHandler;
import blue.handler.FindHandler;
import blue.handler.ListHandler;
import blue.handler.ToDoHandler;

/**
 * Entry point of the Blue application.
 * Initializes the application and interacts with the user.
 */
public class Blue {
    private static final String CONFUSED_RESPONSE =
            "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private HashMap<String, CommandHandler> commandHandlers;

    public Blue() {
        this("data/tasks.txt");
    }

    /**
     * Constructs a Blue instance.
     *
     * @param filePath Path to save tasks.
     */
    public Blue(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BlueException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        initCommandHandlers();
    }

    /**
     * Keeps engaging the user until the user input the exit command.
     */
    public void run() {
        ui.showLogo();
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            boolean shouldContinue = canHandle(input);
            storage.save(tasks);
            if (!shouldContinue) {
                break;
            }
        }
        scanner.close();
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
        ExitHandler exitHandler = new ExitHandler(tasks);

        // put the handlers into HashMap
        commandHandlers = new HashMap<>();
        commandHandlers.put(Command.LIST, listHandler);
        commandHandlers.put(Command.TODO, toDoHandler);
        commandHandlers.put(Command.DEADLINE, deadlineHandler);
        commandHandlers.put(Command.EVENT, eventHandler);
        commandHandlers.put(Command.DONE, doneHandler);
        commandHandlers.put(Command.DELETE, deleteHandler);
        commandHandlers.put(Command.FIND, findHandler);
        commandHandlers.put(Command.EXIT, exitHandler);
    }

    private boolean canHandle(String input) {
        String command = Parser.getCommand(input);
        if (command.equals(Command.EXIT)) {
            ui.sayGoodbye();
            return false;
        }
        if (commandHandlers.containsKey(command)) {
            CommandHandler commandHandler = commandHandlers.get(command);
            try {
                String response = commandHandler.handle(input);
                ui.speak(response);
            } catch (BlueException e) {
                ui.speak(e.getMessage());
            }
        } else {
            ui.actConfused();
        }
        return true;
    }

    /**
     * Handles user input and returns Blue's response.
     *
     * @param input User input.
     * @return Response from Blue.
     */
    public String getResponse(String input) {
        String command = Parser.getCommand(input);
        if (commandHandlers.containsKey(command)) {
            CommandHandler commandHandler = commandHandlers.get(command);
            try {
                return commandHandler.handle(input);
            } catch (BlueException e) {
                return e.getMessage();
            }
        } else {
            return CONFUSED_RESPONSE;
        }
    }

    /**
     * Creates a Blue instance and runs it.
     *
     * @param args Ignored.
     */
    public static void main(String[] args) {
        new Blue("data/tasks.txt").run();
    }
}
