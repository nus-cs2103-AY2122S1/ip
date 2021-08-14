import java.util.Scanner;

public class Side {
    private static final String LINEBREAK = "---------------------------------------------------------------------";
    private static final String GREETING = LINEBREAK + "" + "\nI'm Side, your unpaid personal assistant. " +
            "Please do less...\n" + LINEBREAK;
    private static final String GOODBYE = LINEBREAK + "\nOh, you have to go? What a pity...\n"
            + LINEBREAK;

    private static void printLogo() {
        String logo = " ___  _____  _____   _____  \n"
                + "|  _|  | |  | ___ \\  | |__\n"
                + " \\ \\   | |  | |_| |  | |  \n"
                + "|___| _|_|_ |____/   |_|__\n";
        System.out.println(logo);
    }

    private static void echo(String input, TaskList tasks) {
        System.out.println(LINEBREAK);
        String taskQuantifier = tasks.length() == 1 ? "task..." : "tasks...";
        System.out.println("Fine, I'll add: " + input + "\nYou now have " + tasks.length() + " " + taskQuantifier);
        System.out.println(LINEBREAK);
    }

    private static void printResponse(String input) {
        System.out.println(LINEBREAK);
        System.out.println(input);
        System.out.println(LINEBREAK);
    }

    private static String findTime(String input, String arg) {
        int argIdx = input.lastIndexOf(arg);
        String output = input.substring(argIdx + arg.length());

        if (output.replaceAll("\\s", "").length() < 1) {
            return null;
        }
        return output;
    }

    private static void addDeadline(String input, TaskList taskList) throws WrongFormatException {
        if (input.contains("/by") && (findTime(input, "/by") != null)) {
            String time = findTime(input, "/by");
            String description = input.replace("/by" + time, "");
            taskList.addDeadline(description, time);
            echo(new Deadline(description, time).toString(), taskList);
        } else {
            throw new WrongFormatException("deadline [task name] /by [time]");
        }
    }

    private static void addEvent(String input, TaskList taskList) throws WrongFormatException {
        if (input.contains("/at") && (findTime(input, "/at") != null)) {
            String time = findTime(input, "/at");
            String description = input.replace("/at" + time, "");
            taskList.addEvent(description, time);
            echo(new Event(description, time).toString(), taskList);
        } else {
            throw new WrongFormatException("event [task name] /at [time]");
        }
    }

    private static void addTask(String input, TaskList taskList) throws WrongFormatException {
        if (input.replace("todo", "").replaceAll(" ", "").length() > 0) {
            taskList.addTask(input);
            echo(new Task(input).toString(), taskList);
        } else {
            throw new WrongFormatException("todo [task name]");
        }
    }

    public static void main(String[] args) {
        printLogo();
        System.out.println(GREETING);
        TaskList tasks = new TaskList();

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (!userInput.equalsIgnoreCase("bye")) {
            String command = userInput.split("\\s+")[0];

            try {
                switch (command) {
                    case "todo":
                        addTask(userInput, tasks);
                        break;
                    case "deadline":
                        addDeadline(userInput, tasks);
                        break;
                    case "event":
                        addEvent(userInput, tasks);
                        break;
                    case "list":
                        printResponse(tasks.toString());
                        break;
                    case "done":
                        if (userInput.split("\\s+").length == 2) {
                            int taskNum = Integer.parseInt(userInput.split("\\s+")[1]);
                            if (taskNum > tasks.length() || taskNum <= 0) {
                                throw new TaskIndexException();
                            } else {
                                printResponse(tasks.markTaskDone(taskNum - 1));
                            }
                        } else {
                            printResponse("I only have 1 hand, I can only mark 1 at a time...");
                        }
                        break;
                    default:
                        throw new UnknownCommandException();
                }
            } catch (UnknownCommandException | TaskIndexException | WrongFormatException e) {
                printResponse(e.getMessage());
            }
            userInput = scanner.nextLine();
        }

        System.out.println(GOODBYE);
    }
}
