import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.List;
import java.io.File;

/**
 * The class for the Petal bot. It is able to respond to
 * a certain number of pre-determined commands in order to add certain
 * activities and track them.
 */
public class Petal {

    //Boolean representing if the user has said bye
    private boolean bye;
    //List of user inputted tasks
    private final List<Task> tasks;
    //Relative path of the folder containing Tasks.txt
    private final String folderPath = System.getProperty("user.dir") + "/PetalData";
    //Relative path of the txt file with the taks
    private final String filePath = folderPath + "/Tasks.txt";
    //Boolean representing if saving should be performed
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
                case "date":
                    tasksOnThisDay(formatted);
                    break;
                case "event":
                    handleTasks("event", formatted);
                    break;
                default: //All messages here do not meet the required format or are unintelligible
                    throw new InvalidInputException("I do not understand what you mean :(");
            }
        } catch (PetalException e) {
            printMessage(e.getMessage());
            printMessage(Responses.REQUIRED_FORMAT);
        }
    }

    public void tasksOnThisDay(String date) throws InvalidInputException {
        try {
            String result = Deadline.deadlinesOnDate(date);
            printMessage(result);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("The date/time format used was wrong! Try again :(");
        }

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
                task = new ToDo(message, false);
                break;
            case "deadline":
                try {
                    task = new Deadline(deadlineEvent[0], deadlineEvent[1], false);
                } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                    throw new InvalidInputException("The date/time format used was wrong! Try again :(");
                }
                break;
            default: //Represents the Event task
                task = new Event(deadlineEvent[0], deadlineEvent[1], false);
                break;
        }
        addTask(task);
    }

    /**
     * Overloaded method to add previously saved tasks to the list of tasks
     * @param addTasks The arraylist of previously saved tasks
     */
    public void addTask(ArrayList<Task> addTasks) {
        tasks.addAll(addTasks); //Add done ability
        printMessage(Responses.WELCOME_BACK);
    }

    /**
     * Method to add a task to list of tasks
     * @param task The task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
        String plural = (tasks.size() + 1) > 0 ? " tasks!" : " task!";
        printMessage("Okay. I've added this task:\n" + task + "\nYou now have " + tasks.size()
                     + plural);
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
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidInputException("Invalid task number given! Please enter another value!", e);
        }
    }

    /**
     * Method to parse the text from Tasks.txt in tasks
     * @return True if tasks were retrieved, false if no tasks (new user) or exception occurred
     */
    public boolean retrieveTasks() {
        try {
            File tasks = new File(filePath);
            Scanner scanner = new Scanner(tasks);
            ArrayList<Task> toBeAdded = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String taskLine = scanner.nextLine();
                String[] components = taskLine.split("\\|");
                boolean isDone = Objects.equals(components[1], "X");
                switch (components[0]) {
                    case "T":
                        toBeAdded.add(new ToDo(components[2], isDone));
                        break;
                    case "D":
                        toBeAdded.add(new Deadline(components[2], components[3], isDone));
                        break;
                    case "E":
                        toBeAdded.add(new Event(components[2], components[3], isDone));
                        break;
                }
            }
            addTask(toBeAdded);
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Method to create the main PetalData folder, containing Tasks.txt
     */
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
            printMessage(Responses.START_MESSAGE);
        } catch (IOException e) {
            savedProperly = false;
            printMessage(Responses.FILE_ERROR);
        }
    }

    /**
     * Method to save the tasks. If the folder was not able to be created, Petal does not
     * save any of the tasks.
     * @throws IOException Thrown if tasks are not saved properly
     */
    public void saveTasks() throws IOException {
        if (!savedProperly) {
            return;
        }
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(formatForSaving());
        fileWriter.close();
    }

    /**
     * Method for Petal to say goodbye. In the case saveTasks() throws an error,
     * Petal does not save any of the tasks.
     */
    public void goodBye() {
        try {
            bye = true;
            saveTasks();
        } catch (IOException e) {
            printMessage(Responses.SAVE_ERROR);
        } finally {
            printMessage(Responses.GOODBYE);
        }
    }

    /**
     * Method that returns the string representations of the tasks
     * @return String containing the number, type, and description of tasks
     */
    public String printList() {
        if (tasks.size() == 0)
            return "No items in list yet!";
        int count = 1;
        StringBuilder list = new StringBuilder();
        for (Task m : tasks) {
            //I do this check to ensure there isn't a newline at the top
            if (count == 1) {
                list.append(count++).append(". ").append(m);
            } else {
                list.append("\n").append(count++).append(". ").append(m);
            }
        }
        return list.toString();
    }

    /**
     * Method that takes the tasks and returns a formatted string representation
     * which can be easily parsed by retrieveTasks() once the program is run again
     * @return Formatted string representation of all the user-added tasks
     */
    public String formatForSaving() {
        if (tasks.size() == 0) {
            return "";
        }
        int count = 1;
        StringBuilder result = new StringBuilder();
        for (Task m : tasks) {
            if (count == 1) {
                result.append(m.strForSaving());
            } else {
                result.append("\n").append(m.strForSaving());
            }
            count++;
        }
        return result.toString();
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
    public void printMessage(Responses message) {
        System.out.println(Responses.LINE + "\n" + message.toString() + "\n" + Responses.LINE);
    }

    /**
     * Method to add the indentation to the message
     * @param message Message to be printed
     */
    public void printMessage(String message) {
        System.out.println(Responses.LINE + "\n" + message + "\n" + Responses.LINE);
    }

    public static void main(String[] args) {
        Petal petal = new Petal();
        petal.run();
    }
}

