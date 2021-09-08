package retriever.task;

import java.util.ArrayList;

import retriever.Storage;
import retriever.Ui;
import retriever.exception.IllegalCommandException;
import retriever.exception.IllegalDateFormatException;
import retriever.exception.IllegalDeadlineFormatException;
import retriever.exception.IllegalEventFormatException;
import retriever.exception.IllegalTaskNumberException;
import retriever.exception.IllegalTodoFormatException;
import retriever.exception.RetrieverException;
import retriever.exception.TaskNotFoundException;

/**
 * This class handles the task list and various
 * operations on it.
 */
public class TaskList {
    private ArrayList<Task> userTaskList;
    private Storage taskStorage;
    private Ui ui;

    /**
     * Initializes the task list, calls a method to read
     * and parse the file to populate the user task list.
     *
     * @param taskStorage The storage object is used to read and write the tasks in a text file.
     */
    public TaskList(Storage taskStorage) {
        this.taskStorage = taskStorage;
        this.userTaskList = taskStorage.readTasks();
        this.ui = new Ui();
    }

    /**
     * Returns the number of tasks present in the task list.
     *
     * @return The length of the user task list.
     */
    public int taskListLength() {
        return userTaskList.size();
    }

    /**
     * Returns a boolean suggesting whether the format for adding a
     * deadline task is followed or not.
     *
     * @param userInput The command entered by the user, to be checked.
     * @return A boolean, true, if the format for adding the deadline task is followed.
     */
    public boolean isDeadlineFormatCorrect(String userInput) {
        boolean hasByKeyword = userInput.toLowerCase().contains("/by");
        if (!hasByKeyword) {
            return false;
        }

        boolean hasLengthGreaterThanOrEqualToFour = userInput.split(" ").length >= 4;
        boolean hasDescriptionAndDateSpecified = userInput.substring(9).split(" /by ").length >= 2;

        return hasByKeyword && hasLengthGreaterThanOrEqualToFour && hasDescriptionAndDateSpecified;
    }

    /**
     * Adds a deadline type task to the task list and storage.
     *
     * @param userInput The task details input by the user.
     * @throws IllegalDeadlineFormatException If the format for adding a deadline task is not followed.
     * @throws IllegalDateFormatException If the date format is not followed.
     */
    public void addDeadlineTask(String userInput) throws IllegalDeadlineFormatException, IllegalDateFormatException {
        int prefixLengthPlusSpace = 9;

        // Making sure that a properly formatted deadline task is entered.
        if (!isDeadlineFormatCorrect(userInput)) {
            throw new IllegalDeadlineFormatException("Please Follow The Format For Adding A Deadline Task:\n"
                    + "\tdeadline task_description /by DD/MM/YYYY");
        }

        // Parsing the user input to obtain the information about the task.
        String[] userInputArray = userInput.substring(prefixLengthPlusSpace).split(" /by ");

        // Making sure that the time is properly formatted.
        TaskDateAndTime deadlineDate = new TaskDateAndTime(userInputArray[1]);
        if (!deadlineDate.isValidDate()) {
            throw new IllegalDateFormatException("Please Follow The Date Format DD/MM/YYYY");
        }

        Task deadlineTask = new Deadline(userInputArray[0], deadlineDate);
        userTaskList.add(deadlineTask);
        taskStorage.writeTask(Task.TaskType.DEADLINE, userInputArray[0], userInputArray[1]);
        ui.printTaskAdded(deadlineTask, taskListLength());
    }

    /**
     * Returns a boolean suggesting whether the format for adding an
     * event task is followed or not.
     *
     * @param userInput The command entered by the user, to be checked.
     * @return A boolean, true, if the format for adding the event task is followed.
     */
    public boolean isEventFormatCorrect(String userInput) {
        boolean hasAtKeyword = userInput.toLowerCase().contains("/at");
        if (!hasAtKeyword) {
            return false;
        }

        boolean hasLengthGreaterThanOrEqualToFour = userInput.split(" ").length >= 4;
        boolean hasDescriptionAndDateSpecified = userInput.substring(5).split(" /at ").length >= 2;

        return hasAtKeyword && hasLengthGreaterThanOrEqualToFour && hasDescriptionAndDateSpecified;
    }

