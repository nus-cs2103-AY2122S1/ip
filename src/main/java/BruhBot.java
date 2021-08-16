import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BruhBot {
    private static final String LOGO = "  /$$                           /$$      \n"
            + " | $$                          | $$      \n" + " | $$$$$$$   /$$$$$$  /$$   /$$| $$$$$$$ \n"
            + " | $$__  $$ /$$__  $$| $$  | $$| $$__  $$\n" + " | $$  \\ $$| $$  \\__/| $$  | $$| $$  \\ $$\n"
            + " | $$  | $$| $$      | $$  | $$| $$  | $$\n" + " | $$$$$$$/| $$      |  $$$$$$/| $$  | $$\n"
            + " |_______/ |__/       \\______/ |__/  |__/";

    private static final String GREETING = String.format("What can\n%s\ndo for you today?", LOGO);
    private static final String FAREWELL = "Bye. Hope to see you again soon!\n";
    private static final String STOP_SIGNAL_STRING = "bye";
    private static final String GENERIC_ERROR_MESSAGE = "Please enter a valid command.\n";

    private String userCommand = "";
    private Scanner userCommandScanner = new Scanner(System.in);
    private List<Task> tasks = new ArrayList<>();

    /**
     * Initializes the chatbot with a greeting.
     */
    public void init() {
        System.out.println(GREETING + '\n');
    }

    public void shutdown() {
        userCommandScanner.close();
    }

    /**
     * Begin listening for user input. Terminates when the user says 'bye'.
     */
    public void listen() {
        while (true) {
            userCommand = userCommandScanner.nextLine();

            boolean isStopCommand = userCommand.equals(STOP_SIGNAL_STRING);
            System.out.println(formatResponse(process(userCommand)));
            if (isStopCommand) {
                break;
            }
        }
    }

    /**
     * Processes the command entered, calls the appropriate method & returns the
     * appropriate response.
     * 
     * @param command The command entered by the user.
     * @return The response to be displayed.
     */
    private String process(String command) {
        if (command.isEmpty()) {
            return GENERIC_ERROR_MESSAGE;
        } else if (command.equals("bye")) {
            return FAREWELL;
        } else if (command.equals("list")) {
            return listTasks();
        } else {
            String[] sections = command.split(" ", 2); // Split into at most 2 parts
            String keyword = sections[0];
            try {
                switch (keyword) {
                    case "done": {
                        checkArguments(sections, "Please specify an index.\n");
                        int index = Integer.parseInt(sections[1]) - 1;
                        tasks.get(index).markAsDone();
                        return String.format("Nice! I've marked this task as done:\n  %s\n",
                                tasks.get(index).toString());
                    }
                    case "delete": {
                        checkArguments(sections, "Please specify an index.\n");
                        int index = Integer.parseInt(sections[1]) - 1;
                        return generateRemoveTaskResponse(tasks.remove(index));
                    }
                    case "todo": {
                        checkArguments(sections, String.format("The description of a %s cannot be empty.\n", keyword));
                        Todo newTask = new Todo(sections[1]);
                        tasks.add(newTask);
                        return generateAddTaskResponse(newTask);
                    }
                    case "deadline": {
                        checkArguments(sections, String.format("The description of a %s cannot be empty.\n", keyword));
                        sections = sections[1].split(" /by ", 2);
                        checkArguments(sections,
                                String.format("Please specify the date/time of your deadline.\n", keyword));
                        Deadline newTask = new Deadline(sections[0], sections[1]);
                        tasks.add(newTask);
                        return generateAddTaskResponse(newTask);
                    }
                    case "event": {
                        checkArguments(sections, String.format("The description of a %s cannot be empty.\n", keyword));
                        sections = sections[1].split(" /at ", 2);
                        checkArguments(sections, String.format("Please specify a time for your event.\n", keyword));
                        Event newTask = new Event(sections[0], sections[1]);
                        tasks.add(newTask);
                        return generateAddTaskResponse(newTask);
                    }
                    default:
                        throw new InvalidArgumentException(keyword);
                }
            } catch (InvalidArgumentException | MissingArgumentException e) {
                return e.getMessage();
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                return "Please specify a valid index.\n";
            } catch (DukeException e) {
                return GENERIC_ERROR_MESSAGE;
            }
        }
    }

    private void checkArguments(String[] sections, String errorMessage) throws DukeException {
        // Sections is guaranteed to have 1 or 2 elements. 2nd element might be empty.
        if (sections.length != 2 || sections[1].isEmpty()) {
            throw new MissingArgumentException(errorMessage);
        }
    }

    private String generateAddTaskResponse(Task newTask) {
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d task%s in the list.\n",
                newTask.toString(), tasks.size(), (tasks.size() == 1 ? "" : 's'));
    }

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
        String out = divider + content.replaceAll("(?m)^", " ") + divider;
        return out.replaceAll("(?m)^", "    ");
    }
}
