package duke.command;

import duke.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import duke.exception.DukeException;

public class Parser {
    private String userInput;

    /**
     * Constructor of Parser class. Initialize a Parser instance from a given userInput.
     */
    public Parser(String userInput) {
        this.userInput = userInput;
    }

    private static boolean isValidDate(String date) {
        try{
            String year = date.substring(0, 3);
            String month = date.substring(5, 6);
            String day = date.substring(8, 9);
            Integer.parseInt(year); 
            Integer.parseInt(month);
            Integer.parseInt(day);
            return date.charAt(4) == '-' && date.charAt(7) == '-';

        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Return the operation type of user command.
     */
    public String getOperationType() throws DukeException {
        String operation = userInput.split(" ")[0];

        if (Arrays.asList(TaskList.OperationType).contains(operation)) {
            return operation;
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Returns the index info in a line of command.
     */
    public int getIndex() throws DukeException {
        String[] allWords = userInput.split(" ");
        if (allWords.length == 2) {
            try {
                int index = Integer.parseInt(allWords[1]);
                return index;
            } catch (NumberFormatException e) {
                throw new DukeException("The format of command is wrong");
            }
        } else {
            throw new DukeException("The format of command is wrong");
        }
    }

    /**
    * Returns the task info in a line of command.
    */

    public String getTask() throws DukeException {
        String taskDescription;

        if (this.getOperationType().equals("deadline") || this.getOperationType().equals("event") ||
                this.getOperationType().equals("todo") || this.getOperationType().equals("find")) {
            if (userInput.contains("/")) {
                if (userInput.indexOf(" ") < userInput.indexOf("/")) {
                    taskDescription = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf("/") - 1);
                } else {
                    throw new DukeException("The description of a " + this.getOperationType() + " cannot be empty.");
                }
            } else {
                if (!userInput.contains(" ")) {
                    throw new DukeException("☹ OOPS!!! The description of a " + this.getOperationType() + " cannot be empty.");
                } else {
                    taskDescription = userInput.substring(userInput.indexOf(" ") + 1);
                }
            }
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return taskDescription;
    }

    /**
     * Returns the time info in a line of command in LocalDate type.
     */
    public LocalDate getTime() throws DukeException {
        String time;
        if (this.getOperationType().equals("deadline")) {
            if (userInput.contains("/by")) {
                time = userInput.substring(userInput.indexOf("/by") + 4);
            } else {
                throw new DukeException("I'm sorry, but the format of deadline is wrong :-(");
            }
        } else if (this.getOperationType().equals("event")){
            if (userInput.contains("/at")) {
                time = userInput.substring(userInput.indexOf("/at") + 4);
            } else {
                throw new DukeException("I'm sorry, but the format of event is wrong :-(");
            }
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        if (isValidDate(time)) {
            try {
                return LocalDate.parse(time);
            } catch (DateTimeParseException e) {
                throw new DukeException("Sorry Date format is wrong");
            }
        } else {
            throw new DukeException("Sorry Date format is wrong");
        }
    }
    /**
    * Returns the task info in local data.
     */
    public String getFileTask() {
        String task;
        char taskType = userInput.charAt(0);
        if (taskType == 'D' || taskType == 'E') {
            task = userInput.substring(8, userInput.indexOf("|", 8) - 1);
        } else {
            task = userInput.substring(8);
        }
        return task;
    }
    /**
     * Returns the time info in local data.
     */
    public LocalDate getFileTime() {
        String time;
        LocalDate parsedTime;
        time = userInput.substring(userInput.lastIndexOf("|") + 2);
        parsedTime = LocalDate.parse(time, DateTimeFormatter.ofPattern("MMM d yyyy"));
        return parsedTime;
    }

}
