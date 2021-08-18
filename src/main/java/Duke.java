import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";

    private static void initialiseBot() {
        String message = "Hello! I'm Duke\n"
                + "What can I do for you?";
        System.out.println(message);
    }

    private static void closeBot() {
        String message = "Bye. Hope to see you again soon!";
        System.out.println(message);
    }

    private static Task getTask(String message, String taskType) throws EmptyTaskDescriptionException, InvalidDateAndTimeException, InvalidInputException {

        switch (taskType) {
            case TODO:
                if (message.trim().equals(TODO)) {
                    throw new EmptyTaskDescriptionException("Empty Todo Description", TODO);
                } else {
                    if (!Character.isWhitespace(message.charAt(4))) {
                        throw new InvalidInputException("invalid input");
                    } else {
                        String taskName = message.substring(TODO.length() + 1);
                        return new Todo(taskName);
                    }
                }
            case DEADLINE:
                if (message.trim().equals(DEADLINE)) {
                    throw new EmptyTaskDescriptionException("Empty Deadline Description", DEADLINE);
                } else {
                    String deadline = getDateAndTime(message, DEADLINE);

                    int startingIndex = message.indexOf(" ");
                    int endingIndex = message.indexOf("/");
                    String taskName = message.substring(startingIndex + 1, endingIndex - 1);

                    //todo deadline return book being invalid input rather than invalid date

                    return new Deadline(taskName, deadline);
                }
            case EVENT:
                if (message.trim().equals(EVENT)) {
                    throw new EmptyTaskDescriptionException("Empty Event Description", EVENT);
                } else {
                    String eventTime = getDateAndTime(message, EVENT);

                    int startingIndex = message.indexOf(" ");
                    int endingIndex = message.indexOf("/");
                    String taskName = message.substring(startingIndex + 1, endingIndex - 1);

                    return new Event(taskName, eventTime);
                }
        }

        return new Task();
    }

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

            if (message.equals("bye")) {
                status = false;
                closeBot();
            } else if (message.equals("list")) {
                printTasks(tasks);
            } else if (message.startsWith("done")) {
                markTaskAsDone(message, tasks);
            } else if (message.startsWith("delete")) {
                deleteTask(message, tasks);
            } else if (message.startsWith("todo")) {
                Task todo = null;
                try {
                    todo = getTask(message, TODO);
                    if (todo.isEmpty()) {
                        throw new InvalidInputException("error");
                    }
                    addTask(todo, tasks);
                } catch (DukeException e) {
                    e.printErrorMessage();
                }
            } else if (message.startsWith("deadline")) {
                Task deadline = null;
                try {
                    deadline = getTask(message, DEADLINE);
                    if (deadline.isEmpty()) {
                        throw new InvalidInputException("error");
                    }
                    addTask(deadline, tasks);
                } catch (DukeException e) {
                    e.printErrorMessage();
                }
            } else if (message.startsWith("event")) {
                Task event = null;
                try {
                    event = getTask(message, EVENT);
                    if (event.isEmpty()) {
                        throw new InvalidInputException("error");
                    }
                    addTask(event, tasks);
                } catch (DukeException e) {
                    e.printErrorMessage();
                }
            } else {
                try {
                    throw new InvalidInputException("invalid input");
                } catch (DukeException e) {
                    e.printErrorMessage();
                }
            }
        }

        sc.close();
    }

    private static void deleteTask(String message, List<Task> tasks) {
        try {
            int taskPosition = getTaskNumber(message) - 1;
            if (taskPosition >= tasks.size()) {
                throw new InvalidInputException("invalid task number entered");
            } else {
                Task removedTask = tasks.get(taskPosition);
                tasks.remove(taskPosition);
                String displayedMessage = "Noted. I've removed this task:\n"
                        + "  " + removedTask.toString() + "\n"
                        + getTotalTaskString(tasks);
                System.out.println(displayedMessage);
            }
        } catch (DukeException e) {
            e.printErrorMessage();
        }
    }

    private static void addTask(Task task, List<Task> tasks) {
        tasks.add(task);
        String displayedMessage = getAddedSuccessMessage(task, tasks);
        System.out.println(displayedMessage);
    }

    private static String getTotalTaskString(List<Task> tasks) {
        return String.format("Now you have %d tasks in the list.", getTotalTaskNumber(tasks));
    }

    private static String getAddedSuccessMessage(Task task, List<Task> tasks) {
        String successMessage = "Got it. I've added this task:";
        String taskString = task.toString();
        String result = successMessage + "\n"
                + "  " + taskString + "\n"
                + getTotalTaskString(tasks);
        return result;
    }

    private static int getTotalTaskNumber(List<Task> tasks) {
        return tasks.size();
    }

    private static void checkValidTaskName(String message) throws InvalidInputException {
        int startingIndex = message.indexOf(" ");
        int endingIndex = message.indexOf("/");

        if (startingIndex < 0 || endingIndex - startingIndex <= 1) {
            throw new InvalidInputException("invalid input");
        }
    }

    private static String getDateAndTime(String message, String taskType) throws InvalidDateAndTimeException, InvalidInputException {
        int startingIndex;

        checkValidTaskName(message);

        switch (taskType) {
            case DEADLINE:
                startingIndex = message.indexOf("/by ");

                if (startingIndex < 0 || startingIndex + 3 == message.length() - 1) {
                    throw new InvalidDateAndTimeException("Invalid deadline", DEADLINE);
                }

                return message.substring(startingIndex + 4);
            case EVENT:
                startingIndex = message.indexOf("/at ");

                if (startingIndex < 0 || startingIndex + 3 == message.length() - 1) {
                    throw new InvalidDateAndTimeException("Invalid event time", EVENT);
                }

                return message.substring(startingIndex + 4);
            }
        return "";
    }

    private static int getTaskNumber (String message) throws InvalidInputException {
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
            throw new InvalidInputException("invalid task number entered");
        } else {
            number = Integer.parseInt(numberString);
        }
        return number;
    }

    private static void markTaskAsDone(String message, List<Task> tasks) {
        try {
            int taskPosition = getTaskNumber(message) - 1;
            if (taskPosition >= tasks.size()) {
                throw new InvalidInputException("invalid task number entered");
            } else {
                Task taskMarked = tasks.get(taskPosition);
                taskMarked.markAsDone();
                String displayedMessage = "Nice! I've marked this task as done:\n"
                        + "  "
                        + taskMarked.toString();
                System.out.println(displayedMessage);
            }
        } catch (DukeException e) {
            e.printErrorMessage();
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






}
