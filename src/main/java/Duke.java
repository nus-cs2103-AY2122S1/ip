import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Duke {
    private final String HORIZONTAL_LINE = "\t____________________________________________________________\n";
    private final String MESSAGE_WELCOME = "Hi! I'm Sora. How can I help you?";
    private final String MESSAGE_EXIT = "Have a nice day! Good bye XD";
    private final String MESSAGE_LIST = "Here are the tasks in your list:";
    private final String MESSAGE_DONE = "Congrats! You have accomplished the following task:";
    private final String MESSAGE_ADD = "Alright, new task added to the list:";
    private final String MESSAGE_DELETE = "Sure, I've deleted this task:";
    
    private final ArrayList<Task> listTasks = new ArrayList<>();
    
    /**
     * Main body of the bot.
     */
    public void run() {
        // Print welcome message
        printMessage(MESSAGE_WELCOME);
        
        // Setup scanner for user input
        Scanner sc = new Scanner(System.in);
        String input;
        
        // Main body of chat box, each if else handles one type of command
        while (!(input = sc.nextLine().trim()).equals("bye")) {
            interpretCommand(input);
        }
        
        // Close off scanner
        sc.close();
        
        // Print good bye message
        printMessage(MESSAGE_EXIT);
    }
    
    private void interpretCommand(String command) {
        try {
            if (command.equals("list")) {
                printFullList();
            } else if (command.matches("^done( .*)?")) {
                taskDone(command);
            } else if (command.matches("^todo( .*)?")) {
                addTodo(command);
            } else if (command.matches("^deadline( .*)?")) {
                addDeadline(command);
            } else if (command.matches("^event( .*)?")) {
                addEvent(command);
            } else if (command.matches("^delete( .*)?")) {
                taskDelete(command);
            } else {
                throw new UnknownCommandException();
            }
        } catch (DukeException e) {
            printMessage(e.getMessage());
        }
    }
    
    /**
     * Deletes the task with the same task number as specified by user.
     *
     * @param command command entered by user (delete [task number])
     * @throws IllegalFormatException if user gives empty or invalid task number
     * @throws TaskNotFoundException if the task specified by the task number does not exists
     */
    private void taskDelete(String command) throws IllegalFormatException, TaskNotFoundException {
        // Throw exception if user gives empty or invalid task number
        validateCommand(command, "^delete [0-9]+", "delete [task number]");
        
        int taskNumber;
        
        try {
            taskNumber = Integer.parseInt(command.substring(7)) - 1;
        } catch (NumberFormatException e) {
            throw new TaskNotFoundException();
        }
        
        // Throw exception if taskNumber is invalid
        if (taskNumber < 0 || taskNumber >= listTasks.size()) {
            throw new TaskNotFoundException();
        }
        
        // Delete the task
        Task task = listTasks.remove(taskNumber);
        
        // Display message
        printMessage(MESSAGE_DELETE + "\n  " + task + "\n" + "You still have " + listTasks.size() + " tasks in the list.");
    }
    
    /**
     * Marks the task with the same task number as specified by user as done.
     *
     * @param command command entered by user (done [task number])
     * @throws IllegalFormatException if user gives empty or invalid task number
     * @throws TaskNotFoundException if the task specified by the task number does not exists
     */
    private void taskDone(String command) throws IllegalFormatException, TaskNotFoundException {
        // Throw exception if user gives empty or invalid task number
        validateCommand(command, "^done [0-9]+", "done [task number]");
        
        int taskNumber;
        
        try {
            taskNumber = Integer.parseInt(command.substring(5)) - 1;
        } catch (NumberFormatException e) {
            throw new TaskNotFoundException();
        }
        
        // Throw exception if taskNumber is invalid
        if (taskNumber < 0 || taskNumber >= listTasks.size()) {
            throw new TaskNotFoundException();
        }
        
        // Mark the task as done
        Task task = listTasks.get(taskNumber);
        task.markAsDone();
        
        // Display message
        printMessage(MESSAGE_DONE + "\n  " + task);
    }
    
    /**
     * Adds a new event to the list of tasks.
     *
     * @param command command entered by user (event [description] /at [time])
     * @throws IllegalFormatException if user inputs an invalid command
     */
    private void addEvent(String command) throws IllegalFormatException {
        String correctFormat = "event [description] /at [dd/MM/yy] /from [HHmm] /to [HHmm]";
        
        // Throw exception if command does not follow format
        validateCommand(command, "^event .* /at .* /from .* /to .*", correctFormat);
        
        // Separate info and trim each of them
        String[] info = command.substring(6).split("/at|/from|/to");
        info = Arrays.stream(info).map(String::trim).toArray(String[]::new);
        
        LocalDate date;
        LocalTime startTime, endTime;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("Hmm");
        
        // Throw exception if command does not follow format
        try {
            date = LocalDate.parse(info[1], DateTimeFormatter.ofPattern("d/M/yy"));
            startTime = LocalTime.parse(info[2], timeFormatter);
            endTime = LocalTime.parse(info[3], timeFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalFormatException(correctFormat);
        }
        
        // Throw exception if start time is later than end time
        if (startTime.isAfter(endTime)) {
            throw new IllegalFormatException(correctFormat);
        }
        
        // Add new event
        Task newTask = new Event(info[0], date, startTime, endTime);
        addTask(newTask);
    }
    
    /**
     * Adds a new deadline to the list of tasks.
     *
     * @param command command entered by user (deadline [description] /by [time])
     * @throws IllegalFormatException if user inputs an invalid command
     */
    private void addDeadline(String command) throws IllegalFormatException {
        String correctFormat = "deadline [description] /by [dd/MM/yy] [HHmm]";
        
        // Throw exception if command does not follow format
        validateCommand(command, "^deadline .* /by .*", correctFormat);
        
        // Separate info and trim each of them
        String[] info = command.substring(9).split("/by");
        info = Arrays.stream(info).map(String::trim).toArray(String[]::new);
        
        LocalDateTime dateTime;
        
        // Throw exception if command does not follow format
        try {
            dateTime = LocalDateTime.parse(info[1], DateTimeFormatter.ofPattern("d/M/yy Hmm"));
        } catch (DateTimeParseException e) {
            throw new IllegalFormatException(correctFormat);
        }
        
        // Add new deadline
        Task newTask = new Deadline(info[0], dateTime);
        addTask(newTask);
    }
    
    /**
     * Adds a new todo to the list of tasks.
     *
     * @param command command entered by user (todo [description])
     * @throws IllegalFormatException if user inputs an invalid command
     */
    private void addTodo(String command) throws IllegalFormatException {
        // Throw exception if command does not follow format
        validateCommand(command, "^todo .*", "todo [description]");
        
        // Add new todo
        Task newTask = new Todo(command.substring(5).trim());
        addTask(newTask);
    }
    
    /**
     * Adds a new task to the list of tasks. Helper method.
     *
     * @param newTask new task to be added
     */
    private void addTask(Task newTask) {
        // Add the newly created task into list of tasks
        listTasks.add(newTask);
        
        // Display message
        printMessage(MESSAGE_ADD + "\n  " + newTask + "\n" + "Now you have " + listTasks.size() + " tasks in the list.");
    }
    
    private void validateCommand(String command, String regex, String format) throws IllegalFormatException {
        if (!command.matches(regex)) {
            throw new IllegalFormatException(format);
        }
    }
    
    /**
     * Prints all the tasks in the list.
     *
     * @throws EmptyListException if the list of tasks is empty
     */
    private void printFullList() throws EmptyListException {
        // Throw exception if list is empty
        if (listTasks.size() == 0) {
            throw new EmptyListException();
        }
        
        StringBuilder msg = new StringBuilder();
        int size = listTasks.size();
        
        // Reformat the list of tasks into a string
        for (int i = 0; i < size - 1; i++) {
            msg.append(i + 1).append(". ").append(listTasks.get(i)).append("\n");
        }
        
        msg.append(size).append(". ").append(listTasks.get(size - 1));
        
        // Display message
        printMessage(MESSAGE_LIST + "\n" + msg);
    }
    
    /**
     * Prints a horizontal line, followed by the message on a newline, then another horizontal line on a newline. Each
     * newline will be prepended with a tab.
     * <p></p>
     * It looks like the following:
     * <br>
     * -----------
     * <br>
     * message
     * <br>
     * -----------
     *
     * @param msg message to be displayed
     */
    private void printMessage(String msg) {
        String format = HORIZONTAL_LINE + "\t%s\n" + HORIZONTAL_LINE;
        System.out.printf(format, msg.replaceAll("\n", "\n\t"));
    }
    
    public static void main(String[] args) {
        new Duke().run();
    }
}
