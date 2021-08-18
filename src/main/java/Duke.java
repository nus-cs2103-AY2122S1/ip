import java.util.Scanner;
import java.util.StringJoiner;

public class Duke {

    private static final Task[] taskList = new Task[100];
    private static int tasks = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you today?");

        String userCommand;
        String userInput;

        mainLoop:
        while (true) {
            try {
                userCommand = scanner.next();
                userInput = scanner.nextLine().trim();

                switch (userCommand) {
                    case "list":
                        if (tasks == 0) {
                            System.out.println("Currently no tasks!");
                        }
                        for (int i = 0; i < tasks; i++) {
                            System.out.printf("%d. %s%n", i + 1, taskList[i]);
                        }
                        break;
                    case "bye":
                        System.out.println("Good bye.");
                        break mainLoop;
                    case "done":
                        int done = getInputNumber(userInput);

                        if (done >= tasks || done < 0) {
                            System.out.println("Task does not exist!");
                            continue;
                        }

                        Task doneTask = taskList[done];
                        doneTask.markAsDone();

                        System.out.printf("I've marked this task as done:\n" +
                                "%s\n", doneTask.toString());

                        break;
                    case ("todo"):
                    case("deadline"):
                    case("event"):
                        if (userInput.equals("")) {
                            throw new DukeException("The description of a Task cannot be empty.");
                        }

                        if (userCommand.equals("todo")) {
                            taskList[tasks] = new Todo(userInput);
                        } else if (userCommand.equals("deadline")) {
                            String[] deadlineInfo = splitBetween(userInput, "/by");
                            taskList[tasks] = new Deadline(buildDescription(deadlineInfo, "by"));
                        } else {
                            String[] eventInfo = splitBetween(userInput, "/at");
                            taskList[tasks] = new Event(buildDescription(eventInfo, "at"));
                        }
                        addTask(taskList[tasks]);
                        break;
                    default:
                        throw new DukeException("Sorry I do not understand this directive.");
                }}
            catch (DukeException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private static void addTask(Task newTask) {
        tasks += 1;
        System.out.printf("Got it, I've added this task:\n %s\n", newTask.toString());
        System.out.printf("Now you have %d tasks in your list.\n", tasks);
    }

    private static String[] splitBetween(String str, String separator) throws DukeException {
        StringJoiner start = new StringJoiner(" ");
        StringJoiner end = new StringJoiner(" ");

        int i = 0;
        boolean after = false;

        String[] strArray = str.split(" ");

        while (i < strArray.length) {
            String currentString = strArray[i];
            if (after) {
                end.add(currentString);
            } else if (currentString.equals(separator)) {
                after = true;
            } else {
                start.add(currentString);
            }
            i++;
        }

        if (!after) {
            throw new DukeException("Incorrect format for command.");
        }

        if (String.valueOf(end).equals("")) {
            throw new DukeException("Please specify a time for the task.");
        }

        return new String[] {String.valueOf(start), String.valueOf(end)};
    }

    private static String buildDescription(String[] info, String preposition) {
        return String.format("%s (%s: %s)", info[0], preposition, info[1]);
    }

    private static int getInputNumber(String userInput) throws DukeException {
        try {
           return Integer.parseInt(userInput) - 1;
        } catch (NumberFormatException exception) {
            throw new DukeException("Please enter a number after the done command.");
        }
    }
}
