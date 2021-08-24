import java.io.IOException;
import java.io.File;
import java.io.FileWriter;


import java.util.Scanner;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.DataFileChangedException;
import exception.DukeException;
import exception.EmptyCommandException;
import exception.EmptyListException;
import exception.IncorrectFormatException;
import exception.InvalidCommandException;
import exception.InvalidDateTimeException;
import exception.InvalidDurationException;
import exception.InvalidIndexException;
import exception.MessageEmptyException;

/**
 * Duke class that initialises the Duke chat bot.
 * The Duke class supports operators including
 * (i) run: runs the chat bot
 * (ii) greet: prints out a greeting when the chat bot runs
 * (iii) exit: ends the execution of the chat bot
 * (iv) addToList: adds a Task to the list of Tasks
 * (v) displayList: prints out the current list of Tasks
 * (vi) markDone: marks a Task as done
 * (vii) addDeadline: adds a Deadline to the list of Tasks
 * (viii) addTodo: adds a Todo to the list of Tasks
 * (ix) addEvent: adds an Event to the list of Tasks
 * (x) handleCommands: main logic for processing and executing various commands
 * like "list", "done", "deadline", "todo", "event" and other invalid commands
 */

public class Duke {

    /**
     * taskList is the list of Tasks
     */

    private final ArrayList<Task> taskList = new ArrayList<>();

    private final String FILE_PATH = "data.txt";

    /**
     * Runs the Duke chat bot.
     * It takes in user inputs and responds accordingly.
     * If a command is issued, the bot will execute the command if the appropriate message
     * follows the command.
     * Any invalid inputs are caught via custom Exceptions, thrown and printed for the user to see.
     * Invalid inputs include empty inputs, incorrect formats, invalid index, empty messages and
     * attempting to amend the list of Tasks when it is currently empty.
     */

