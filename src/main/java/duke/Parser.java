package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * A class containing methods that parse relevant data from strings.
 *
 * @author Toh Wang Bin
 */
public class Parser {

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
            if (splitArray[1].equals("1")) { event.setCompleted(); }
            return event;
            //no need for break, as function has terminated at the return statement
        //case if task stored is a Deadline
        case "D":
            Task deadline = new Deadlines(splitArray[2], Parser.parseDate(splitArray[3]));
            if (splitArray[1].equals("1")) { deadline.setCompleted(); }
            return deadline;
        //no need for break, as function has terminated at the return statement
        //last case will always be "T", or a Todo
        default:
            Task todo = new Todos(splitArray[2]);
            if (splitArray[1].equals("1")) { todo.setCompleted(); }
            return todo;
        }
    }

    /**
     * Parses the given user input. Returns true only when user input is to exit the program, returns false otherwise.
     *
     * @param taskList The TaskList storing the Tasks.
     * @param storage The Storage instance containing the TaskList and files.
     * @param firstString The first String entered by the user
     * @param inputArray The array containing all strings input by the user.
     * @return A boolean indicating if the user wants to quit the program.
     */
    public static boolean parseInput(TaskList taskList, Storage storage,String firstString, String[] inputArray) {
        //case if nothing is entered
        if (firstString.equals("")) {
            Message.printEmptyInputError();
            return false;
        }

        //case if user wants to exit the program
        if (firstString.equals("bye")) {
            return true;
        }

        //below are cases for specified keywords:
        //case if user wants to view the list
        if (firstString.equals("list")) {
            //if list is empty
            if (taskList.getTaskNumber() == 0) {
                Message.printNoTaskError();
                return false;
            }
            //repeatedly print tasks in the list
            for (int i = 0; i < taskList.getTaskNumber(); i++) {
                int listNumber = i + 1;
                System.out.println(listNumber + ". " + taskList.getTask(i).toString());
            }
            return false;
        }

        //case if user wants to delete a task or mark a task as done
        if (firstString.equals("done") || firstString.equals("delete")) {
            //case if no number is entered
            if (inputArray.length < 2) {
                Message.printNumberError();
                return false;
            }

            try {

                int index = Integer.parseInt(inputArray[1]);
                int arrayIndex = index - 1;
                //case if entered index does not correspond to a task
                if (index > taskList.getTaskNumber() || index < 1) {
                    Message.printTaskError();
                }
                //retrieve the task
                Task currentTask = taskList.getTask(arrayIndex);
                if (firstString.equals("done")) {
                    //case to complete a task
                    currentTask.setCompleted();
                    Message.printTaskCompleted();
                    System.out.println(currentTask.toString());
                } else {
                    //remaining case is to delete the task.
                    taskList.deleteTask(currentTask);
                    Message.printTaskCompleted();
                    System.out.println(currentTask.toString());
                }
                Message.printTaskNumberReminder(taskList.getTaskNumber());
                storage.saveData();

            } catch (NumberFormatException exception) {
                //case if string entered was not a number
                Message.printNumberError();
            }
            return false;

        }

        //cases for the 3 task types
        if (firstString.equals("todo")) {
            TaskCreator.createTask(Task.Tasks.TODO, inputArray, storage, taskList);
            return false;
        }

        if (firstString.equals("deadline")) {
            TaskCreator.createTask(Task.Tasks.DEADLINE, inputArray, storage, taskList);
            return false;
        }

        if (firstString.equals("event")) {
            TaskCreator.createTask(Task.Tasks.EVENT, inputArray, storage, taskList);
            return false;
        }

        if (firstString.equals("find")) {
            if (inputArray.length < 2) {
                //case if no number is entered
                Message.printNumberError();
                return false;
            }
            StringBuilder str = new StringBuilder();
            for (int i = 1; i < inputArray.length; i++) {
                str.append(inputArray[i]).append(" ");
            }
            taskList.find(str.toString());
            return false;
        }

        //case if first string input is not a keyword
        Message.printBadInputError();
        return false;
    }
}
