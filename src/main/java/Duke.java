import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulates the Duke bot that has the ability to create, read, update and delete tasks
 * such as todo, deadline and event tasks based on user input.
 *
 * @author Dickson
 */
public class Duke {
    private static ArrayList<Task> list;
    private static int numberOfTasks;

    public static void main(String[] args) {
        list = new ArrayList<>();
        numberOfTasks = 0;
        Scanner scanner = new Scanner(System.in);

        printDoubleLineBorder();
        System.out.println("Hi there! I'm Duke\n" + "How may I help you?\n");
        printDoubleLineBorder();

        processInput(scanner);
    }

    /**
     * Process user input.
     *
     * @param scanner Scanner for user input.
     */
    private static void processInput(Scanner scanner) {
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            printSingleLineBorder();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                scanner.close();
                break;
            } else if (input.equals("list")) {
                if (numberOfTasks == 0) {
                    System.out.println("There are no tasks in your list!");
                } else if (numberOfTasks == 1) {
                    System.out.println("Here is the task in your list:");
                } else {
                    System.out.println("Here are the tasks in your list:");
                }
                for (int i = 0; i < numberOfTasks; i++) {
                    System.out.println((i + 1) + "." + list.get(i));
                }
            } else {
                try {
                    String[] splitInput = input.split(" ");
                    if (splitInput.length == 0) {
                        throw new InvalidInputException("Invalid Task. Please enter a valid task.\n" +
                                "(Todo) todo CS2103T Readings\n" +
                                "(Deadline) deadline CS2103T Individual Project /by 2021-09-07 1159\n" +
                                "(Event) event CS2103T Finals /at 2021-11-23 1700");
                    } else if (splitInput[0].equals("done")) {
                        int taskIndex = Integer.parseInt(splitInput[1]) - 1;
                        if (taskIndex < 0 || taskIndex > list.size() - 1) {
                            throw new InvalidInputException("Invalid Task Index");
                        }
                        Task task = list.get(taskIndex);
                        task.markDone();
                        saveAsFile();
                        System.out.println("Nice! I've marked this task as done:\n" + task);
                    } else if (splitInput[0].equals("delete")) {
                        removeTask(splitInput[1]);
                    } else {
                        addTask(input);
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
            printSingleLineBorder();
        }
    }

    /**
     * Adds a Task object into the list based on user input.
     *
     * @param input user String input.
     * @throws DukeException Exceptions when using bot.
     */
    private static void addTask(String input) throws DukeException {
        Task task;
        String[] splitInput = input.split(" ");
        String taskType = splitInput[0];
        String taskDescription = "";
        boolean taskHasDate = false;
        boolean nextIsDateValue = false;
        for (int i = 1; i < splitInput.length; i++) {
            if (nextIsDateValue) {
                if (!validDate(splitInput[i])) {
                    throw new InvalidInputException("Invalid Task Date. Please input date in this format YYYY-MM-DD.");
                }
                nextIsDateValue = false;
                taskDescription = taskDescription.concat(parseDate(splitInput[i]) + " ");
            } else if (splitInput[i].contains("/")) {
                taskHasDate = true;
                nextIsDateValue = true;
                taskDescription = taskDescription.concat("(" + splitInput[i].split("/")[1] + ": ");
            } else if (i != splitInput.length - 1){
                taskDescription = taskDescription.concat(splitInput[i] + " ");
            } else {
                taskDescription = taskDescription.concat(splitInput[i]);
                if (taskHasDate) {
                    taskDescription += ")";
                }
            }
        }
        if (validTaskType(taskType)) {
            if (taskDescription.length() == 0) {
                throw new InvalidInputException("Invalid Task Description. The description of a task cannot be empty.");
            } else if (taskType.equals("todo")) {
                task = new ToDo(taskDescription);
                list.add(task);
            } else if (taskType.equals("deadline")) {
                if (!input.contains("by")) {
                    throw new InvalidInputException("Invalid Deadline Task.\n" +
                            "Please enter deadline task followed by /by and then the date/time it is due,\n" +
                            "e.g. CS2103T Individual Project /by 2021-09-07 1159");
                }
                task = new Deadline(taskDescription);
                list.add(task);
            } else {
                if (!input.contains("at")) {
                    throw new InvalidInputException("Invalid Event Task.\n" +
                            "Please enter event task followed by /at and then the date it is due,\n" +
                            "e.g. CS2103T Test /at 2021-11-23 1700");
                }
                task = new Event(taskDescription);
                list.add(task);
            }
            numberOfTasks++;
            saveAsFile();
            System.out.println("Got it. I've added this task:\n" + task);
            if (numberOfTasks == 1) {
                System.out.println("Now you have " + numberOfTasks + " task in the list");
            } else {
                System.out.println("Now you have " + numberOfTasks + " tasks in the list");
            }
        } else {
            throw new InvalidInputException("Invalid Task. Please enter a valid task.\n" +
                    "(Todo) todo CS2103T Readings\n" +
                    "(Deadline) deadline CS2103T Individual Project /by Week 6\n" +
                    "(Event) event CS2103T Test /at Friday 4-6pm");
        }
    }

    /**
     * Removes Task from list based on user input.
     *
     * @param index list index of task.
     * @throws DukeException Exceptions when using bot.
     */
    private static void removeTask(String index) throws DukeException {
        int taskIndex = Integer.parseInt(index) - 1;
        if (taskIndex < 0 || taskIndex > numberOfTasks - 1) {
            throw new InvalidInputException("Invalid Task Index.");
        } else {
            Task task = list.get(taskIndex);
            list.remove(taskIndex);
            numberOfTasks--;
            saveAsFile();
            System.out.println("Noted. I've removed the following task:\n" + task +
                    "\nNow you have " + numberOfTasks + " tasks in the list.");
        }
    }

    /**
     * Checks if task type of user input is valid.
     *
     * @param input Task type of type String.
     * @return boolean to check if task type is valid.
     */
    private static boolean validTaskType(String input) {
        return input.equals("todo") || input.equals("deadline") || input.equals("event");
    }

    /**
     * Saves the tasks list as a text file in user's current directory.
     */
    private static void saveAsFile() {
        try {
            String path = System.getProperty("user.dir");
            FileWriter myWriter = new FileWriter(path + "\\duke.txt");
            myWriter.write("Tasks:\n");
            for (int i = 0; i < numberOfTasks; i++) {
                myWriter.write(list.get(i).toString() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred when saving tasks list as text file." + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Returns boolean after checking for valid input date in this format: YYYY-MM-DD.
     *
     * @param input User input date string
     * @return boolean to check for valid user input date string.
     */
    private static boolean validDate(String input) {
        if (input.length() == 10) {
            return input.charAt(4) == input.charAt(7);
        } else {
            return false;
        }
    }

    /**
     * Parse user input date string.
     *
     * @return String of formatted user input date.
     */
    private static String parseDate(String input) {
        LocalDate date = LocalDate.parse(input);
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Prints double line border.
     */
    private static void printDoubleLineBorder() {
        System.out.println("==================================================");
    }

    /**
     * Prints single line border.
     */
    private static void printSingleLineBorder() {
        System.out.println("--------------------------------------------------");
    }
}
