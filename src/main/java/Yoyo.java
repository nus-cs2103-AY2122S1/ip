import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.time.DateTimeException;
import java.time.LocalDateTime;

import static java.lang.Integer.parseInt;


public class Yoyo {
    private static TaskList tasks = new TaskList(new ArrayList<>());
    private static final int TYPE_STR_INDEX = 1;
    private static final int ISDONE_STR_INDEX = 4;
    private static final String DATAPATH = "data/yoyo.txt";

    enum TaskType {
        TODO,
        EVENT,
        DEADLINE
    }

    /**
     * Exception class for command with invalid format .
     */
    private static class YoyoInvalidFormatException extends IOException {
        YoyoInvalidFormatException(String message) {
            super(message);
        }
    }

    /**
     * Exception class for incomplete command .
     */
    private static class YoyoIncompleteCommandException extends IOException {
        YoyoIncompleteCommandException(String message) {
            super(message);
        }
    }

    /**
     * Exception class for invalid command.
     */
    private static class YoyoCommandNotFoundException extends IOException {
        YoyoCommandNotFoundException(String message) {
            super(message);
        }
    }

    /**
     * Exception class for invalid task index.
     */
    private static class YoyoTaskIndexException extends IOException {
        YoyoTaskIndexException(String message) {
            super(message);
        }
    }

    /**
     * Exception class for empty command.
     */
    private static class YoyoEmptyCommandException extends IOException {
        YoyoEmptyCommandException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.greetUser();
        File f = new File(DATAPATH);

        try {
            if (!f.exists()) {
                f.createNewFile();
            } else {
                readExistingTasks(f);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong while creating new file:\n"
                    + e.getMessage());
        }



        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                String input = scanner.nextLine();
                input = input.trim();
                if (input.length() == 0) {
                    throw new YoyoEmptyCommandException("You have not entered a command!");
                }
                String[] inputTokens = input.split(" ", 2);
                String command = inputTokens[0];

