/**
 * The Bhutu chatbot app
 */


import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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
        printMessage("Hello! I'm Bhutu, your personal chatbot!\nWhat can I do for you?");
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
            input = getInput(sc).split("\\s+");
            String command = input[0];
            String str;
            String output = "";
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
        String date;
        switch (input[0]) {
        case "deadline":
            String[] output = result.toString().split(" /by ");
            if (output.length < 2) {
                throw new DukeException("Please provide both description and time. Use '/by'. (eg. deadline fix hair /by 1pm tomorrow)");
            }
            date = parseDate(output[1]);
            try {
                LocalDateTime.parse(date);
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid date.");
            }
            return new String[] {input[0], output[1], date};
        case "event":
            String[] output1 = result.toString().split(" /at ");
            if (output1.length < 2) {
                throw new DukeException("Please provide both description and time. Use '/at'. (eg. event fix hair /at 1pm tomorrow)");
            }
            date = parseDate(output1[1]);
            try {
                LocalDateTime.parse(date);
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid date.");
            }
            return new String[] {input[0], output1[1], date};
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
     */
    public List<String> readFile(Items items) {

        // Make directory and/or file if they don't exist
        File dataFolder = new File(DATA_FOLDER);
        boolean folderCreated = dataFolder.mkdirs();
        File dataFile = new File(DATA_FILE_FOLDER);
        try {
            boolean fileCreated = dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Failed to create a new file");
        }

        List<String> fileList = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(dataFile);
            while (fileReader.hasNextLine()) {
                String rawData = fileReader.nextLine();
                fileList.add(rawData);
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
            return fileList;
        }
        return fileList;
    }

    /**
     * Parses a raw date string as input into a valid date and time string.
     * @param input The raw date string
     * @return A string valid as a date
     * @throws DukeException Thrown if the input is an invalid date
     */
    private static String parseDate(String input) throws DukeException {
        String[] dateTime = input.split("\\s+");
        int len = dateTime.length;
        String formatPattern = "yyyy-MM-dd";
        if (len < 1 || len > 2) {
            throw new DukeException("Invalid format, please specify both Date and Time (eg. 2/12/2019 1900)");
        }
        String result = "";
        if (dateTime[0].equals("today")) {
            LocalDate date = LocalDate.now();
            result += date.format(DateTimeFormatter.ofPattern(formatPattern));
        } else if (dateTime[0].equals("tomorrow")) {
            LocalDate date = LocalDate.now().plusDays(1);
            result += date.format(DateTimeFormatter.ofPattern(formatPattern));
        } else {
            // Date
            String[] date1 = dateTime[0].split("-");
            String[] date2 = dateTime[0].split("/");
            if (date1.length == 3 || date2.length == 3) {
                result = date1.length == 3 ? stringToDate(date1) : stringToDate(date2);
            } else {
                throw new DukeException("Invalid date. Accepted formats: DD/MM/YYYY.");
            }
        }
        // Time
        if (dateTime.length == 2) {
            result += "T" + stringToTime(dateTime[1]);
        } else {
            result += "T23:59";
        }
        return result;
    }

    /**
     * Converts a string to time
     * @param time The time in the form of a string
     * @return The string representation of the time
     * @throws DukeException Thrown if the time is invalid
     */
    private static String stringToTime(String time) throws DukeException {
        String[] splitTime = time.split(":");
        if (splitTime.length > 2 || splitTime.length < 1) {
            throw new DukeException("Invalid time.");
        }
        for (String s : splitTime) {
            try {
                // Check if all the string are numbers:
                Integer.parseInt(s);
            } catch (Exception e) {
                System.out.println("Time is not a number");
                throw new DukeException("Time is invalid.");
            }
        }
        if (splitTime.length == 2) {
            // in the form of [hh, mm]
            if (
                    (splitTime[0].length() == 2 || splitTime[0].length() == 1) &&
                            (splitTime[1].length() == 2)
            ) {
                String hh = String.format("%02d", Integer.parseInt(splitTime[0]));
                String mm = String.format("%02d", Integer.parseInt(splitTime[1]));
                return hh + ":" + mm;
            }
        } else {
            // in the form of [hhmm]
            if (
                    (splitTime[0].length() == 3 || splitTime[0].length() == 4)
            ) {
                String hh = splitTime[0].length() == 3 ? splitTime[0].substring(0, 1) : splitTime[0].substring(0, 2);
                String mm = splitTime[0].length() == 3 ? splitTime[0].substring(1, 3) : splitTime[0].substring(2, 4);
                hh = String.format("%02d", Integer.parseInt(hh));
                mm = String.format("%02d", Integer.parseInt(mm));
                return hh + ":" + mm;
            }
        }
        throw new DukeException("Date is invalid.");
    }

    /**
     * Converts a date array to a date string
     * @param date A String array e.g. [yyyy, mm, dd]
     * @return Null if invalid, else a string representation of the date -> yyyy-mm-dd
     * @throws DukeException An invalid date will produce this
     */
    private static String stringToDate(String[] date) throws DukeException {
        // can be [yyyy, mm, dd] or [dd, mm, yyyy]
        try {
            // Check if all the string are numbers:
            Integer.parseInt(date[0]);
            Integer.parseInt(date[1]);
            Integer.parseInt(date[2]);
        } catch (Exception e) {
            throw new DukeException("Date is not a number.");
        }

        if (
                date[0].length() == 4 &&
                        (date[1].length() == 1 || date[1].length() == 2) &&
                        (date[2].length() == 1 || date[2].length() == 2)
        ) {
            // In the form of [yyyy, mm, dd]
            String year = date[0];
            String month = String.format("%02d", Integer.parseInt(date[1]));
            String day = String.format("%02d", Integer.parseInt(date[2]));
            return year + "-" + month + "-" + day;
        } else if (
                (date[0].length() == 1 || date[0].length() == 2) &&
                        (date[1].length() == 1 || date[1].length() == 2) &&
                        (date[2].length() == 4)
        ) {
            // In the form of [dd, mm, yyyy]
            String year = date[2];
            String month = String.format("%02d", Integer.parseInt(date[1]));
            String day = String.format("%02d", Integer.parseInt(date[0]));
            return year + "-" + month + "-" + day;
        }
        throw new DukeException("Invalid date. Please use the format DD-MM-YYYY or MM-DD-YYYY or YYYY-MM-DD");
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