    private void run() {

        greet();

        try {
            load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        String input;
        Scanner sc = new Scanner(System.in);

        // user input trimmed to remove unwanted spaces at the front and back of user input
        // allows for greater margin of error when typing in commands
        while(!(input = sc.nextLine().trim()).equals("bye")) {
            // continuously runs the bot as long as the "bye" command is not issued
            handleCommands(input);
        }

        sc.close(); // closes the Scanner

        try {
            save();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        exit();
    }

    /**
     * Prints out a greeting for the user when the bot is first ran.
     */

    private void greet() {
        System.out.println("__________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("__________________________________");
    }

    /**
     * Prints out a goodbye message when the bot is exited.
     */

    private void exit() {
        System.out.println("__________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("__________________________________");
    }

    /**
     * Adds a task to the list of Tasks with a confirmation message printed out after.
     * @param task The Task to be added to the list of Tasks
     */

    private void addToList(Task task) {
        taskList.add(task);

        System.out.println("Got it. I've added this task:");
        System.out.println("Added: " + task);
        String taskGrammar = (taskList.size() == 1) ? " task" : " tasks";
        System.out.println("Now you have " + taskList.size() + taskGrammar + " in the list.");
    }

    /**
     * Prints out the full contents of the list of Tasks.
     * @throws EmptyListException If the list of Tasks is empty and there is nothing to be printed.
     */

    private void displayList() throws EmptyListException {
        if (taskList.size() == 0) {
            throw new EmptyListException();
        }

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println(i + 1 + ". " + task);
        }
    }

    /**
     * Marks a current Task in the list of Tasks as Done.
     * @param taskIndex The index of the Task in the list of Tasks to be marked as Done.
     * @throws EmptyListException If the list of Tasks is empty and there is nothing to be marked as Done.
     * @throws InvalidIndexException If the index of the Task provided is out of range of the current list of Tasks.
     */

    private void markDone(String taskIndex) throws EmptyListException, InvalidIndexException {
        int intTaskIndex = Integer.parseInt(taskIndex) - 1; // -1 because user inputs start from 1 not 0
        int taskListSize = taskList.size();

        if (taskListSize == 0) {
            throw new EmptyListException();
        } else if (intTaskIndex < 0 || intTaskIndex >= taskListSize) {
            throw new InvalidIndexException(1, taskListSize, intTaskIndex + 1);
        }

        Task task = taskList.get(intTaskIndex);
        task.markAsDone();

        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Adds a Deadline to the list of Tasks.
     * @param deadline The Deadline to be added to the list of Tasks which is the whole input barring the command.
     * @throws IncorrectFormatException If the deadline command is used but a "/by" is not present in the message.
     */

    private void addDeadline(String deadline) throws IncorrectFormatException, InvalidDateTimeException, MessageEmptyException {
        String[] result = deadline.split("/by");

        if (result.length == 0) {
            throw new MessageEmptyException();
        } else if (result.length == 1) {
            // throws an error if "/by" is not present in the message
            throw new IncorrectFormatException("deadline", "/by");
        }

        String description = result[0].trim(); // trims the additional spaces to the left and right of "by"
        String by = result[1].trim(); // trims the additional spaces to the left and right of "by"

        LocalDateTime finalBy;

        try {
            // checks if the formats of the input date and time are correct
            finalBy = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }

        Deadline d = new Deadline(description, finalBy);
        addToList(d);
    }

    /**
     * Adds a Todo to the list of Tasks.
     * @param todo The Todo to be added to the list of Tasks.
     */

    private void addTodo(String todo) {
        Todo tempTask = new Todo(todo);
        addToList(tempTask);
    }

    /**
     * Adds an Event to the list of Tasks.
     * @param event The Event to be added to the list of Tasks, which is the entire user input barring the command.
     * @throws IncorrectFormatException If the event command is used but a "/at" is not present in the message.
     */

    private void addEvent(String event) throws IncorrectFormatException, MessageEmptyException, InvalidDateTimeException, InvalidDurationException {
        String[] result = event.split("/at");

        if (result.length == 0) {
            throw new MessageEmptyException();
        } else if (result.length == 1) {
            // throws an error if "/at" is not present in the message
            throw new IncorrectFormatException("event", "/at");
        }
        String description = result[0].trim();    // trims the additional spaces to the left and right of "at"
        String at = result[1].trim();             // trims the additional spaces to the left and right of "at"

        // throws error if it doesn't even contain sufficient number of characters for correct format
        if (at.replaceAll("\\s", "").length() < 19) { // YYYY/MM/DD HHMM - HHMM
            throw new InvalidDurationException();
        }

        String date = at.substring(0, 10).trim(); // at this point, date contains 10 chars YYYY/MM/DD
        String eventDuration = at.substring(11).trim();
        String[] eventTimes = eventDuration.split("-");

        // if no "-" present
        if (eventTimes.length != 2) {
            throw new InvalidDurationException();
        }

        String startTime = eventTimes[0].trim();
        String endTime = eventTimes[1].trim();

        LocalDate finalDate;
        LocalTime finalStartTime;
        LocalTime finalEndTime;

        try {
            // checks if the formats of the input date and time are correct
            finalDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            finalStartTime = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HHmm"));
            finalEndTime = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HHmm"));
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }

        Event e = new Event(description, finalDate, finalStartTime, finalEndTime);

        addToList(e);
    }

    /**
     * Deletes a Task from the list of Tasks.
     * @param taskIndex Index of the Task to be deleted.
     * @throws EmptyListException If the list of Tasks is empty and there is nothing to be deleted.
     * @throws InvalidIndexException If the index of the Task provided is out of range of the current list of Tasks.
     */

    private void deleteTask(String taskIndex) throws EmptyListException, InvalidIndexException {
        int intTaskIndex = Integer.parseInt(taskIndex) - 1; // -1 because user inputs start from 1 not 0
        int taskListSize = taskList.size();

        if (taskListSize == 0) {
            throw new EmptyListException();
        } else if (intTaskIndex < 0 || intTaskIndex >= taskListSize) {
            throw new InvalidIndexException(1, taskListSize, intTaskIndex + 1);
        }

        Task task = taskList.remove(intTaskIndex);
        System.out.println("Noted. I've removed this task:\n" + task);
        String taskGrammar = (taskList.size() == 1) ? " task" : " tasks";
        System.out.println("Now you have " + taskList.size() + taskGrammar + " in the list.");
    }

    /**
     * Logic for handling different commands and executing the appropriate methods for the inputted command.
     * @param input The entire user input.
     */

    private void handleCommands(String input) {
        String[] words = input.split(" "); // isolates the command word
        String command = words[0];

        try {
            System.out.println("__________________________________");
            switch (command) {
            case "list":
                displayList();
                break;
            case "done":
                if (words.length == 1) {
                    // throws an error if there is no message input after the command word
                    throw new MessageEmptyException();
                }
                String doneTaskIndex = words[words.length - 1];
                markDone(doneTaskIndex);
                break;
            case "deadline":
                if (words.length == 1) {
                    // throws an error if there is no message input after the command word
                    throw new MessageEmptyException();
                }
                try {
                    // excludes command "deadline " from the string
                    addDeadline(input.substring(9));
                } catch (InvalidDateTimeException | MessageEmptyException | IncorrectFormatException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "todo":
                if (words.length == 1) {
                    // throws an error if there is no message input after the command word
                    throw new MessageEmptyException();
                }
                // excludes command "todo" from the string
                addTodo(input.substring(5));
                break;
            case "event":
                if (words.length == 1) {
                    // throws an error if there is no message input after the command word
                    throw new MessageEmptyException();
                }
                // excludes command "event" from the string
                addEvent(input.substring(6));
                break;
            case "delete":
                if (words.length == 1) {
                    // throws an error if there is no message input after the command word
                    throw new MessageEmptyException();
                }
                String deleteTaskIndex = words[words.length - 1];
                deleteTask(deleteTaskIndex);
                break;
            case "": // empty user input
                throw new EmptyCommandException();
            default: // all other inputs that are not supported
                throw new InvalidCommandException();
            }
            System.out.println("__________________________________");
        } catch (DukeException e) {
            System.out.println(e.getMessage()); // prints only error message out for user
            System.out.println("__________________________________");
        }
    }

    private void save() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);

        for (Task task : taskList) {
            fw.write(task.toString() + "\n");
        }

        fw.flush();
        fw.close();
    }

    private void load() throws IOException {
        File file = new File(FILE_PATH);

        // creates file if not present, else it does nothing
        if (file.createNewFile()) {
            // exit method if a new file is created
            return;
        }

        Scanner sc = new Scanner(file);

        while(sc.hasNext()) {
            String nextCommand = sc.nextLine();
            Task task;

            switch (nextCommand.charAt(1)) {
            case 'D':
                try {
                    task = extractDeadline(nextCommand.substring(7));   // [D][X] something by time
                    break;
                } catch (DataFileChangedException e) {
                    System.out.println(e.getMessage());
                }
            case 'E':
                try {
                    task = extractEvent(nextCommand.substring(7));      // [D][X] something at time
                    break;
                } catch (DataFileChangedException e) {
                    System.out.println(e.getMessage());
                }
            default:                                                // todos
                task = new Todo(nextCommand.substring(7));          // disregards [T][X]
            }

            if (nextCommand.charAt(4) == 'X') {
                task.markAsDone();
            }

            taskList.add(task);
        }

        sc.close();
    }

    private Deadline extractDeadline(String text) throws DataFileChangedException {
        int lastOccurrenceOfBy = text.lastIndexOf(" (by: "); // in case other bys appear
        String description = text.substring(0, lastOccurrenceOfBy);

        // disregards "( by: " and trailing ")"
        String by = text.substring(lastOccurrenceOfBy + 6, text.length() - 1);

        LocalDateTime dateTime;

        try {
            dateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
        } catch (DateTimeParseException e) {
            throw new DataFileChangedException();
        }

        return new Deadline(description, dateTime);
    }

    private Event extractEvent(String text) throws DataFileChangedException {
        int lastOccurrenceOfAt = text.lastIndexOf(" (at: ");
        String description = text.substring(0, lastOccurrenceOfAt);

        // disregards "( at: " and trailing ")"
        String at = text.substring(lastOccurrenceOfAt + 6, text.length() - 1);

        // throws error if it doesn't even contain sufficient number of characters for correct format
        if (at.replaceAll("\\s", "").length() < 22 || at.replaceAll("\\s", "").length() > 25) { // MMM d yyyy, HH:mm - HH:mm
            throw new DataFileChangedException();
        }

        int indexOfComma = at.indexOf(',');
        String date = at.substring(0, indexOfComma).trim(); // at this point, date contains 10 chars YYYY/MM/DD
        String eventDuration = at.substring(indexOfComma + 1).trim();
        String[] eventTimes = eventDuration.split("-");

        // if no "-" present
        if (eventTimes.length != 2) {
            throw new DataFileChangedException();
        }

        String startTime = eventTimes[0].trim();
        String endTime = eventTimes[1].trim();

        LocalDate finalDate;
        LocalTime finalStartTime;
        LocalTime finalEndTime;

        try {
            // checks if the formats of the input date and time are correct
            finalDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMM d yyyy"));
            finalStartTime = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("h:mm a"));
            finalEndTime = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("h:mm a"));
        } catch (DateTimeParseException e) {
            throw new DataFileChangedException();
        }

        return new Event(description, finalDate, finalStartTime, finalEndTime);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
