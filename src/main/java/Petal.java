import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;

/**
 * The class for the Petal bot. It is able to respond to
 * a certain number of pre-determined commands in order to add certain
 * activities and track them.
 */
public class Petal {

    //The line used to display on the output
    public static final String LINE = "ꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤ"
                                      + "ꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤ"
                                      + "ꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤ";

    //Boolean representing if the user has said bye
    private boolean bye;
    //List of user inputted tasks
    private final List<Task> tasks;
    private final String folderPath = System.getProperty("user.dir") + "/PetalData";
    private final String filePath = folderPath + "/Tasks.txt";
    private boolean savedProperly;

    /**
     * Constructor for the Duke class
     */
    public Petal() {
        bye = false;
        tasks = new ArrayList<>();
    }

    /**
     * Method to give the start message and to run the bot.
     */
    public void run() {
        createDirectory(); //IOException already handled internally
        Scanner scanner = new Scanner(System.in);
        String logo = "Welcome to Petal (•◡•)/";
        String logo2 = "\nI am the best chat bot you'll meet! Don't be shy, say something! :P";
        printMessage(logo + logo2);
        while (!bye) {
            String message = scanner.nextLine();
            handleInput(message.trim().toLowerCase());
        }
        scanner.close();
    }

    /**
     * Method that formats the message to be displayed. It splits the input and takes
     * the first word (assumed to be command if format followed) and reacts accordingly
     * @param message User input
     */
    public void handleInput(String message) {
        try {
            message += " "; //So blank inputs can be handled
            String command = message.substring(0, message.indexOf(" "));
            String formatted = message.substring(message.indexOf(' ') + 1).trim();
            switch (command) { //Checks first word in string
                case "list":
                    printMessage(printList());
                    break;
                case "bye":
                    goodBye();
                    break;
                case "done":
                    markTaskAsDone(formatted);
                    break;
                case "delete":
                    deleteTask(formatted);
                    break;
                case "todo":
                    handleTasks("todo", formatted);
                    break;
                case "deadline":
                    handleTasks("deadline", formatted);
                    break;
                case "event":
                    handleTasks("event", formatted);
                    break;
                default: //All messages here do not meet the required format or are unintelligible
                    throw new InvalidInputException("I do not understand what you mean :(");
            }
        } catch (PetalException e) {
            printMessage(e.getMessage());
            requiredFormat();
        }
    }

    /**
     * Method which helps guide the user to display the required format if the user has
     * used the wrong format
     */
    public void requiredFormat() {
        String todo = "Use 'todo <insert activity>' to create a to-do!";
        String deadline = "\nUse 'deadline <insert activity> /by <insert deadline>' "
                          + "to create an activity with a deadline!";
        String event = "\nUse 'event <insert activity> /at <insert start/end time>' "
                       + "to create an activity with a start/end time!";
        String delete = "\nUse 'delete <insert task number> to delete a task!";
        String done = "\nUse 'done <insert task number>' to mark task as done!";
        printMessage(todo + deadline + event + delete + done);
    }

    /**
     * Method to handle the tasks, depending on the command given
     * @param type The type of task: To.Do, deadline, event
     * @param message The desc/time of the task
     * @throws EmptyDescException Thrown when the task lacks a description
     * @throws InvalidInputException Thrown when an invalid format is given or when a time is not given
     */
    public void handleTasks(String type, String message) throws EmptyDescException, InvalidInputException {
        Task task;
        String[] deadlineEvent = type.equals("deadline") ? message.split("/by")
                                                         : message.split("/at");
        if (message.isBlank() || deadlineEvent[0].isBlank()) {
            throw new EmptyDescException("The description cannot be empty! Enter a valid one! :(");
        }
        if ((type.equals("deadline") || type.equals("event")) && deadlineEvent.length < 2) {
            //No time given or the command /by or /at wasn't given by the user
            throw new InvalidInputException("The format used was wrong! Try again :(");
        }
        switch (type) {
            case "todo":
                task = new ToDo(message);
                break;
            case "deadline":
                task = new Deadline(deadlineEvent[0], deadlineEvent[1]);
                break;
            default: //Represents the Event task
                task = new Event(deadlineEvent[0], deadlineEvent[1]);
                break;
        }
        addTask(task);
    }

    /**
     * Overloaded method to add previously saved tasks to the list of tasks
     * @param addTasks The arraylist of previously saved tasks
     */
    public void addTask(ArrayList<Task> addTasks) {
        tasks.addAll(addTasks);
        printMessage("Welcome back! It definitely is good to see you again :D");
    }

