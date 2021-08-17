import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {



    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        initialiseBot();

        boolean status = true;
        List<Task> tasks = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        while (status) {
            String message = sc.nextLine();
            message.trim();

            if (message.isEmpty()) {

            } else if (message.equals("bye")) {
                status = false;
                closeBot();
            } else if (message.equals("list")) {
                printTasks(tasks);
            } else if (message.startsWith("done")) {
                markTaskAsDone(message, tasks);
            } else if (message.startsWith("todo")) {
                String taskName = getTaskName(message);
                addTodo(taskName, tasks);
            } else if (message.startsWith("deadline")) {
                String deadline = getDateAndTime(message, "deadline");
                String taskName = getTaskName(message);
                addDeadline(taskName, deadline, tasks);
            } else if (message.startsWith("event")) {
                String eventTime = getDateAndTime(message, "event");
                String taskName = getTaskName(message);
                addEvent(taskName, eventTime, tasks);
            } else {
//                echo(message);
                add(message, tasks);
            }
        }

        sc.close();

    }

    private static void addEvent(String taskName, String eventTime, List<Task> tasks) {
        Event storedEvent = new Event(taskName, eventTime);
        tasks.add(storedEvent);
        String displayedMessage = getAddedSuccessMessage(storedEvent, tasks);
        System.out.println(displayedMessage);
    }

    private static String getAddedSuccessMessage(Task task, List<Task> tasks) {
        String successMessage = "Got it. I've added this task:";
        String taskString = task.toString();
        String totalTaskString = String.format("Now you have %d tasks in the list.", getTotalTaskNumber(tasks));
        String result = successMessage + "\n"
                + "  " + taskString + "\n"
                + totalTaskString;
        return result;
    }

    private static int getTotalTaskNumber(List<Task> tasks) {
        return tasks.size();
    }

    private static void addDeadline(String taskName, String deadline, List<Task> tasks) {
        Deadline storedDeadline = new Deadline(taskName, deadline);
        tasks.add(storedDeadline);
        String displayedMessage = getAddedSuccessMessage(storedDeadline, tasks);
        System.out.println(displayedMessage);
    }

    private static String getDateAndTime(String message, String taskType) {

        if (taskType.equals("deadline")) {
            int startingIndex = message.indexOf("/by ");

            if (startingIndex < 0 || startingIndex + 3 == message.length() - 1) {
                return "";
            }

            String result = message.substring(startingIndex + 4);
            return result;
        } else if (taskType.equals("event")) {
            int startingIndex = message.indexOf("/at ");

            if (startingIndex < 0 || startingIndex + 3 == message.length() - 1) {
                return "";
            }

            String result = message.substring(startingIndex + 4);
            return result;

        } else {
            return "";
        }

    }

    private static String getTaskName(String message) {
        int startingIndex = message.indexOf(" ");
        int endingIndex = message.indexOf("/");

        if (message.startsWith("todo") && startingIndex != message.length() - 1) {
            return message.substring(startingIndex + 1);
        } else if (startingIndex < 0 || (endingIndex - startingIndex < 2)) {
            //TODO no task name
            return "error";
        } else {
            return message.substring(startingIndex + 1, endingIndex - 1);
        }

    }

    private static void addTodo(String taskName, List<Task> tasks) {
        Todo storedTodo = new Todo(taskName);
        tasks.add(storedTodo);
        String displayedMessage = getAddedSuccessMessage(storedTodo, tasks);
        System.out.println(displayedMessage);
    }

    private static int getTaskNumber(String message) {
        String numberString = "";
        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);
            if (!numberString.isEmpty() && Character.isWhitespace(currentChar)) {
                break; //task number string complete
            } else if (Character.isDigit(currentChar)) {
                numberString += message.charAt(i);
            } else {}
        }

        int number;
        if (numberString.isEmpty()) {
            number = -1;
        } else {
            number = Integer.parseInt(numberString);
        }
        return number;
    }

    private static void markTaskAsDone(String message, List<Task> tasks) {
        int taskPosition = getTaskNumber(message) - 1;
        if (taskPosition < 0) {
            System.out.println("error");
        } else {
            Task taskMarked = tasks.get(taskPosition);
            taskMarked.markAsDone();
            String displayedMessage = "Nice! I've marked this task as done:\n"
                    + "  "
                    + taskMarked.toString();
            System.out.println(displayedMessage);
        }
    }

    private static void printTasks(List<Task> tasks) {
        String message = "Here are the tasks in your list:";
        System.out.println(message);
        for (int i = 1; i <= tasks.size(); i++) {
            Task currentTask = tasks.get(i - 1);
            String displayedTask = i + "." + currentTask.toString();
            System.out.println(displayedTask);
        }
    }

    private static void add(String message, List<Task> tasks) {
        String displayedMessage = "added: " + message;
        Task storedTask = new Task(message);
        tasks.add(storedTask);
        System.out.println(displayedMessage);
    }

    private static void closeBot() {
        String message = "Bye. Hope to see you again soon!";
        System.out.println(message);
    }

    private static void echo(String message) {
        System.out.println(message);
    }

    private static void initialiseBot() {
        String message = "Hello! I'm Duke\n"
                + "What can I do for you?";
        System.out.println(message);
    }


}
