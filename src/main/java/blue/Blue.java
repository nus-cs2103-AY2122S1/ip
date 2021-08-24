package blue;

import blue.handler.*;

import java.util.HashMap;
import java.util.Scanner;

public class Blue {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private HashMap<String, CommandHandler> commandHandlers;

    public Blue(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BlueException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        initCommandHandlers();
        ui.showLogo();
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            boolean shouldContinue = handle(input);
            storage.save(tasks);
            if (!shouldContinue) {
                break;
            }
        }
        scanner.close();
    }

    private void initCommandHandlers() {
        commandHandlers = new HashMap<>();
        ListHandler listHandler = new ListHandler(tasks);
        ToDoHandler toDoHandler = new ToDoHandler(tasks);
        DeadlineHandler deadlineHandler = new DeadlineHandler(tasks);
        EventHandler eventHandler = new EventHandler(tasks);
        DoneHandler doneHandler = new DoneHandler(tasks);
        DeleteHandler deleteHandler = new DeleteHandler(tasks);
        FindHandler findHandler = new FindHandler(tasks);
        commandHandlers.put(Command.LIST, listHandler);
        commandHandlers.put(Command.TODO, toDoHandler);
        commandHandlers.put(Command.DEADLINE, deadlineHandler);
        commandHandlers.put(Command.EVENT, eventHandler);
        commandHandlers.put(Command.DONE, doneHandler);
        commandHandlers.put(Command.DELETE, deleteHandler);
        commandHandlers.put(Command.FIND, findHandler);
    }

    private boolean handle(String input) {
        String command = Parser.getCommand(input);
        if (command.equals(Command.EXIT)) {
            ui.goodbye();
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
            ui.confused();
        }
        return true;
    }

    public static void main(String[] args) {
        new Blue("data/tasks.txt").run();
    }
}
