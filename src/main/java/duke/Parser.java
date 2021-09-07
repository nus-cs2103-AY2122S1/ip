package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javafx.application.Platform;

/**
 * A class containing methods that parse relevant data from strings.
 *
 * @author Toh Wang Bin
 */
public class Parser {

    private static final int MIN_LENGTH_ARRAY = 2;
    private static final int MIN_LENGTH_ARRAY_FOR_SETPR = 3;
    private static final int ARRAY_INDEX_OF_TASK_INDEX = 1;
    private static final int ARRAY_INDEX_OF_PRIORITY = 2;

    /**
     * Parses a string into a LocalDate object.
     *
     * @param str The string to be parsed.
     * @return A LocalDate object created byb parsing the input string.
     * @throws IllegalArgumentException When an invalid string is parsed.
     */
    public static LocalDate parseDate(String str) throws IllegalArgumentException {
        LocalDate date;
        try {
            date = LocalDate.parse(str);
        } catch (DateTimeParseException exception) {
            throw new IllegalArgumentException("Date could not be parsed!");
        }
        assert date != null : "Date should have been initialised and assigned.";
        return date;
    }

    /**
     * Parses a string into a Task object.
     *
     * @param str The string to be parsed.
     * @return A task object created by parsing the input string.
     */
    public static Task parseData(String str) {
        String[] splitArray = str.split("\\|");
        switch (splitArray[0]) {
        //case if task stored is a Event
        case "E":
            Task event = new Events(splitArray[2], Parser.parseDate(splitArray[3]));
            if (splitArray[1].equals("1")) {
                event.setCompleted();
            }
            event.setPriorityLevel(splitArray[4]);
            return event;
        //case if task stored is a Deadline
        case "D":
            Task deadline = new Deadlines(splitArray[2], Parser.parseDate(splitArray[3]));
            if (splitArray[1].equals("1")) {
                deadline.setCompleted();
            }
            deadline.setPriorityLevel(splitArray[4]);
            return deadline;
        //last case will always be "T", or a Todo
        //no need for breaks in intermediate cases, as function has terminated at the return statements
        default:
            Task todo = new Todos(splitArray[2]);
            if (splitArray[1].equals("1")) {
                todo.setCompleted();
            }
            todo.setPriorityLevel(splitArray[3]);
            return todo;
        }
    }

    /**
     * Parses the given user input and returns a response.
     *
     * @param taskList The TaskList storing the Tasks.
     * @param storage The Storage instance containing the TaskList and files.
     * @param firstString The first String entered by the user
     * @param inputArray The array containing all strings input by the user.
     *
     * @return A string indicating the response to the user.
     */
    public static String parseInput(TaskList taskList, Storage storage, String firstString, String[] inputArray) {
        //case if nothing is entered
        if (firstString.equals("")) {
            return Ui.getEmptyInputError();
        }

        //case if user wants to exit
        if (firstString.equals("bye")) {
            Platform.exit();
            return Ui.getEndMessage();
        }

        if (firstString.equals("help")) {
            return Ui.getHelpMessage();
        }

        //below are cases for specified keywords:
        //case if user wants to view the list
        if (firstString.equals("list")) {
            //if list is empty
            if (taskList.getTotalTasks() == 0) {
                return Ui.getNoTaskError();
            }
            //repeatedly append tasks then return them
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < taskList.getTotalTasks(); i++) {
                int listNumber = i + 1;
                String taskString = listNumber + ". " + taskList.getTask(i).toString() + "\n";
                str.append(taskString);
            }
            return str.toString();
        }

        //case if user wants to delete a task or mark a task as done or set priority
        boolean isValidCommand = firstString.equals("done") || firstString.equals("delete")
                                   || firstString.equals("setpr");
        if (isValidCommand) {
            //case if no number is entered
            if (inputArray.length < MIN_LENGTH_ARRAY) {
                return Ui.getNumberError();
            }

            try {
                StringBuilder str = new StringBuilder();
                int index = Integer.parseInt(inputArray[ARRAY_INDEX_OF_TASK_INDEX]);
                int arrayIndex = index - 1;
                //case if entered index does not correspond to a task
                boolean isInvalidIndex = index > taskList.getTotalTasks() || index < 1;
                if (isInvalidIndex) {
                    return Ui.getTaskError();
                }
                //retrieve the task
                Task currentTask = taskList.getTask(arrayIndex);
                switch (firstString) {
                case "done":
                    currentTask.setCompleted();
                    str.append(Ui.getTaskCompleted(currentTask));
                    break;
                case "delete":
                    taskList.deleteTask(currentTask);
                    str.append(Ui.getTaskDeleted(currentTask));
                    break;
                default:
                    //last case is to set priority
                    if (inputArray.length < MIN_LENGTH_ARRAY_FOR_SETPR) {
                        return Ui.getBadInputError();
                    }
                    try {
                        currentTask.setPriorityLevel(inputArray[ARRAY_INDEX_OF_PRIORITY]);
                    } catch (IllegalArgumentException e) {
                        return Ui.getInvalidPriorityLevel();
                    }
                    str.append(Ui.getTaskPrioritised(currentTask));
                }
                str.append(Ui.getTaskNumberReminder(taskList.getTotalTasks()));
                try {
                    storage.saveData();
                } catch (IOException e) {
                    return Ui.getFileError();
                }
                return str.toString();

            } catch (NumberFormatException exception) {
                //case if string entered was not a number
                return Ui.getNumberError();
            }
        }

        //cases for the 3 task types
        if (firstString.equals("todo")) {
            return TaskCreator.createTask(Task.Tasks.TODO, inputArray, storage, taskList);
        }

        if (firstString.equals("deadline")) {
            return TaskCreator.createTask(Task.Tasks.DEADLINE, inputArray, storage, taskList);
        }

        if (firstString.equals("event")) {
            return TaskCreator.createTask(Task.Tasks.EVENT, inputArray, storage, taskList);
        }

        if (firstString.equals("find")) {
            if (inputArray.length < MIN_LENGTH_ARRAY) {
                //case if no number is entered
                return Ui.getNumberError();
            }
            StringBuilder str = new StringBuilder();
            for (int i = 1; i < inputArray.length; i++) {
                str.append(inputArray[i]).append(" ");
            }
            return taskList.find(str.toString());
        }

        //case if first string input is not a keyword
        return Ui.getBadInputError();
    }

}
