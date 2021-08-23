import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Class encapsulating a Duke and its commands.
 */
public class Duke {
    /**
     * Field for duke to keep track of task list.
     */
    private TaskList taskList;
    private FileManager fileManager;

    /**
     * Constructor for Duke
     */
    public Duke(TaskList taskList, FileManager fileManager) {
        this.taskList = taskList;
        this.fileManager = fileManager;
    }

    /**
     * Commands that Duke might use.
     */
    private enum Commands {
        GREET(String.format("Hello! I'm Duke\n%4sWhat can I do for you?", " ")),
        ADD("Got it. I've added this task:"),
        DONE("Nice! I've marked this task as done:"),
        DELETE("Noted. I've removed this task:"),
        LIST("Here are the tasks in your list:"),
        EXIT("Bye. Hope to see you again soon!");

        private final String command;

        private Commands(String command) {
            this.command = command;
        }

        public void printCommand() {
            System.out.println(String.format("%4s%s", " ", this.command));
        }
    }

    /**
     * Dividing line for formatting Duke's replies.
     */
    private void divider() {
        StringBuilder builder = new StringBuilder(100);
        Stream.generate(() -> '-').limit(60).forEach(e -> builder.append(e));

        String line = String.format("%4s+%s+\n", " ", builder.toString());
        System.out.println(line);
    }

    /**
     * Method that prints Duke's greetings.
     */
    private void greet() {
        divider();
        Commands.GREET.printCommand();
        divider();
    }

    /**
     * Method that prints Duke's exit message.
     */
    private void exit() {
        divider();
        Commands.EXIT.printCommand();
        divider();
    }

    /**
     * Method that prints the current tasks in the task list.
     */
    private void returnTaskList() {
        divider();
        Commands.LIST.printCommand();
        System.out.println(taskList);
        divider();
    }

    /**
     * Method for Duke to handle invalid inputs by the user.
     *
     * @param input The user input to Duke.
     */
    private void handleInvalidInputs(String input) {
        try {
            switch (input) {
                case "todo": // fallthrough
                case "deadline": // fallthrough
                case "event": {
                    System.out.println(

                    );
                    throw new DukeException(
                            String.format(
                                    "☹ OOPS!!! The description of a %s cannot be empty.",
                                    input
                            )
                    );
                }
                default:
                    throw new DukeException(
                            "☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                    );
            }
        } catch (DukeException e) {
            divider();
            System.out.println(String.format("%4s%s",
                    " ", e.getMessage()));
            divider();
        }
    }

    /**
     * Method for Duke to update the task list according to the given tasks.
     *
     * @param command The command that specifies the type of task and its description.
     */
    private void updateTaskList(String command) {
        // The type of the task indicated before the first space.
        try {
            int indexOfFirstSpace = command.indexOf(" ");

            // Only got one word or no description entered.
            if (indexOfFirstSpace == -1) {
                handleInvalidInputs(command);
                return;
            }
            String taskType = command.substring(0, indexOfFirstSpace);
            String description = command.substring(indexOfFirstSpace + 1);

            String customPattern ="dd-MM-yyyy";
            DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern(customPattern);
            DateTimeManager dateTimeManager = new DateTimeManager(customFormatter);

            Task newTask;
            String dateTime;
            LocalDate date;

            switch (taskType) {
                case "todo":
                    newTask = new ToDo(description);
                    break;
                case "deadline":
                    int deadlineIndex = description.indexOf("/by");
                    dateTime = description.substring(deadlineIndex + 4);
                    date = dateTimeManager.parseDateTime(dateTime);
                    newTask = new Deadline(description.substring(0, deadlineIndex),
                            date);
                    break;
                case "event":
                    int timeIndex = description.indexOf("/at");
                    dateTime = description.substring(timeIndex + 4);
                    date = dateTimeManager.parseDateTime(dateTime);
                    newTask = new Event(description.substring(0, timeIndex),
                            date);
                    break;
                default:
                    throw new DukeException(
                            "☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                    );

            }

            divider();
            this.taskList = this.taskList.add(newTask);
            Commands.ADD.printCommand();
            System.out.println(
                    String.format("%5s%s\n%4s%s", " ", newTask,
                            " ", this.taskList.status())
            );
            divider();
            fileManager.addTaskToFile(newTask);
        } catch (DukeException e) {
            divider();
            System.out.println(String.format("%4s%s",
                    " ", e.getMessage()));
            divider();
        }
    }

    /**
     * Method for Duke to mark the respective tasks as completed.
     *
     * @param index Index of the task to be deleted.
     */
    private void markTaskAsCompleted(int index) throws DukeException {
        boolean isValid = this.taskList.isValidTaskIndex(index);
        divider();
        if (isValid) {
            String toUpdate = this.taskList.getTask(index).toString();
            Task task = this.taskList.markTaskAsCompleted(index);
            Commands.DONE.printCommand();
            System.out.println(
                    String.format("%6s%s\n%4s%s", " ", task,
                            " ", this.taskList.status())
            );
            fileManager.markTaskAsCompleted(task.toString(), toUpdate);
        } else {
            throw new DukeException("There is no such task.");
        }
        divider();
    }

    /**
     * Method for Duke to delete the corresponding task.
     *
     * @param index Index of the task to be deleted.
     */
    private void deleteTask(int index) throws DukeException {
        boolean isValid = taskList.isValidTaskIndex(index);
        divider();
        if (isValid) {
            Task task = taskList.getTask(index);
            this.taskList = this.taskList.deleteTask(index);
            Commands.DELETE.printCommand();
            System.out.println(
                    String.format("%6s%s\n%4s%s", " ", task,
                            " ", taskList.status())
            );
            fileManager.deleteTaskFromFile(this.taskList);
        } else {
            throw new DukeException("There is no such task.");
        }
        divider();
    }


    /**
     * Runs the Duke chatbot.
     */
    private void run() {

        this.taskList = fileManager.loadData(this.taskList);

        // Greeting the user
        greet();

        // Taking in commands
        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.nextLine().strip();
            if (command.equals("bye")) {
                exit();
                break;
            } else if (command.equals("list")) {
                returnTaskList();
            } else if (command.startsWith("done")
                    || command.startsWith("delete")) {
                try {
                    String[] arrOfCommandWords = command.split(" ", 2);
                    if (arrOfCommandWords.length <= 1) {
                        // No task specified.
                        throw new DukeException("Please enter the task index.");
                    }
                    int taskIndex = Integer.parseInt(arrOfCommandWords[1]) - 1;
                    if (command.startsWith("done")) {
                        markTaskAsCompleted(taskIndex);
                    } else {
                        deleteTask(taskIndex);
                    }
                } catch (NumberFormatException|DukeException e){
                    divider();
                    System.out.println(String.format("%4s%s",
                            " ", e.getMessage()));
                    divider();
                } finally {
                    // TODO: implement cleanup
                }
            } else {
                // Add the task to the task list
                updateTaskList(command);
            }
        }
        sc.close();
    }

    /**
     * Main method to execute Duke's functions.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        String directoryPath = "./data";
        String filePath = "./data/duke.text";
        File directory = new File(directoryPath);
        System.out.println(directory.getAbsolutePath());
        // Check folder exists
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
                System.out.println("Successfully created directory.");
            } else {
                System.out.println("An error occurred");
            }
        }

        Duke duke = new Duke(new TaskList(), new FileManager(filePath));
        duke.run();
    }
}