    /**
     * Adds an event type task to the task list and storage.
     *
     * @param userInput The task details input by the user.
     * @throws IllegalEventFormatException If the format for adding an event task is not followed.
     * @throws IllegalDateFormatException If the date format is not followed.
     */
    public void addEventTask(String userInput) throws IllegalEventFormatException, IllegalDateFormatException {
        int prefixLengthPlusSpace = 6;

        // Making sure that a properly formatted event task is entered.
        if (!isEventFormatCorrect(userInput)) {
            throw new IllegalEventFormatException("Please Follow The Format For Adding An Event Task:\n"
                    + "\tevent task_description /at time");
        }

        // Parsing the user input to obtain the information about the task.
        String[] userInputArray = userInput.substring(prefixLengthPlusSpace).split(" /at ");

        // Making sure that the date is properly formatted.
        TaskDateAndTime eventDate = new TaskDateAndTime(userInputArray[1]);
        if (!eventDate.isValidDate()) {
            throw new IllegalDateFormatException("Please Follow The Date Format DD/MM/YYYY");
        }

        Task eventTask = new Event(userInputArray[0], eventDate);
        userTaskList.add(eventTask);
        taskStorage.writeTask(Task.TaskType.EVENT, userInputArray[0], userInputArray[1]);
        ui.printTaskAdded(eventTask, taskListLength());
    }

    /**
     * Returns a boolean suggesting whether the format for adding a
     * todo task is followed or not.
     *
     * @param userInput The command entered by the user, to be checked.
     * @return A boolean, true, if the format for adding the todo task is followed.
     */
    public boolean isTodoFormatCorrect(String userInput) {
        boolean hasLengthGreaterThanOrEqualToTwo = userInput.split(" ").length >= 2;
        if (!hasLengthGreaterThanOrEqualToTwo) {
            return false;
        }

        boolean hasDescriptionSpecified = userInput.substring(5).compareTo("") != 0;

        return hasLengthGreaterThanOrEqualToTwo && hasDescriptionSpecified;
    }

    /**
     * Adds a todo type task to the task list and storage.
     *
     * @param userInput The task details input by the user.
     * @throws IllegalTodoFormatException If the format for adding a todo task is not followed.
     */
    public void addTodoTask(String userInput) throws IllegalTodoFormatException {
        int prefixLengthPlusSpace = 5;

        // Making sure that a properly formatted todo task is entered.
        if (!isTodoFormatCorrect(userInput)) {
            throw new IllegalTodoFormatException("Please Follow The Format For Adding A Todo Task:\n"
                    + "\ttodo task_description");
        }

        // Parsing the user input to obtain the information about the task.
        String userInputTodo = userInput.substring(prefixLengthPlusSpace);

        Task todoTask = new Todo(userInputTodo);
        userTaskList.add(todoTask);
        taskStorage.writeTask(Task.TaskType.TODO, userInputTodo, "N/A");
        ui.printTaskAdded(todoTask, taskListLength());
    }

    /**
     * Returns a boolean suggesting if the task number is entered
     * correctly.
     *
     * @param parsedUserInput The parsed user input which should hold the task number.
     * @return A boolean, true, if the task number is entered well.
     */
    public boolean isTaskNumberSpecified(String[] parsedUserInput) {
        boolean isArrayLengthGreaterThanOne = parsedUserInput.length > 1;
        if (!isArrayLengthGreaterThanOne) {
            return false;
        }
        boolean hasANumber = parsedUserInput[1].compareTo("") != 0;
        boolean isDigit = Character.isDigit(parsedUserInput[1].charAt(0));

        return isArrayLengthGreaterThanOne && hasANumber && isDigit;
    }

    /**
     * Returns a boolean suggesting whether the task number entered is
     * in the valid range according to the user task list length.
     *
     * @param taskNumber The task number to check.
     * @return A boolean, true, if the task number is in the valid range.
     */
    public boolean isTaskNumberInRange(int taskNumber) {
        return (taskNumber <= (userTaskList.size() - 1)) && (taskNumber >= 0);
    }
    /**
     * Deletes a particular task from the task list and storage.
     *
     * @param parsedUserInput The parsed command, entered by the user with the "delete" keyword.
     * @throws IllegalTaskNumberException If the task number entered is not a number.
     * @throws TaskNotFoundException If the entered task number does not exist.
     */
    public void deleteTask(String[] parsedUserInput) throws IllegalTaskNumberException, TaskNotFoundException {
        // Checking if a task number is entered and is an integer.
        if (!isTaskNumberSpecified(parsedUserInput)) {
            throw new IllegalTaskNumberException("Please Enter An Integer Value For Task Number");
        }

        // Obtaining the task number to be deleted.
        int taskNumber = (Integer.parseInt(parsedUserInput[1])) - 1;

        // Making sure the task number exists in the list.
        if (!isTaskNumberInRange(taskNumber)) {
            throw new TaskNotFoundException("Sorry! The Task Number Entered Does Not Exist!");
        }

        Task task = userTaskList.get(taskNumber);
        userTaskList.remove(taskNumber);
        taskStorage.deleteTask(taskNumber);
        ui.printTaskDeleted(task, taskListLength());
    }

