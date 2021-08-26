/**
 * The Bhutu chatbot app
 */

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Duke {

    /**
     * Global variables
     */
    private static final String SPACE = "    ";
    private static final String LINE = SPACE + "______________________________________________________________________";
    private static final String END_LINE = SPACE + "======================================================================\n";

    private static final String DATA_FOLDER = "./data";
    private static final String DATA_FILE = "duke.txt";
    private static final String DATA_FILE_FOLDER = DATA_FOLDER + "/" + DATA_FILE;

    private Items items;

    public Duke() {
        items = new Items();
    }


    /**
     * method to greet the user
     */
    private void greet() {
        System.out.println(LINE);
        System.out.println(SPACE + "Hello! I'm Bhutu, your personal chatbot!");
        System.out.println(SPACE + "What can I do for you?");
        System.out.println(END_LINE);
    }

    /**
     * interact with the user
     */
    private void interact() throws DukeException, IOException {
        String[] input;
        List<String> fileContent = readFile(items);

        if (fileContent == null) {
            System.out.println("File read error");
            return;
        }

        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while(flag) {
            String output = "";
            input = getInput(sc).split("\\s+");
            String command = input[0];
            String str;
            try {
                String[] task = compileInput(input);
                switch (command) {
                case "list":
                    output = items.printList();

                    break;
                case "done":
                    output = items.markDone(Integer.parseInt(input[1]));
                    int idx = Integer.parseInt(input[1]);
                    str = fileContent.get(idx - 1);
                    str = str.substring(0, 4) + "1" + str.substring(5);
                    fileContent.set(idx - 1, str);

                    break;
                case "bye":
                    flag = false;
                    break;
                case "todo":
                    output = items.addItem(new Todo(task[0]));
                    str = "T | 0 | " + task[0];
                    fileContent.add(str);
                    break;
                case "event":
                    output = items.addItem(new Event(task[0], task[1]));

                    str = "T | 0 | " + task[0] + " | "+ task[1];
                    fileContent.add(str);

                    break;
                case "deadline":
                    output = items.addItem(new Deadline(task[0], task[1]));

                    str = "E | 0 | " + task[0] + " | "+ task[1];
                    fileContent.add(str);
                    break;
                case "delete":
                    output = items.deleteItem(Integer.parseInt(input[1]));

                    fileContent.remove(Integer.parseInt(input[1]) - 1);
                    break;
                default:
                    output = "I don't recognise this command\n"
                            + "Try 'list', 'todo', 'event', 'deadline', 'done' or 'bye'";
                    break;
                }
            } catch (DukeException dukeException) {
                output = dukeException.getMessage();
            }

            Files.write(Paths.get(DATA_FILE_FOLDER), fileContent, StandardCharsets.UTF_8);
            if (flag) {
                printMessage(output);
            }
        }
        printMessage("Going so soon? Hope to see you again soon!");
    }

    /**
     * Get the user input
     * @param sc The scanner to get the input
     * @return The string representation of the user input
     */
    private static String getInput(Scanner sc) {
        return sc.nextLine();
    }

    /**
     * combine an array of strings into a space seperated sentence.
     * @param input the string array.
     * @return the sentence.
     */
    private StringBuilder combineInputArray(String[] input) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < input.length; i++) {
            if (i < input.length - 1) {
                result.append(input[i]).append(" ");
            } else {
                result.append(input[i]);
            }
        }
        return result;
    }

    /**
     * Convert the user input string into meaningful commands.
     * @param input the user input string.
     * @return the meaningful commands.
     */
    private String[] compileInput(String[] input) throws DukeException {
        StringBuilder result = combineInputArray(input);
        switch (input[0]) {
        case "deadline":
            String[] output = result.toString().split(" /by ");
            if (output.length < 2) {
                throw new DukeException("Please provide both description and time. "
                        + "Use '/by'. (eg. deadline fix hair /by 1pm tomorrow)");
            } else {
                return output;
            }
        case "event":
            String[] output1 = result.toString().split(" /at ");
            if (output1.length < 2) {
                throw new DukeException("Please provide both description and time. "
                        + "Use '/at'. (eg. event fix hair /at 1pm tomorrow)");
            } else {
                return output1;
            }
        case "todo":
            if (input.length < 2) {
                throw new DukeException("Please specify the task you want to do");
            } else {
                return new String[] {result.toString()};
            }
        case "done":
            if (input.length < 2) {
                throw new DukeException("Please specify which task you have done");
            } else if (input.length != 2) {
                throw new DukeException("'done' command requires exactly 1 argument. (eg. done 12)");
            }

            try {
                Integer.parseInt(input[1]);
            } catch (Exception e) {
                throw new DukeException("'done' command requires an integer as number. (eg. done 12)");
            }

            return new String[] {input[1]};
        case "list":
            if (input.length != 1) {
                throw new DukeException("'list' command doesn't require any arguments.");
            } else {
                return new String[] {input[0]};
            }
        case "bye":
            if (input.length != 1) {
                throw new DukeException("'bye' command doesn't require any arguments.");
            } else {
                return new String[] {input[0]};
            }
        case "delete":
            if (input.length < 2) {
                throw new DukeException("Please specify which task you want to delete");
            } else if (input.length != 2) {
                throw new DukeException("'delete' command requires exactly 1 argument. (eg. done 12)");
            }
            try {
                Integer.parseInt(input[1]);
            } catch (Exception e) {
                throw new DukeException("'delete' command requires an integer as number. (eg. done 12)");
            }

            return new String[] {input[1]};

        default:
            throw new DukeException("I don't recognise this command\n"
                + "Try 'list', 'todo', 'event', 'deadline', 'done' or 'bye'");
        }
    }

    /**
     * read file. If file does not exist, create file
     * @param items the list of tasks
     * @return List containing all tasks in file originally
     * @throws IOException if file cannot be created
     * @throws DukeException if data cannot be read from existing file
     */
    public List<String> readFile(Items items) throws IOException, DukeException {

        // Make directory and/or file if they don't exist
        File dataDir = new File(DATA_FOLDER);
        dataDir.mkdirs();
        File dataFile = new File(DATA_FILE_FOLDER);
        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Failed to create a new file");
        }

        List<String> fileContent = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(dataFile);
            while (fileReader.hasNextLine()) {
                String rawData = fileReader.nextLine();
                fileContent.add(rawData);
                String[] data = rawData.split(" \\| ");
                String taskType = data[0];
                boolean isDone = data[1].equals("1");
                Task task = null;
                switch (taskType) {
                case "T":
                    // Add a todo task.
                    task = new Todo(data[2]);

                    break;
                case "D":
                    // Add a deadline task.
                    task = new Deadline(data[2], data[3]);

                    break;
                case "E":
                    // Add an event task.
                    task = new Event(data[2], data[3]);
                }
                if (task != null) {
                    if (isDone) {
                        task.doneTask();
                    }
                    items.addItem(task);
                }
            }
        } catch (FileNotFoundException e) {
            printMessage("No Saved data found");
            return fileContent;
        }
        return fileContent;
    }

    /**
     * print all bot messages in a specific format.
     * @param message message from the bot.
     */
    private static void printMessage(String message) {
        message = SPACE + message.replace("\n", "\n" + SPACE);
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(END_LINE);
    }

    /**
     * The main function of Bhutu
     * @param args The command line arguments
     */
    public static void main(String[] args) throws IOException, DukeException {
        String logo = "\n" +
                "███████████████████████████████\n" +
                "█▄─▄─▀█─█─█▄─██─▄█─▄─▄─█▄─██─▄█\n" +
                "██─▄─▀█─▄─██─██─████─████─██─██\n" +
                "▀▄▄▄▄▀▀▄▀▄▀▀▄▄▄▄▀▀▀▄▄▄▀▀▀▄▄▄▄▀▀";

        System.out.println(logo);

        Duke duke = new Duke();
        duke.greet();
        duke.interact();
    }
}