                if (command.equals("bye")) {
                    try {
                        FileWriter fw = new FileWriter(DATAPATH);
                        String textOutput = "";
                        for (int i = 0; i < tasks.size(); i++) {
                            textOutput += tasks.get(i).showStatusWrite();
                            textOutput += "\n";
                        }
                        fw.write(textOutput);
                        fw.close();

                    } catch (IOException e) {
                        System.out.println("Something went wrong while creating file writer:\n"
                                + e.getMessage());
                    }
                    ui.sayGoodbye();
                    break;
                } else if (command.equals("list")) {
                    int currListLength = tasks.size();
                    ui.printTaskList(tasks);
                } else {
                    if (command.equals("done")) {
                        checkCompleteCommand(inputTokens);
                        try {
                            int taskIndex = Integer.parseInt(inputTokens[1]) - 1;
                            tasks.get(taskIndex).toggleDone();
                            ui.printMarkTaskMessage(tasks, taskIndex);
                        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
                            throw new YoyoTaskIndexException("Please enter A valid task index!");
                        }
                    } else if (command.equals("delete")) {
                        checkCompleteCommand(inputTokens);
                        try {
                            int taskIndex = Integer.parseInt(inputTokens[1]) - 1;
                            Task toRemove = tasks.get(taskIndex);
                            tasks.remove(taskIndex);
                            ui.printRemoveTaskMessage(toRemove, tasks);
                        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
                            throw new YoyoTaskIndexException("Please enter A valid task index!");
                        }

                    } else {
                        if (command.equals("todo")) {
                            checkCompleteCommand(inputTokens);
                            Task newTask = new Todo(inputTokens[1]);
                            tasks.add(newTask);
                            ui.printAddMessage(newTask, tasks);
                        } else if (command.equals("event")) {
                            checkCompleteCommand(inputTokens);
                            String[] taskInfo = inputTokens[1].split(" /at ");
                            if (taskInfo.length < 2 ) {
                                throw new YoyoIncompleteCommandException("command has bad format or"
                                        + " not enough information.");
                            } else {
                                LocalDateTime datetime = parseTimeString(taskInfo[1]);
                                Task newTask = new Event(taskInfo[0], datetime);
                                tasks.add(newTask);
                                ui.printAddMessage(newTask, tasks);
                            }
                        } else if (command.equals("deadline")) {
                            checkCompleteCommand(inputTokens);
                            String[] taskInfo = inputTokens[1].split(" /by ");
                            if (taskInfo.length < 2) {
                                throw new YoyoIncompleteCommandException("command has bad format or"
                                        + " not enough information.");
                            } else {
                                LocalDateTime datetime = parseTimeString(taskInfo[1]);
                                Task newTask = new Deadline(taskInfo[0], datetime);
                                tasks.add(newTask);
                                ui.printAddMessage(newTask, tasks);
                            }
                        } else {
                            throw new YoyoCommandNotFoundException("Yoyo doesn't understand what you mean :-(");
                        }
                    }


                }


            } catch (YoyoCommandNotFoundException | YoyoIncompleteCommandException
                    | YoyoEmptyCommandException | YoyoInvalidFormatException
                    | YoyoTaskIndexException e) {
                ui.printErrorMessage(e);
            }
        }
    }


    private static void readExistingTasks(File f) {
        try {
            Scanner s = new Scanner(f);
            String currLine;
            boolean currCompletionStatus;
            String[] currStrArr;
            TaskType currType;

            while (s.hasNext()) {
                currLine = s.nextLine();
                currCompletionStatus = currLine.charAt(ISDONE_STR_INDEX) == 'X'
                        ? true
                        : false;
                char typeChar = currLine.charAt(TYPE_STR_INDEX);
                currType = typeChar == 'T'
                        ? TaskType.TODO
                        : typeChar == 'D'
                            ? TaskType.DEADLINE
                            : TaskType.EVENT;
                currStrArr = currLine.split(", ");
                switch (currType) {
                case TODO:
                    tasks.add(new Todo(currStrArr[1], currCompletionStatus));
                    break;
                case EVENT:
                    tasks.add(new Event(currStrArr[1], LocalDateTime.parse(currStrArr[2]), currCompletionStatus));
                    break;
                case DEADLINE:
                    tasks.add(new Deadline(currStrArr[1], LocalDateTime.parse(currStrArr[2]), currCompletionStatus));
                    break;
                default:
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not Found!");
        }
    }

    private static LocalDateTime parseTimeString(String ts)
            throws YoyoIncompleteCommandException, YoyoInvalidFormatException {
        char separator;
        if (ts.indexOf('/') != -1) {
            separator = '/';
        } else if (ts.indexOf('-') != -1) {
            separator = '-';
        } else {
            throw new YoyoInvalidFormatException("Invalid datetime format, "
                    + "use 'yyyy/MM/dd hhmm'");
        }

        String[] dateTimeArr = ts.split(" ");
        checkCompleteCommand(dateTimeArr);
        String[] dateArr = dateTimeArr[0].split(String.valueOf(separator));
        if (dateArr.length != 3 || dateTimeArr[1].length() < 3) {
            throw new YoyoInvalidFormatException("Invalid datetime format, "
                    + "use 'yyyy/MM/dd hhmm'");
        }
        int year = parseInt(dateArr[0]);
        int month = parseInt(dateArr[1]);
        int day = parseInt(dateArr[2]);
        int hour = parseInt(dateTimeArr[1].substring(0, 2));
        int min = parseInt(dateTimeArr[1].substring(2));
        LocalDateTime result;
        try {
            result = LocalDateTime.of(year, month, day, hour, min);
        } catch (DateTimeException e) {
            throw new YoyoInvalidFormatException("Invalid datetime format, "
                    + "use 'yyyy/MM/dd hhmm'");
        }
        return result;
    }



    /**
     * Checks user input for incomplete commands.
     *
     * @param inputTokens String array from user input.
     * @throws YoyoIncompleteCommandException Thrown if command is incomplete.
     */
    private static void checkCompleteCommand(String[] inputTokens) throws YoyoIncompleteCommandException {
        if (inputTokens.length < 2 || inputTokens[1].trim().length() == 0) {
            throw new YoyoIncompleteCommandException("You have not entered enough information for your command.");
        }
    }



}