    /**
     * Method to add a task to list of tasks
     * @param task The task to be added
     */
    public void addTask(Task task) {
        try {
            tasks.add(task);
            String plural = (tasks.size() + 1) > 0 ? " tasks!" : " task!";
            printMessage("Okay. I've added this task:\n" + task + "\nYou now have " + tasks.size()
                    + plural);
            saveTasks();
        } catch (IOException e) {
            printMessage("Sorry, the tasks couldn't be saved :/");
        }
    }

    /**
     * Method to delete a task from the list of tasks
     * @param index The message given by the user input
     * @throws InvalidInputException Thrown if no index inputted by the user or
     *                               when index is out-of-bounds/not valid int or when
     *                               desc is empty
     */
    public void deleteTask(String index) throws InvalidInputException, EmptyDescException {
        try {
            int indexOfTask = Integer.parseInt(index) - 1;
            Task toBeDeleted = tasks.remove(indexOfTask);
            printMessage("Okay. I've deleted this task:\n"
                         + toBeDeleted
                         + "\nYou now have " + tasks.size() + " task(s)!");
            saveTasks();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidInputException("Invalid task number given! Please enter another value!", e);
        } catch (IOException e) {
            printMessage("Sorry, the tasks couldn't be saved :/");
        }
    }

    public boolean retrieveTasks() {
        try {
            File tasks = new File(filePath);
            Scanner scanner = new Scanner(tasks);
            ArrayList<Task> toBeAdded = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String taskLine = scanner.nextLine();
                char Task = taskLine.charAt(4);
                String taskDesc = taskLine.substring(10);
                switch (Task) {
                    case 'T':
                        ToDo todo = new ToDo(taskDesc);
                        toBeAdded.add(todo);
                        break;
                    case 'D':
                        String[] split = taskDesc.split("by");
                        Deadline deadline = new Deadline(split[0], split[1]);
                        toBeAdded.add(deadline);
                        break;
                    case 'E':
                        String[] splitEvent = taskDesc.split("at");
                        Event event = new Event(splitEvent[0], splitEvent[1]);
                        toBeAdded.add(event);
                        break;
                }
            }
            addTask(toBeAdded);
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    public void createDirectory() {
        try {
            if (retrieveTasks()) {
                savedProperly = true;
                return;
            }
            Path path = Paths.get(folderPath);
            Files.createDirectories(path);
            File petalData = new File(filePath);
            petalData.createNewFile();
            savedProperly = true;
        } catch (IOException e) {
            savedProperly = false;
            printMessage("Something when wrong whilst creating/accessing these files :/\n"
                          + "As such, the saving/retrieval of tasks will be turned off. Sorry!");
        }
    }

    public void saveTasks() throws IOException {
        if (!savedProperly) {
            return;
        }
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(printList());
        fileWriter.close();
    }

    /**
     * Method for Petal to say goodbye
     */
    public void goodBye() {
        bye = true;
        printMessage("You're leaving :( I hope you return soon!");
    }

    /**
     * Method that returns the string representations of the tasks
     * @return String containing the number, type, and description of tasks
     */
    public String printList() {
        if (tasks.size() == 0)
            return "No items in list yet!";
        int count = 1;
        StringBuilder list = new StringBuilder("");
        for (Task m : tasks) {
            //I do this check to ensure there isn't a newline at the top
            list = count == 1 ? list.append(count++).append(". ").append(m)
                              : list.append("\n").append(count++).append(". ").append(m);
        }
        return list.toString();
    }

    /**
     * Method to mark a particular task as done
     * @param indexOfTask String representation of the index of the task
     * @throws IndexOutOfBoundsException Thrown if string is not within size of list
     * @throws NumberFormatException Thrown if string cannot be converted into valid int
     */
    public void markTaskAsDone(String indexOfTask) throws EmptyDescException, InvalidInputException {
        try {
            Task taskToBeCompleted = tasks.get(Integer.parseInt(indexOfTask) - 1);
            taskToBeCompleted.taskDone();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidInputException("Invalid task number given! Please enter another value!", e);
        }
    }

    /**
     * Method to add the indentation to the message
     * @param message Message to be printed
     */
    public void printMessage(String message) {
        System.out.println(LINE + "\n" + message + "\n" + LINE);
    }

    public static void main(String[] args) throws IOException {
        Petal petal = new Petal();
        petal.run();
    }
}

