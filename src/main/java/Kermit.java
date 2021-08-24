import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.StringBuilder;

public class Kermit {
    /**
     * Adds a top and bottom horizontal line to text
     *
     * @param text Text to be formatted.
     * @return Formatted version of text.
     */
    private static String formatText(String text) {
        String horizontalDivider = "____________________________________________________________________________";
        return horizontalDivider + "\n" + text + "\n" + horizontalDivider;
    }

    /**
     * Pretty format text when task is added
     *
     * @param task Task that is added to list
     * @param list List that task was added to
     * @return String stating task was successfully added
     */
    private static void printAddTask(Task task, ToDo list) {
        formatAndPrintText("Got it. I've added this task:\n"
                + task +"\nNow you have " + list.size() + " tasks in the list.");
    }

    private static void printDeleteTask(Task task, ToDo list) {
        formatAndPrintText("Noted. I've removed this task:\n"
                + task +"\nNow you have " + list.size() + " tasks in the list.");
    }

    private static void printFilteredTasks(ArrayList<Task> filteredTasks) {
        String outputString = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < filteredTasks.size(); i++) {
            Task task = filteredTasks.get(i);
            outputString += (i + 1) + ". " + task.toString();
            if (i != filteredTasks.size() - 1) {
                outputString += "\n";
            }

        }
        formatAndPrintText(outputString);
    }

    private static LocalDate formatTaskDateFormat(String s) {
        String[] components = s.split("-");
        String day = components[0];
        String month = components[1];
        String year = components[2];
        LocalDate parsedDate = LocalDate.parse(String.join("-", year, month, day));
        return parsedDate;
    }

    private static LocalDate formatUserDateFormat(String s) {
        String[] components = s.split("-");
        String day = components[0];
        String month = components[1];
        String year = components[2];
        LocalDate parsedDate = LocalDate.parse(String.join("-", year, month, day));
        return parsedDate;
    }

    private static void formatAndPrintText(String text) {
        System.out.println(formatText(text));
    }

    private static String formatWriteString(Task task) {
        String delimiter = " | ";

        String taskComplete = task.isComplete() ? "1" : "0";
        String formattedString = String.join(delimiter, task.getShortForm(), taskComplete, task.getDescription());

        if (task instanceof DateDependentTask) {
            DateDependentTask dateTask = (DateDependentTask) task;
            formattedString = String.join(delimiter, formattedString, dateTask.getDate());
        }
        return formattedString;
    }

        private static ToDo readToDoData(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        ToDo todo = new ToDo();
        String line;
        Task task;
        // Read file line by line
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            String[] commands = line.split(" \\| ");
            String description = commands[2];
            boolean isCompleted = commands[1] == "1";
            switch (commands[0]) {
                case "T":
                    task = new ToDos(description, isCompleted);
                    todo.add(task);
                    break;
                case "D":
                    task = new Deadline(description, formatTaskDateFormat(commands[3]), isCompleted);
                    todo.add(task);
                    break;
                case "E":
                    task = new Event(description, formatTaskDateFormat(commands[3]), isCompleted);
                    todo.add(task);
                    break;
            }
        }
        return todo;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        String command = "";
        String flag;
        String word;

        String DIRNAME = "./data";
        String FILENAME = "kermit.txt";
        String PATHSTRING = String.join("/", DIRNAME, FILENAME);
        File FILE = new File(PATHSTRING);


        // Check if Kermit data exists, else create it
        try {
            File dir = new File(DIRNAME);
            dir.mkdir();
            FILE.createNewFile();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }

        StringBuilder descriptionBuilder = new StringBuilder();
        StringBuilder flagBuilder = new StringBuilder();

        // ArrayList of all valid commands and tasks
        String[] strCommands = {"bye", "list", "done", "deadline", "todo", "event", "delete", "find"};
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(strCommands));
        String[] strTasks = {"deadline", "todo", "event"};
        ArrayList<String> validTasks = new ArrayList<>(Arrays.asList(strTasks));

        ToDo list = readToDoData(FILE);

        final String introductionText = "Hello I am Kermit ( *・∀・)ノ゛, eaten any flies today?\nWhat can I do for you?";
        final String listText = "Here are the tasks in your list:";
        final String invalidCommandText = "I'm sorry, but I don't know what that means :-(";
        final String invalidTimeText = "BAH That's not a time is it?? Try writing like this D/MM/YYYY 1200";
        final String completeTaskText = "Ribbit Ribbit! Good job, task has been marked as complete:";
        final String goodbyeText = "Bye. Hope to see you again soon!";

        formatAndPrintText(introductionText);

        // Init writer to save Kermit data

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATHSTRING, true))) {
            while (true) {
                try {
                    // Task description and flag should be separated by some /command
                    String[] userInput = sc.nextLine().split("/");
                    String commandString = userInput[0];
                    String flagString = userInput.length > 1 ? userInput[1] : "";

                    String[] commandArr = commandString.split(" ");
                    String[] flagArr = flagString.split(" ");

                    // first item is command
                    command = commandArr[0];
                    flag = flagArr[0];

                    // Check if command is valid
                    if (!commands.contains(command)) {
                        throw new KermitException(invalidCommandText);
                    }
                    String description = "";
                    String flagArguments = "";

                    // Clear contents of string builders
                    descriptionBuilder.setLength(0);
                    flagBuilder.setLength(0);

                    // Get description of task and error check
                    for (int i = 1; i < commandArr.length; i++) {
                        word = commandArr[i];
                        if (i != 1) {
                            descriptionBuilder.append(" ");
                        }
                        descriptionBuilder.append(word);
                    }
                    description = descriptionBuilder.toString();
                    if (description.equals("") && validTasks.contains(command)) {
                        throw new KermitException("The description of a " + command + " cannot be empty");
                    }

                    // Get the flags provided for task and error check
                    for (int i = 1; i < flagArr.length; i++) {
                        word = flagArr[i];
                        if (i != 1) {
                            flagBuilder.append(" ");
                        }
                        flagBuilder.append(word);
                    }
                    flagArguments = flagBuilder.toString();
                    // flag arguments for these tasks should not be empty
                    if (flagArguments.equals("")) {
                        switch (command) {
                            case "event":
                                throw new KermitException("Events should be formatted as:\nevent <description> /at <time of event>");
                            case "deadline":
                                throw new KermitException("Deadlines should be formatted as:\ndeadline <description> /by <deadline>");

                        }
                    }

                    int index;
                    // Quit program
                    switch (command) {
                        case "bye":
                            formatAndPrintText(goodbyeText);
                            return;
                        // List out all objects that user added to list
                        case "list":
                            formatAndPrintText(listText + "\n" + list);
                            break;
                        // Add objects to list
                        case "done":
                            index = Integer.parseInt(description) - 1;
                            // Get task name
                            String taskText = list.completeTask(index);
                            formatAndPrintText(completeTaskText + "\n" + taskText);
                            break;
                        // Add new todo task
                        case "todo":
                            Task newToDo = new ToDos(description);
                            list.add(newToDo);
                            printAddTask(newToDo, list);
                            bufferedWriter.write(formatWriteString(newToDo));
                            bufferedWriter.newLine();
                            break;
                        // Add new deadline task
                        case "deadline":
                            Task newDeadline = new Deadline(description, formatUserDateFormat(flagArguments));
                            list.add(newDeadline);
                            printAddTask(newDeadline, list);
                            bufferedWriter.write(formatWriteString(newDeadline));
                            bufferedWriter.newLine();
                            break;

                        // Add new event task
                        case "event":
                            Task newEvent = new Event(description, formatUserDateFormat(flagArguments));
                            list.add(newEvent);
                            printAddTask(newEvent, list);
                            bufferedWriter.write(formatWriteString(newEvent));
                            bufferedWriter.newLine();
                            break;
                        // Delete task
                        case "delete":
                            index = Integer.parseInt(description) - 1;
                            Task deletedTask = list.deleteTask(index);
                            printDeleteTask(deletedTask, list);
                            break;
                        case "find":
                            ArrayList<Task> filteredTasks = list.filter(description);
                            printFilteredTasks(filteredTasks);
                            break;
                    }
                } catch (KermitException e) {
                    formatAndPrintText(e.getMessage());
                } catch (DateTimeParseException e) {
                    formatAndPrintText(invalidTimeText);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}