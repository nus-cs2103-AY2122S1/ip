package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DataFileChangedException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;

/**
 * Handles loading and saving tasks from the data file.
 */
public class Storage {


    /** Represents the deadline keyword. */
    private static final char DEADLINE = 'D';

    /** Represents the event keyword. */
    private static final char EVENT = 'E';

    /** Represents the todo keyword. */
    private static final char TODO = 'T';

    /** Data file. */
    private final File file;

    /** Expenses data file */
    private final File expensesFile;

    /**
     * Constructor for Storage class.
     *
     * @param filePath path to data file.
     */
    public Storage(String filePath, String expensesFilePath) {
        file = new File(filePath);
        expensesFile = new File(expensesFilePath);
    }

    /**
     * Saves and writes the current tasks to the data files.
     * If the file does not exist, it will create a new file.
     *
     * @param tasks contains all current tasks.
     * @throws IOException if the named file exists but is a directory
     * rather than a regular file, does not exist but cannot be created,
     * or cannot be opened for any other reason.
     */
    public void save(TaskList tasks) throws IOException {
        ArrayList<Task> taskList = tasks.getTaskList();
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        FileWriter fwExpenses = new FileWriter(expensesFile.getAbsoluteFile());

        int count = 1;
        for (Task task : taskList) {
            fw.write(task.parseToSave() + System.lineSeparator());
            if (task.hasExpenses()) {
                fwExpenses.write(count + "|" + task.formatExpensesToSave() + System.lineSeparator());
            }
            count++;
        }
        fw.flush();
        fw.close();
        fwExpenses.flush();
        fwExpenses.close();
    }

    /**
     * Loads data from data files.
     * If the data file does not exist, a new data file will be created.
     *
     * @return an arraylist of tasks which contains any task read from the data file.
     * @throws IOException  if the named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason.
     * @throws DataFileChangedException if the data file was changed and any entry contains a wrong format.
     */
    public ArrayList<Task> load() throws IOException, DataFileChangedException {
        file.getParentFile().mkdirs();
        expensesFile.getParentFile().mkdirs();
        expensesFile.createNewFile();

        ArrayList<Task> taskList = new ArrayList<>();

        // if file not present it creates a file, else it does nothing
        if (file.createNewFile()) {
            // exit method if a new file is created
            return taskList;
        }

        Scanner sc = new Scanner(file);

        try {
            int count = 1;
            while (sc.hasNext()) {
                String nextCommand = sc.nextLine();
                Task task;

                switch (extractTask(nextCommand)) {
                case DEADLINE:
                    task = extractDeadline(nextCommand);
                    break;
                case EVENT:
                    task = extractEvent(nextCommand);
                    break;
                case TODO:
                    task = extractTodo(nextCommand);
                    break;
                default:
                    throw new DataFileChangedException();
                }

                assert task != null : " Trying to add nothing as a Task in Storage";

                if (isMarkedDone(nextCommand)) {
                    task.markAsDone();
                }
                checkExpenses(count, task);
                taskList.add(task);
                count++;
            }
            sc.close();
            return taskList;
        } catch (IndexOutOfBoundsException | DateTimeParseException | NumberFormatException e) {
            throw new DataFileChangedException();
        }
    }

    /**
     * Checks the expenses of the chosen task and adds them to the task.
     *
     * @param taskIndex the current index of task.
     * @param task the task that expenses should be added to.
     * @throws IOException if the expenses data file cannot be loaded properly.
     */
    private void checkExpenses(int taskIndex, Task task) throws IOException {
        expensesFile.getParentFile().mkdirs();
        if (expensesFile.createNewFile()) {
            return;
        }
        Scanner sc = new Scanner(expensesFile);

        while (sc.hasNext()) {
            String nextLine = sc.nextLine();
            String[] parsedInfo = nextLine.split("\\|");
            int indexInDataFile = Integer.parseInt(parsedInfo[0]);
            if (indexInDataFile == taskIndex) {
                for (int i = 1; i <= parsedInfo.length - 2; i += 2) {
                    String purpose = parsedInfo[i];
                    float amount = Float.parseFloat(parsedInfo[i + 1]);
                    task.addExpenseToTask(purpose, amount);
                }
            }
        }
        sc.close();
    }

    /**
     * Checks if task from data file was marked done.
     *
     * @param input input from data file.
     * @return boolean that indicates if the task has been marked done.
     */
    private boolean isMarkedDone(String input) {
        return input.charAt(2) == 'X';
    }

    /**
     * Extracts the letter that indicates the type of task.
     *
     * @param input input from data file.
     * @return the letter indicating the type of task.
     */
    private char extractTask(String input) {
        return input.charAt(0);
    }

    /**
     * Extracts a todo from the data file in the proper format for duke to read.
     *
     * @param text the todo in the data file.
     * @return a new todo that represents the todo from the data file.
     */
    private Todo extractTodo(String text) {
        String[] parsedTodo = text.split("\\|");
        String description = parsedTodo[2];
        return new Todo(description);
    }

    /**
     * Extracts a deadline from the data file in the proper format for duke to read.
     *
     * @param text the deadline in the data file.
     * @return a new deadline that represents the deadline from the data file.
     */
    private Deadline extractDeadline(String text) {
        String[] parsedText = text.split("\\|");

        String description = parsedText[2];
        String by = parsedText[3];

        LocalDateTime dateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));

        return new Deadline(description, dateTime);
    }

    /**
     * Extracts an event from the data file in the proper format for duke to read.
     *
     * @param text the event in the data file.
     * @return a new deadline that represents the deadline from the data file.
     */
    private Event extractEvent(String text) {
        String[] parsedEvent = text.split("\\|");

        String description = parsedEvent[2];
        String date = parsedEvent[3];
        String startTime = parsedEvent[4];
        String endTime = parsedEvent[5];

        LocalDate finalDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMM d yyyy"));
        LocalTime finalStartTime = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("h:mm a"));
        LocalTime finalEndTime = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("h:mm a"));

        return new Event(description, finalDate, finalStartTime, finalEndTime);
    }
}
