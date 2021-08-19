import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BruhBot {
    /** The chatbot's logo. */
    private static final String LOGO = "  /$$                           /$$      \n"
            + " | $$                          | $$      \n" + " | $$$$$$$   /$$$$$$  /$$   /$$| $$$$$$$ \n"
            + " | $$__  $$ /$$__  $$| $$  | $$| $$__  $$\n" + " | $$  \\ $$| $$  \\__/| $$  | $$| $$  \\ $$\n"
            + " | $$  | $$| $$      | $$  | $$| $$  | $$\n" + " | $$$$$$$/| $$      |  $$$$$$/| $$  | $$\n"
            + " |_______/ |__/       \\______/ |__/  |__/";

    /** The greeting message displayed when the bot is initialized. */
    private static final String GREETING = String.format("What can\n%s\ndo for you today?", LOGO);

    /** The farewell message displayed when the bot is exited. */
    private static final String FAREWELL = "Bye. Hope to see you again soon!\n";

    /** The command which stops the bot. */
    private static final String STOP_SIGNAL = "bye";

    private String userInput = "";
    private Scanner userInputScanner = new Scanner(System.in);
    private List<Task> tasks = new ArrayList<>();

    /**
     * Initializes the chatbot.
     */
    public void init() {
        System.out.println(GREETING + '\n');
        listen();
    }

    /**
     * Performs cleanup tasks before exiting the bot.
     */
    public void shutdown() {
        userInputScanner.close();
    }

    /**
     * Begin listening for user input. Terminates when the user says 'bye'.
     */
    public void listen() {
        while (true) {
            userInput = userInputScanner.nextLine();

            boolean isStopCommand = userInput.equals(STOP_SIGNAL);
            System.out.println(formatResponse(processInput(userInput)));
            if (isStopCommand) {
                break;
            }
        }
        shutdown();
    }

    /**
     * Processes the user's input by calling the appropriate method & returning the
     * resulting response.
     * 
     * @param input The input entered by the user.
     * @return The response to be displayed.
     */
    private String processInput(String input) {
        try {
            if (input.isEmpty()) {
                throw new InvalidArgumentException();
            } else if (input.equals("bye")) {
                return FAREWELL;
            } else if (input.equals("list")) {
                return listTasks();
            } else {
                String[] sections = input.split(" ", 2);
                String keyword = sections[0];
                try {
                    switch (keyword) {
                        case "done": {
                            checkMissingArguments(sections, "Please specify a task number to mark as done.\n");
                            int index = Integer.parseInt(sections[1]) - 1;
                            tasks.get(index).markAsDone();
                            return String.format("Nice! I've marked this task as done:\n  %s\n",
                                    tasks.get(index).toString());
                        }
                        case "delete": {
                            checkMissingArguments(sections, "Please specify a task number for deletion.\n");
                            int index = Integer.parseInt(sections[1]) - 1;
                            return generateRemoveTaskResponse(tasks.remove(index));
                        }
                        case "todo": {
                            checkMissingArguments(sections,
                                    String.format("The description of a %s cannot be empty.\n", keyword));
                            Todo newTask = new Todo(sections[1]);
                            tasks.add(newTask);
                            return generateAddTaskResponse(newTask);
                        }
                        case "deadline": {
                            checkMissingArguments(sections,
                                    String.format("The description of a %s cannot be empty.\n", keyword));
                            sections = sections[1].split(" /by ", 2);
                            checkMissingArguments(sections,
                                    String.format("Please specify the date/time of your deadline.\n", keyword));
                            Deadline newTask = new Deadline(sections[0], sections[1]);
                            tasks.add(newTask);
                            return generateAddTaskResponse(newTask);
                        }
                        case "event": {
                            checkMissingArguments(sections,
                                    String.format("The description of a %s cannot be empty.\n", keyword));
                            sections = sections[1].split(" /at ", 2);
                            checkMissingArguments(sections,
                                    String.format("Please specify a time for your event.\n", keyword));
                            Event newTask = new Event(sections[0], sections[1]);
                            tasks.add(newTask);
                            return generateAddTaskResponse(newTask);
                        }
                        default:
                            throw new InvalidArgumentException();
                    }
                } catch (InvalidArgumentException | MissingArgumentException e) {
                    return e.getMessage();
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    return "Please specify a valid task number (use 'list' to view your tasks).\n";
                }
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Checks for missing argument in the user input.
     * 
     * @param sections     The list of the user's entered arguments, divided into
     *                     relevant sections.
     * @param errorMessage The error message to be displayed, in the case of missing
     *                     arguments.
     * @throws MissingArgumentException if missing arguments are detected.
     */
    private void checkMissingArguments(String[] sections, String errorMessage) throws MissingArgumentException {
        if (sections.length != 2 || sections[1].isEmpty()) {
            throw new MissingArgumentException(errorMessage);
        }
    }

    /**
     * Generates the response that is displayed whenever a new task is added.
     * 
     * @param newTask The new task added to the task list.
     * @return The response displayed to the user.
     */
    private String generateAddTaskResponse(Task newTask) {
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d task%s in the list.\n",
                newTask.toString(), tasks.size(), (tasks.size() == 1 ? "" : 's'));
    }

    /**
     * Generates the response that is displayed whenever a task is removed.
     * 
     * @param removedTask The task removed from the task list.
     * @return The response displayed to the user.
     */
    private String generateRemoveTaskResponse(Task removedTask) {
        return String.format("Noted. I've removed this task:\n  %s\nNow you have %d task%s in the list.\n",
                removedTask.toString(), tasks.size(), (tasks.size() == 1 ? "" : 's'));
    }

    private String listTasks() {
        if (tasks.isEmpty()) {
            return "You have no tasks.\n";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%d.%s\n", i + 1, tasks.get(i).toString()));
        }
        return sb.toString();
    }

    private String formatResponse(String content) {
        final String divider = "____________________________________________________________\n";
        content = divider + content.replaceAll("(?m)^", " ") + divider;
        return content.replaceAll("(?m)^", "    ");
    }
}
