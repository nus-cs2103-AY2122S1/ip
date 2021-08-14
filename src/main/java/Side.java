import java.util.Scanner;

public class Side {
    private static final String LINEBREAK = "---------------------------------------------------------------------";
    private static final String GREETING = LINEBREAK + "" + "\nHello! I'm Side, your personal assistant. " +
            "How can I help you today?\n" + LINEBREAK;
    private static final String GOODBYE = LINEBREAK + "\nOh, you have to go? Goodbye, I'll see you soon!\n"
            + LINEBREAK;
    private static final String NOSUCHTASK = "Sorry, this task doesn't exist!";

    private static void printLogo() {
        String logo = " ___  _____  _____   _____  \n"
                + "|  _|  | |  | ___ \\  | |__\n"
                + " \\ \\   | |  | |_| |  | |  \n"
                + "|___| _|_|_ |____/   |_|__\n";
        System.out.println(logo);
    }

    private static void echo(String input, TaskList tasks) {
        System.out.println(LINEBREAK);
        String taskQuantifier = tasks.length() == 1 ? "task!" : "tasks!";
        System.out.println("Ok, I've added: " + input + "\nYou now have " + tasks.length() + " " + taskQuantifier);
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

    private static void addDeadline(String input, TaskList taskList) {
        if (input.contains("/by") && (findTime(input, "/by") != null)) {
            String time = findTime(input, "/by");
            String description = input.replace("/by" + time, "");
            taskList.addDeadline(description, time);
            echo(new Deadline(description, time).toString(), taskList);
        } else {
            printResponse("Your input is wrong, please try again!");
        }
    }

    private static void addEvent(String input, TaskList taskList) {
        if (input.contains("/at") && (findTime(input, "/at") != null)) {
            String time = findTime(input, "/at");
            String description = input.replace("/at" + time, "");
            taskList.addEvent(description, time);
            echo(new Event(description, time).toString(), taskList);
        } else {
            printResponse("Your input is wrong, please try again!");
        }
    }

    private static void addTask(String input, TaskList taskList) {
        taskList.addTask(input);
        echo(new Task(input).toString(), taskList);
    }

    public static void main(String[] args) {
        printLogo();
        System.out.println(GREETING);
        TaskList tasks = new TaskList();

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (!userInput.equalsIgnoreCase("bye")) {
            String command = userInput.split("\\s+")[0];

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
                            printResponse(NOSUCHTASK);
                        } else {
                            printResponse(tasks.markTaskDone(taskNum - 1));
                        }
                    } else {
                        printResponse("Sorry, you can only mark 1 task at a time.");
                    }
                    break;
                default:
                    printResponse("No such command, please use a valid command!");
            }
            userInput = scanner.nextLine();
        }

        System.out.println(GOODBYE);
    }
}
