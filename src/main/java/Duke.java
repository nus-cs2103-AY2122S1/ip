import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {

    private ArrayList<Task> userInput;
    private boolean isBye;
    private Scanner scanner;

    private final static String divider = "  ---------------------------------------------";
    private final static String indent1 = "    ";
    private final static String indent2 = "      ";

    public enum Commands {
        LIST, BYE, DELETE, DONE,
        TODO, EVENT, DEADLINE;
    }


    public Duke() {
        scanner = new Scanner(System.in);
        userInput = new ArrayList<>();
        isBye = false;
    }

    public void getInput() {
        while (!isBye && scanner.hasNext()) {
            String input = scanner.nextLine().trim();
            String[] splitInput = input.split(" +");
            try {
                Commands word = Commands.valueOf(splitInput[0].toUpperCase());

                switch (word) {

                    case BYE:
                        System.out.println(divider);
                        System.out.println(indent1 + "Bye! Hope to see you again soon :)");
                        System.out.println(divider);
                        isBye = true;
                        scanner.close();
                        break;

                    case LIST:
                        System.out.println(divider);
                        System.out.println(indent1 + "Here are the tasks in your list:");
                        int point = 0;
                        while (point < userInput.size()) {
                            Task temp = userInput.get(point);
                            System.out.println(indent2 + (point + 1) + ". " + temp.toString());
                            point++;
                        }
                        printNumberOfTasks();
                        break;

                    case DONE:
                        inputDone(input, splitInput);
                        break;

                    case DELETE:
                        deleteTask(input, splitInput);
                        break;

                    case TODO:

                    case DEADLINE:

                    case EVENT:
                        checkTask(input);
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(divider);
                System.out.println(indent1 + "☹ OH NO I'm sorry, but I don't " +
                        "know what that means :-(");
                System.out.println(divider);
            } catch (DukeException e) {
                System.out.println(divider);
                System.out.println(e.getMessage());
                System.out.println(divider);
            }
        }
    }

    public void deleteTask(String input, String[] splitInput) throws DukeException {
        if (splitInput.length == 2) {
            int taskNum = Integer.valueOf(splitInput[1]);
            if (taskNum <= 0 || taskNum > userInput.size()) {
                throw new DukeException(indent1 + "☹ OOPS!!! There is no " +
                        "corresponding task to be deleted.");
            } else {
                System.out.println(divider);
                System.out.println(indent1 + "Sure! I've removed this task:\n" + indent2 +
                        userInput.get(taskNum - 1).toString());
                userInput.remove(taskNum - 1);
                printNumberOfTasks();
            }
        } else {
            throw new DukeException(indent1 + "☹ OOPS!!! The task to be deleted" +
                    "is not indicated!!");
        }
    }

    public void inputDone(String input, String[] splitInput) throws DukeException {
        if (splitInput.length == 2) {
            int taskNum = Integer.valueOf(splitInput[1]);
            if (taskNum > userInput.size() || taskNum <= 0) {
                throw new DukeException(indent1 + "☹ OOPS!!! There is no corresponding task to be " +
                        "marked done.");
            } else {
                Task temp = userInput.get(Integer.valueOf(splitInput[1]) - 1);
                temp.markAsDone();
                System.out.println(divider);
                System.out.println(indent1 + "YAY good job for completing the task :)\n" +
                        indent1 + "I've marked it as done:\n" + indent2 +
                        temp.toString());
                System.out.println(divider);
            }
        } else if (splitInput.length == 1) {
            throw new DukeException(indent1 + "☹ OOPS!!! The task to be marked done is not indicated!!");
        }
    }

    public void addTask(Task task) {
        userInput.add(task);
        System.out.println(divider);
        System.out.println(indent1 + "Sure! I've added this task:");
        System.out.println(indent2 + task.toString());
        printNumberOfTasks();
    }


    public void printNumberOfTasks() {
        String numberOfTasks = indent1 + "Now you have " + userInput.size() +
                (userInput.size() == 1 ? " task" : " tasks") + " in the list.\n" +
                divider;
        System.out.println(numberOfTasks);
    }

    public void checkTask(String input) throws DukeException {
        String[] splitInput2 = input.split(" +", 2);
        String taskType = splitInput2[0];
        if (splitInput2.length == 2) {
            if (taskType.equals("todo")) {
                Todo todo = new Todo(splitInput2[1]);
                addTask(todo);
            } else {
                String[] splitTask2 = splitInput2[1].split(" +");
                String[] splitTask = taskType.equals("deadline")
                        ? splitInput2[1].split("/by") :
                        splitInput2[1].split("/at");
                if (splitTask.length == 2 && !splitTask[0].isBlank()) {
                    Task toAdd;
                    switch (taskType) {
                        case "deadline":
                            try {
                                LocalDate date = LocalDate.parse(splitTask[1].trim());
                                toAdd = new Deadline(splitTask[0], date);
                                addTask(toAdd);
                            } catch (DateTimeParseException e) {
                                System.out.println(indent1 + "OH NO :( I can't seem to understand the date you have entered.\n"
                                        + indent1 + "I can only understand if it is in  the yyyy-mm-dd format..");
                            }
                            break;
                        case "event":
                            try {
                                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                                LocalDateTime dateTime = LocalDateTime.parse(splitTask[1].trim(), dtf);
                                toAdd = new Event(splitTask[0], dateTime);
                                addTask(toAdd);
                            } catch (DateTimeParseException e) {
                                System.out.println(indent1 + "OH NO :( I can't seem " +
                                        "to understand the date and time you have entered.\n"
                                        + indent1 + "I can only understand if it is in yyyy-MM-dd HH:mm format..");
                            }
                            break;
                    }
                } else {
                    if (taskType.equals("deadline") && !splitInput2[1].contains("/by")
                            || taskType.equals("event") && !splitInput2[1].contains("/at")) {
                        throw new DukeException(indent1 + "☹ OOPS!!! The " +
                                (taskType.equals("event") ? "period which the event occurs" :
                                        "deadline") + " is not inputted correctly. Use " +
                                (taskType.equals("event") ? "/at" : "/by") +
                                " to indicate ;)");
                    } else {
                        noDescription(taskType);
                    }

                }

            }
        } else {
            noDescription(taskType);
        }
    }

    public static void noDescription(String taskType) throws DukeException {
        throw new DukeException(indent1 + "☹ OOPS!!! The description of " +
                (taskType.equals("event") ? "an " : "a ")
                + taskType + " cannot be empty.");
    }


    public static void main(String[] args) {

        System.out.println("  ---------------------------------------------");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("  ---------------------------------------------");

        Duke duke = new Duke();
        duke.getInput();

    }
}