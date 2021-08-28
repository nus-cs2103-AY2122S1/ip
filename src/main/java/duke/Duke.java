package duke;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import duke.command.Command;
import duke.data.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

//todo command to print deadlines/events occuring on specific date

/**
 * The Duke programme implements a bot that help users to record the tasks they have.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private String storagePath;
    private File dataFile;

    private static final String STORAGE_PATH = "data/duke.txt";
    private static final File DATA_FILE = new File(STORAGE_PATH);

    public Duke(String storagePath) {
        this.storagePath = storagePath;
        this.dataFile = new File(storagePath);
        ui = new Ui();
        storage = new Storage(storagePath);
        try {
            boolean isFileCreated = dataFile.createNewFile();
            if (!isFileCreated) {
                taskList = new TaskList(storage.load());
            } else {
                taskList = new TaskList();
            }
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

//    private static List<duke.task.Task> initialiseBot() {
//        List<duke.task.Task> taskList = new ArrayList<>();
//        duke.storage.Storage dataManager = new duke.storage.Storage();
//
//        try {
//            boolean isFileCreated = DATA_FILE.createNewFile();
//            if (!isFileCreated) {
//                taskList = dataManager.txtToList(DATA_FILE);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        String message = "Hello! I'm duke.Duke\n"
//                + "What can I do for you?";
//        System.out.println(message);
//
//        return taskList;
//    }
//
//    private static void closeBot() {
//        String message = "Bye. Hope to see you again soon!";
//        System.out.println(message);
//    }
//
//    private static duke.task.Task getTask(String message, String taskType) throws duke.data.exceptions.EmptyTaskDescriptionException, duke.data.exceptions.InvalidDateAndTimeException, duke.data.exceptions.InvalidInputException {
//
//        switch (taskType) {
//            case TODO:
//                if (message.trim().equals(TODO)) {
//                    throw new duke.data.exceptions.EmptyTaskDescriptionException("Empty duke.task.Todo Description", TODO);
//                } else {
//                    if (!Character.isWhitespace(message.charAt(4))) {
//                        throw new duke.data.exceptions.InvalidInputException("invalid input");
//                    } else {
//                        String taskName = message.substring(TODO.length() + 1);
//                        return new duke.task.Todo(taskName);
//                    }
//                }
//            case DEADLINE:
//                if (message.trim().equals(DEADLINE)) {
//                    throw new duke.data.exceptions.EmptyTaskDescriptionException("Empty duke.task.Deadline Description", DEADLINE);
//                } else {
//                    String deadline = getDateAndTime(message, DEADLINE);
//
//                    int startingIndex = message.indexOf(" ");
//                    int endingIndex = message.indexOf("/");
//                    String taskName = message.substring(startingIndex + 1, endingIndex - 1);
//
//                    //todo deadline return book being invalid input rather than invalid date.
//
//                    return new duke.task.Deadline(taskName, deadline);
//                }
//            case EVENT:
//                if (message.trim().equals(EVENT)) {
//                    throw new duke.data.exceptions.EmptyTaskDescriptionException("Empty duke.task.Event Description", EVENT);
//                } else {
//                    String eventTime = getDateAndTime(message, EVENT);
//
//                    int startingIndex = message.indexOf(" ");
//                    int endingIndex = message.indexOf("/");
//                    String taskName = message.substring(startingIndex + 1, endingIndex - 1);
//
//                    return new duke.task.Event(taskName, eventTime);
//                }
//        }
//
//        return new duke.task.Task();
//    }

    /**
     * Executes a Duke object.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        Scanner sc = new Scanner(System.in);

        while (!isExit) {
            try {
                String command = ui.readCommand(sc);
                ui.showLine();
                Parser p = new Parser();
                Command c = p.parse(command);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

        sc.close();

    }

    public static void main(String[] args) {
        new Duke(STORAGE_PATH).run();
    }

//        boolean status = true;
//        List<duke.task.Task> tasks = initialiseBot();
//
//        Scanner sc = new Scanner(System.in);
//
//        while (status) {
//            String message = sc.nextLine();
//
//            if (message.equals("bye")) {
//                status = false;
//                closeBot();
//            } else if (message.equals("list")) {
//                printTasks(tasks);
//            } else if (message.startsWith("done")) {
//                markTaskAsDone(message, tasks);
//            } else if (message.startsWith("delete")) {
//                deleteTask(message, tasks);
//            } else if (message.startsWith("todo")) {
//                addTaskFromCommand(tasks, message, TODO);
//            } else if (message.startsWith("deadline")) {
//                addTaskFromCommand(tasks, message, DEADLINE);
//            } else if (message.startsWith("event")) {
//                addTaskFromCommand(tasks, message, EVENT);
//            } else {
//                try {
//                    throw new duke.data.exceptions.InvalidInputException("invalid input");
//                } catch (duke.data.DukeException e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//
//        }
//
//        sc.close();
//    }
//
//    private static void addTaskFromCommand(List<duke.task.Task> tasks, String message, String taskType) {
//        duke.task.Task task = null;
//        try {
//            task = getTask(message, taskType);
//            if (task.isEmpty()) {
//                throw new duke.data.exceptions.InvalidInputException("error");
//            }
//            addTask(task, tasks);
//        } catch (duke.data.DukeException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    private static void deleteTask(String message, List<duke.task.Task> tasks) {
//        try {
//            int taskPosition = getTaskNumber(message) - 1;
//            if (taskPosition >= tasks.size()) {
//                throw new duke.data.exceptions.InvalidInputException("invalid task number entered");
//            } else {
//                duke.task.Task removedTask = tasks.get(taskPosition);
//                tasks.remove(taskPosition);
//
//                //TODO make better
//                duke.storage.Storage dataManager = new duke.storage.Storage(tasks);
//                dataManager.update(tasks);
//
//                String displayedMessage = "Noted. I've removed this task:\n"
//                        + "  " + removedTask.toString() + "\n"
//                        + getTotalTaskString(tasks);
//                System.out.println(displayedMessage);
//            }
//        } catch (duke.data.DukeException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    private static void addTask(duke.task.Task task, List<duke.task.Task> tasks) {
//        tasks.add(task);
//
//        //TODO make better
//        duke.storage.Storage storage = new duke.storage.Storage(tasks);
//        storage.update(tasks);
//
//        String displayedMessage = getAddedSuccessMessage(task, tasks);
//        System.out.println(displayedMessage);
//    }
//
//    private static String getTotalTaskString(List<duke.task.Task> tasks) {
//        return String.format("Now you have %d tasks in the list.", getTotalTaskNumber(tasks));
//    }
//
//    private static String getAddedSuccessMessage(duke.task.Task task, List<duke.task.Task> tasks) {
//        String successMessage = "Got it. I've added this task:";
//        String taskString = task.toString();
//        String result = successMessage + "\n"
//                + "  " + taskString + "\n"
//                + getTotalTaskString(tasks);
//        return result;
//    }
//
//    private static int getTotalTaskNumber(List<duke.task.Task> tasks) {
//        return tasks.size();
//    }
//
//    private static void checkValidTaskName(String message) throws duke.data.exceptions.InvalidInputException {
//        int startingIndex = message.indexOf(" ");
//        int endingIndex = message.indexOf("/");
//
//        if (startingIndex < 0 || endingIndex - startingIndex <= 1) {
//            throw new duke.data.exceptions.InvalidInputException("invalid input");
//        }
//    }
//
//    private static String getDateAndTime(String message, String taskType) throws duke.data.exceptions.InvalidDateAndTimeException, duke.data.exceptions.InvalidInputException {
//        int startingIndex;
//
//        checkValidTaskName(message);  //todo move to better slot?
//
//        switch (taskType) {
//            case DEADLINE:
//                startingIndex = message.indexOf("/by ");
//
//                if (startingIndex < 0 || startingIndex + 3 == message.length() - 1) {
//                    throw new duke.data.exceptions.InvalidDateAndTimeException("missing deadline");
//                }
//
//                duke.data.DateAndTime dateAndTime1 = new duke.data.DateAndTime(message);
//                return dateAndTime1.getReformattedDateAndTime();
//            case EVENT:
//                startingIndex = message.indexOf("/at ");
//
//                if (startingIndex < 0 || startingIndex + 3 == message.length() - 1) {
//                    throw new duke.data.exceptions.InvalidDateAndTimeException("missing event time");
//                }
//
//                duke.data.DateAndTime dateAndTime2 = new duke.data.DateAndTime(message);
//                return dateAndTime2.getReformattedDateAndTime();
//            }
//        return "";
//    }
//
//    private static int getTaskNumber (String message) throws duke.data.exceptions.InvalidInputException {
//        String numberString = "";
//        for (int i = 0; i < message.length(); i++) {
//            char currentChar = message.charAt(i);
//            if (!numberString.isEmpty() && Character.isWhitespace(currentChar)) {
//                break; //task number string complete
//            } else if (Character.isDigit(currentChar)) {
//                numberString += message.charAt(i);
//            } else {}
//        }
//
//        int number;
//        if (numberString.isEmpty()) {
//            throw new duke.data.exceptions.InvalidInputException("invalid task number entered");
//        } else {
//            number = Integer.parseInt(numberString);
//        }
//        return number;
//    }
//
//    private static void markTaskAsDone(String message, List<duke.task.Task> tasks) {
//        try {
//            int taskPosition = getTaskNumber(message) - 1;
//            if (taskPosition >= tasks.size()) {
//                throw new duke.data.exceptions.InvalidInputException("invalid task number entered");
//            } else {
//                duke.task.Task taskMarked = tasks.get(taskPosition);
//                taskMarked.markAsDone();
//
//                //TODO make better
//                duke.storage.Storage storage = new duke.storage.Storage(tasks);
//                storage.update(tasks);
//
//                String displayedMessage = "Nice! I've marked this task as done:\n"
//                        + "  "
//                        + taskMarked.toString();
//                System.out.println(displayedMessage);
//            }
//        } catch (duke.data.DukeException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    private static void printTasks(List<duke.task.Task> tasks) {
//        String message = "Here are the tasks in your list:";
//        System.out.println(message);
//        for (int i = 1; i <= tasks.size(); i++) {
//            duke.task.Task currentTask = tasks.get(i - 1);
//            String displayedTask = i + "." + currentTask.toString();
//            System.out.println(displayedTask);
//        }
//    }
//





}
