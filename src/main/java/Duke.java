/**
 * The Duke chatbot app
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Duke {
    /**
     * Global Variables
     */
    private static final String DATA_DIRECTORY = "./data";
    private static final String DATA_FILE = "duke.txt";
    private static final String DATA_FILE_DIRECTORY = DATA_DIRECTORY + "/" + DATA_FILE;
    private Ui ui;
    private Constants constants;

    public Duke() {
        ui = new Ui();
        constants = new Constants();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * The main function of Borat
     */
    public void run() {
        // The list of the Borat app
        Items list = new Items();

        // Read from saved file.
        List<String> fileContent = readSaved(list);
        if (fileContent == null) {
            System.out.println("File read error");
            return;
        }

        // Greetings
        ui.showGreetings();

        String rawInput = ui.getInput();
        String output = "";
        label:
        while (true) {

            try {
                String[] input = parseInput(rawInput);
                Constants.Commands command = Constants.Commands.valueOf(input[0]);
                String task = "";

                switch (command) {
                    case help:
                        // Show help
                        output = ui.getCommandMenu();

                        break;
                    case bye:
                        // Quit program
                        break label;
                    case list:
                        // Displays the tasks in the list
                        output = list.toString();

                        break;
                    case done:
                        int idx = Integer.parseInt(input[1]);
                        // Marks a task as done.
                        output = list.markDone(idx);
                        // Edit the file content.
                        task = fileContent.get(idx - 1);
                        task = task.substring(0, 4) + "1" + task.substring(5);
                        fileContent.set(idx - 1, task);

                        break;
                    case todo:
                        // Add a todo task in the list.
                        output = list.addItem(new Todo(input[1]));
                        // Add to file content.
                        task = "T | 0 | " + input[1];
                        fileContent.add(task);

                        break;
                    case deadline:
                        // Add a deadline task in the list.
                        output = list.addItem(new Deadline(input[1], input[2]));
                        // Add to file content.
                        task = "D | 0 | " + input[1] + " | " + input[2];
                        fileContent.add(task);

                        break;
                    case event:
                        // Add an event task in the list.
                        output = list.addItem(new Event(input[1], input[2]));
                        // Add to file content.
                        task = "E | 0 | " + input[1] + " | " + input[2];
                        fileContent.add(task);

                        break;
                    case delete:
                        int id = Integer.parseInt(input[1]);
                        // Delete an event from the list.
                        output = list.removeItem(id);
                        // Remove from file content.
                        fileContent.remove(id-1);

                        break;
                    case dates:
                        output = ui.getAllAcceptedDates();
                }
                Files.write(Paths.get(DATA_FILE_DIRECTORY), fileContent, StandardCharsets.UTF_8);
                ui.showMessage(output);
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            } catch (Exception e) {
                ui.showMessage(e.getMessage());
                return;
            }

            rawInput = ui.getInput();
        }

        /**
         * Good bye message from Borat
         */
        ui.showMessage("Bye. Have a good time!");
    }


    /**
     * Read the data from a file and save it to the given list.
     * @param list The list of tasks
     * @return Each line of the file in a list.
     */
    private List<String> readSaved(Items list) {
        // Make directory and/or file if they don't exist
        File dataDir = new File(DATA_DIRECTORY);
        dataDir.mkdirs();
        File dataFile = new File(DATA_DIRECTORY + "/" + DATA_FILE);
        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Failed to create a new file");
            return null;
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
                        task.markDone();
                    }
                    list.addItem(task);
                }
            }
        } catch (FileNotFoundException e) {
            ui.showMessage("No Saved data found");
            return null;
        } catch (DukeException e) {
            ui.showMessage("Loading Saved Data Fault: " + e.getMessage());
        }
        return fileContent;
    }


    /**
     * Parse the user input string into a meaningful String array.
     * @param rawInput The user input
     * @return Parsed input ready to be processed
     * @throws DukeException An invalid input will produce this exception
     */
    private String[] parseInput(String rawInput) throws DukeException {
        String[] input = rawInput.split("\\s+");
        if (input.length < 1) {
            throw new DukeException(DukeException.Errors.INVALID_COMMAND.toString());
        }
        Constants.Commands command;
        try {
            command = Constants.Commands.valueOf(input[0]);
        } catch (Exception e) {
            throw new DukeException(DukeException.Errors.INVALID_COMMAND.toString());
        }
        switch (command) {
            case list:
                if (input.length != 1) {
                    throw new DukeException(DukeException.Errors.INVALID_ARGUMENT.toString() + " `list` command has no arguments");
                }
                return new String[] {input[0]};

            case done:
                if (input.length != 2) {
                    throw new DukeException(DukeException.Errors.INVALID_ARGUMENT.toString() + " (example: 'done 5')");
                }
                try {
                    // Check if the argument is a number
                    Integer.parseInt(input[1]);
                } catch (Exception e) {
                    throw new DukeException(DukeException.Errors.WRONG_ARGUMENT_TYPE.toString() + " (example: 'done 5')");
                }
                return new String[] {input[0], input[1]};

            case todo:
                if (input.length < 2) {
                    throw new DukeException(DukeException.Errors.MISSING_DESCRIPTION.toString() + " (example: 'todo watch Borat')");
                }
                String description = combineStringArray(input, 1, input.length);
                return new String[] {input[0], description};

            case deadline:
                if (input.length < 2) {
                    throw new DukeException(DukeException.Errors.MISSING_DESCRIPTION.toString() + " (example: 'deadline watch Borat /by 2021-08-21 18:00')");
                }
                String arguments = combineStringArray(input, 1, input.length);
                String[] divided = arguments.split(" /by ");
                if (divided.length < 2) {
                    throw new DukeException(DukeException.Errors.MISSING_DATE.toString() + " (example: 'deadline watch Borat /by 2021-08-21 18:00')");
                } else if (divided.length > 2) {
                    throw new DukeException(DukeException.Errors.INVALID_DATE.toString() + " (example: 'deadline watch Borat /by 2021-08-21 18:00')");
                }
                String date = parseDate(divided[1]);
                try {
                    LocalDateTime.parse(date);
                } catch (DateTimeParseException e) {
                    throw new DukeException(DukeException.Errors.INVALID_DATE.toString());
                }
                return new String[] {input[0], divided[0], date};

            case event:
                if (input.length < 2) {
                    throw new DukeException(DukeException.Errors.MISSING_DESCRIPTION.toString() + " (example: 'event Borat concert /at 2021-08-21 18:00')");
                }
                String args = combineStringArray(input, 1, input.length);
                String[] div = args.split(" /at ");
                if (div.length < 2) {
                    throw new DukeException(DukeException.Errors.MISSING_DATE.toString() + " (example: 'event watch Borat /at 2021-08-21 18:00')");
                } else if (div.length > 2) {
                    throw new DukeException(DukeException.Errors.INVALID_DATE.toString() + " (example: 'event watch Borat /at 2021-08-21 18:00')");
                }
                String dateTest = parseDate(div[1]);
                try {
                    LocalDateTime.parse(dateTest);
                } catch (DateTimeParseException e) {
                    throw new DukeException(DukeException.Errors.INVALID_DATE.toString());
                }
                return new String[] {input[0], div[0], parseDate(div[1])};

            case bye:
                if (input.length != 1) {
                    throw new DukeException(DukeException.Errors.INVALID_ARGUMENT.toString() + " `bye` command has no arguments");
                }
                return new String[] {input[0]};

            case delete:
                if (input.length != 2) {
                    throw new DukeException(DukeException.Errors.INVALID_ARGUMENT.toString() + " (example: 'delete 5')");
                }
                try {
                    // Check if the argument is a number
                    Integer.parseInt(input[1]);
                } catch (Exception e) {
                    throw new DukeException(DukeException.Errors.WRONG_ARGUMENT_TYPE.toString() + " (example: 'delete 5')");
                }
                return new String[] {input[0], input[1]};

            case help:
                if (input.length != 1) {
                    throw new DukeException(DukeException.Errors.INVALID_ARGUMENT.toString() + " `help` command has no arguments");
                }
                return new String[] {input[0]};

            case dates:
                if (input.length != 1) {
                    throw new DukeException(DukeException.Errors.INVALID_ARGUMENT.toString() + " `dates` command has no arguments");
                }
                return new String[] {input[0]};

            default:
                throw new DukeException(DukeException.Errors.INVALID_ARGUMENT.toString());
        }
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
            throw new DukeException(DukeException.Errors.INVALID_DATE.toString());
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
                throw new DukeException(DukeException.Errors.INVALID_DATE.toString());
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
            throw new DukeException(DukeException.Errors.INVALID_TIME.toString());
        }
        for (String s : splitTime) {
            try {
                // Check if all the string are numbers:
                Integer.parseInt(s);
            } catch (Exception e) {
                System.out.println("Time is not a number");
                throw new DukeException(DukeException.Errors.INVALID_TIME.toString());
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
        throw new DukeException(DukeException.Errors.INVALID_TIME.toString());
    }

    /**
     * Converts a date array to a date string
     * @param date A String array e.g. [yyyy, mm, dd]
     * @return Null if invalid, else a string representation of the date -> yyyy-mm-dd
     * @throws DukeException An invalid date will produce this
     */
    private static String stringToDate(String[] date) throws DukeException{
        // can be [yyyy, mm, dd] or [dd, mm, yyyy]
        try {
            // Check if all the string are numbers:
            Integer.parseInt(date[0]);
            Integer.parseInt(date[1]);
            Integer.parseInt(date[2]);
        } catch (Exception e) {
            throw new DukeException(DukeException.Errors.INVALID_DATE.toString() + " Date is not a number.");
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
        throw new DukeException(DukeException.Errors.INVALID_DATE.toString());
    }


    /**
     * Combine an array of strings into a space separated sentence.
     * @param arr The string array
     * @param start The starting index to be combined (inclusive)
     * @param exclusiveEnd The ending index (exclusive)
     * @return The sentence.
     */
    private static String combineStringArray(String[] arr, int start, int exclusiveEnd) {
        StringBuilder tmp = new StringBuilder();
        if (exclusiveEnd > arr.length) {
            exclusiveEnd = arr.length;
        }
        for (int i = start; i < exclusiveEnd; ++i) {
            if (i + 1 >= exclusiveEnd) {
                tmp.append(arr[i]);
            } else {
                tmp.append(arr[i]).append(" ");
            }
        }
        return tmp.toString();
    }
}
