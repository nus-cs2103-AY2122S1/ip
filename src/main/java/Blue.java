import java.util.*;

public class Blue {
    private static final String LOGO = " ____  _                \n"
            + "|  . \\| | _   _   ____  \n"
            + "|____/| || | | | /  _  \\\n"
            + "|  . \\| || |_| ||   ___/\n"
            + "|____/|_||_____| \\_____/\n";
    private static final String GREET_CONTENT = "Hello! I'm Blue\n" + "What can I do for you?";
    private static final String EXIT_CONTENT = "Bye. Hope to never see you again!";
    private static final TaskList taskList = new TaskList();
    private static final ListHandler listHandler = new ListHandler(taskList);
    private static final ToDoHandler toDoHandler = new ToDoHandler(taskList);
    private static final DeadlineHandler deadlineHandler = new DeadlineHandler(taskList);
    private static final EventHandler eventHandler = new EventHandler(taskList);
    private static final DoneHandler doneHandler = new DoneHandler(taskList);
    private static final DeleteHandler deleteHandler = new DeleteHandler(taskList);
    private static final HashMap<String, CommandHandler> commandHandlers = new HashMap<>();


    public static void main(String[] args) {
        System.out.println(LOGO);
        initCommandHandlers();
        greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            boolean shouldContinue = handle(input);
            if (!shouldContinue)
                break;
        }
        scanner.close();
    }

    private static void initCommandHandlers() {
        commandHandlers.put(Command.LIST, listHandler);
        commandHandlers.put(Command.TODO, toDoHandler);
        commandHandlers.put(Command.DEADLINE, deadlineHandler);
        commandHandlers.put(Command.EVENT, eventHandler);
        commandHandlers.put(Command.DONE, doneHandler);
        commandHandlers.put(Command.DELETE, deleteHandler);
    }

    private static void greet() {
        speak(GREET_CONTENT);
    }

    private static boolean handle(String input) {
        String command = getCommand(input);
        if (command.equals(Command.EXIT)) {
            speak(EXIT_CONTENT);
            return false;
        }
        if (commandHandlers.containsKey(command)) {
            CommandHandler commandHandler = commandHandlers.get(command);
            try {
                speak(commandHandler.handle(input));
            } catch (BlueException e) {
                speak(e.getMessage());
            }
        } else {
            speak("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }

    private static String getCommand(String input) {
        if (input.length() > 0)
            return input.split(" ")[0];
        else
            return "";
    }

    private static void speak(String content) {
        String line = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        System.out.println(line);
        System.out.println(content);
        System.out.println(line);
    }
}
