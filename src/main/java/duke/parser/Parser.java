package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

public class Parser {
    private String userInput;

    /**
     * Constructor of Parser class. Initialize a Parser instance from a given userInput.
     */
    public Parser(String userInput) {
        this.userInput = userInput;
    }

    private static boolean isValidDate(String date) {
        try {
            String year = date.substring(0, 3);
            String month = date.substring(5, 6);
            String day = date.substring(8, 9);
            assert Integer.parseInt(month) >= 0:"Not Valid";
            assert Integer.parseInt(month) <= 12:"Not Valid";
            assert Integer.parseInt(day) <= 31:"Not Valid";
            return date.charAt(4) == '-' && date.charAt(7) == '-';

        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Return the operation type of user parser.
     */
    public String getOperationType() throws DukeException {
        String operation = userInput.split(" ")[0];

        if (TaskList.isValidOperation(operation)) {
            return operation;
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Returns the index info in a line of parser.
     */
    public int getIndex() throws DukeException {
        String[] allWords = userInput.split(" ");
        if (allWords.length >= 2) {
            try {
                int index = Integer.parseInt(allWords[1]);
                return index;
            } catch (NumberFormatException e) {
                throw new DukeException("The format of parser is wrong");
            }
        } else {
            throw new DukeException("The format of parser is wrong");
        }
    }

    /**
    * Returns the task info in a line of parser.
    */

    public String getTask() throws DukeException {
        String taskDescription;

        if (this.getOperationType().equals("deadline")
                || this.getOperationType().equals("event")) {
            assert userInput.indexOf(" ") < userInput.indexOf("/") : "The description cannot be empty.";
            taskDescription = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf("/") - 1);
        } else if (this.getOperationType().equals("todo")
                || this.getOperationType().equals("find")) {
            assert userInput.contains(" ") : "The description cannot be empty.";
            taskDescription = userInput.substring(userInput.indexOf(" ") + 1);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return taskDescription;
    }

    /**
     * Returns the time info in a line of parser in LocalDate type.
     */
    public LocalDate getTime() throws DukeException {
        String time;
        if (this.getOperationType().equals("deadline")) {
            if (userInput.contains("/by")) {
                time = userInput.substring(userInput.indexOf("/by") + 4);
            } else {
                throw new DukeException("I'm sorry, but the format of deadline is wrong :-(");
            }
        } else if (this.getOperationType().equals("event")) {
            if (userInput.contains("/at")) {
                time = userInput.substring(userInput.indexOf("/at") + 4);
            } else {
                throw new DukeException("I'm sorry, but the format of event is wrong :-(");
            }
        } else if (this.getOperationType().equals("snooze")) {
            String[] allWords = userInput.split(" ");
            if (allWords.length == 3) {
                time = allWords[2];
            } else {
                throw new DukeException("I'm sorry, but the format of snooze command is wrong :-(");
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
        parsedTime = LocalDate.parse(time, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return parsedTime;
    }

    /**
     * Parse user command and return a Command instance corresponding to userInput.
     *
     * @param taskList A list of tasks
     * @param textUi User interface
     */
    public Command parse(TaskList taskList, Ui textUi) throws DukeException {
        Command command;
        try {
            String operation = this.getOperationType();
            switch (operation) {
            case "bye": {
                command = new ByeCommand(textUi);
                break;
            }
            case "list": {
                command = new ListCommand(taskList, textUi);
                break;
            }
            case "done": {
                int index = this.getIndex();
                command = new DoneCommand(taskList, textUi, index);
                break;
            }
            case "delete": {
                int index = this.getIndex();
                command = new DeleteCommand(taskList, textUi, index);
                break;
            }
            case "todo": {
                String taskInfo = this.getTask();
                command = new TodoCommand(taskList, textUi, taskInfo);
                break;
            }
            case "event": {
                String taskInfo = this.getTask();
                LocalDate taskTime = this.getTime();
                command = new EventCommand(taskList, textUi, taskInfo, taskTime);
                break;
            }
            case "deadline": {
                String taskInfo = this.getTask();
                LocalDate taskTime = this.getTime();
                command = new DeadlineCommand(taskList, textUi, taskInfo, taskTime);
                break;
            }
            case "find": {
                String keyword = this.getTask();
                command = new FindCommand(taskList, textUi, keyword);
                break;
            }
            case "snooze": {
                int index = this.getIndex();
                LocalDate newTiming = this.getTime();
                command = new SnoozeCommand(taskList, textUi, index, newTiming);
                break;
            }
            default: {
                throw new DukeException("I'm sorry but, I don't know what that means :-(");
            }
            }
            return command;
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

}