    /**
     * Marks a particular task as done in the task list and storage.
     *
     * @param parsedUserInput The parsed command, entered by the user with the "done" keyword.
     * @throws IllegalTaskNumberException If the task number entered is not a number.
     * @throws TaskNotFoundException If the entered task number does not exist.
     */
    public void markTaskAsDone(String[] parsedUserInput) throws IllegalTaskNumberException, TaskNotFoundException {
        // Checking if a task number is entered and is an integer.
        if (!isTaskNumberSpecified(parsedUserInput)) {
            throw new IllegalTaskNumberException("Please Enter An Integer Value For Task Number");
        }

        // Obtaining the task number to be marked as done.
        int taskNumber = (Integer.parseInt(parsedUserInput[1])) - 1;

        // Making sure the task number exists in the list.
        if (!isTaskNumberInRange(taskNumber)) {
            throw new TaskNotFoundException("Sorry! The Task Number Entered Does Not Exist!");
        }

        Task task = userTaskList.get(taskNumber);
        task.markAsDone();
        taskStorage.updateTaskStatusToDone(taskNumber);
        ui.printTaskMarkedAsDone(task);
    }

    /**
     * Finds tasks with the given keyword and prints them.
     *
     * @param parsedUserInput The parsed command, entered by the user with the "find" keyword.
     */
    public void findTaskWithKeyword(String[] parsedUserInput) {
        String taskDescription;
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (int i = 0; i < taskListLength(); i++) {
            taskDescription = userTaskList.get(i).toString().toLowerCase();

            if (taskDescription.contains(parsedUserInput[1].toLowerCase())) {
                matchingTasks.add(userTaskList.get(i));
            }
        }

        ui.printTasksFoundByKeyword(matchingTasks);
    }

    public int numberOfDaysInTheMonth(int month, int year) {
        switch(month) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            return 31;
        case 4:
        case 6:
        case 9:
        case 11:
            return 30;
        case 2:
            if (year % 4 == 0) {
                return 29;
            }
            return 28;
        default:
            return 0;
        }
    }

    public boolean isDateRangeValid(String[] enteredDate) {
        int enteredDay = Integer.parseInt(enteredDate[0]);
        int enteredMonth = Integer.parseInt(enteredDate[1]);
        int enteredYear = Integer.parseInt(enteredDate[2]);

        boolean isValidMonth = enteredMonth >= 1 && enteredMonth <= 12;
        boolean isValidDay = enteredDay >= 1 && enteredDay <= numberOfDaysInTheMonth(enteredMonth, enteredYear);

        return isValidMonth && isValidDay;
    }

    public boolean isViewScheduleFormatCorrect(String[] parsedUserInput) {
        boolean hasDate = parsedUserInput.length == 2;
        if(!hasDate) {
            return false;
        }

        String[] enteredDate = parsedUserInput[1].split("/");
        boolean isDateFormatValid = enteredDate.length == 3;
        if(!isDateFormatValid) {
            return false;
        }

        boolean isDateValid = isDateRangeValid(enteredDate);
        return isDateValid;
    }

    public void viewScheduleForAParticularDay(String[] parsedUserInput) throws RetrieverException {
        if(!isViewScheduleFormatCorrect(parsedUserInput)) {
            throw new IllegalCommandException("Please Follow The Specified Format: view DD/MM/YYYY");
        }

        TaskDateAndTime enteredDate = new TaskDateAndTime(parsedUserInput[1]);
        ArrayList<Task> scheduledTasksForTheDay = new ArrayList<Task>();

        for (int i = 0; i < taskListLength(); i++) {
            Task task = userTaskList.get(i);
            if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;
                if (deadlineTask.isOnDate(enteredDate)) {
                    scheduledTasksForTheDay.add(task);
                }
            } else if (task instanceof Event) {
                Event eventTask = (Event) task;
                if (eventTask.isOnDate(enteredDate)) {
                    scheduledTasksForTheDay.add(task);
                }
            } else {
                // Don't Add
            }
        }

        ui.printTasksScheduledForTheDay(scheduledTasksForTheDay);
    }

    /**
     * Prints the tasks stored in the list.
     */
    public void printTaskList() {
        ui.printTaskList(userTaskList);
    }
}

