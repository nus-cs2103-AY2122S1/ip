package retriever.task;

import java.util.ArrayList;

import retriever.Storage;
import retriever.Ui;

import retriever.exception.IllegalDeadlineFormatException;
import retriever.exception.IllegalDateFormatException;
import retriever.exception.IllegalEventFormatException;
import retriever.exception.IllegalTaskNumberException;
import retriever.exception.IllegalTodoFormatException;
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

    /***
     * Returns the number of tasks present in the task list.
     *
     * @return The length of the user task list.
     */
    public int taskListLength() {
        return userTaskList.size();
    }

    /**
     * Adds a deadline type task to the task list and storage.
     *
     * @param userInput The task details input by the user.
     * @throws IllegalDeadlineFormatException If the format for adding a deadline task is not followed.
     * @throws IllegalDateFormatException If the date format is not followed.
     */
    public void addDeadlineTask(String userInput) throws IllegalDeadlineFormatException, IllegalDateFormatException {
        // Making sure that a properly formatted deadline task is entered.
        if (!userInput.toLowerCase().contains("/by")
                || userInput.split(" ").length < 4
                || userInput.substring(9).split(" /by ").length < 2) {
            throw new IllegalDeadlineFormatException("Please Follow The Format For Adding A Deadline Task:\n"
                    + "\tdeadline task_description /by DD/MM/YYYY");
        }

        // Parsing the user input to obtain the information about the task.
        String[] userInputArray = userInput.substring(9).split(" /by ");

        // Making sure that the time is properly formatted.
        TaskDateAndTime deadlineDate = new TaskDateAndTime(userInputArray[1]);
        if(!deadlineDate.isValidDate()) {
            throw new IllegalDateFormatException("Please Follow The Date Format DD/MM/YYYY");
        }

        Task deadlineTask = new Deadline(userInputArray[0], deadlineDate);
        userTaskList.add(deadlineTask);
        taskStorage.writeTask(Task.TaskType.DEADLINE, userInputArray[0], userInputArray[1]);
        ui.printTaskAdded(deadlineTask, taskListLength());
    }

    /**
     * Adds an event type task to the task list and storage.
     *
     * @param userInput The task details input by the user.
     * @throws IllegalEventFormatException If the format for adding an event task is not followed.
     * @throws IllegalDateFormatException If the date format is not followed.
     */
    public void addEventTask(String userInput) throws IllegalEventFormatException, IllegalDateFormatException {
        // Making sure that a properly formatted event task is entered.
        if (!userInput.toLowerCase().contains("/at")
                || userInput.split(" ").length < 4
                || userInput.substring(5).split(" /at ").length < 2) {
            throw new IllegalEventFormatException("Please Follow The Format For Adding An Event Task:\n"
                    + "\tevent task_description /at time");
        }

        // Parsing the user input to obtain the information about the task.
        String[] userInputArray = userInput.substring(6).split(" /at ");

        // Making sure that the date is properly formatted.
        TaskDateAndTime eventDate = new TaskDateAndTime(userInputArray[1]);
        if(!eventDate.isValidDate()) {
            throw new IllegalDateFormatException("Please Follow The Date Format DD/MM/YYYY");
        }

        Task eventTask = new Event(userInputArray[0], eventDate);
        userTaskList.add(eventTask);
        taskStorage.writeTask(Task.TaskType.EVENT, userInputArray[0], userInputArray[1]);
        ui.printTaskAdded(eventTask, taskListLength());
    }

    /**
     * Adds a todo type task to the task list and storage.
     *
     * @param userInput The task details input by the user.
     * @throws IllegalTodoFormatException If the format for adding a todo task is not followed.
     */
    public void addTodoTask(String userInput) throws IllegalTodoFormatException {
        // Making sure that a properly formatted todo task is entered.
        if (userInput.split(" ").length < 2
                || userInput.substring(5).compareTo("") == 0) {
            throw new IllegalTodoFormatException("Please Follow The Format For Adding A Todo Task:\n"
                    + "\ttodo task_description");
        }

        // Parsing the user input to obtain the information about the task.
        String userInputTodo = userInput.substring(5);

        Task todoTask = new Todo(userInputTodo);
        userTaskList.add(todoTask);
        taskStorage.writeTask(Task.TaskType.TODO, userInputTodo, "N/A");
        ui.printTaskAdded(todoTask, taskListLength());
    }

    /**
     * Deletes a particular task from the task list and storage.
     *
     * @param parsedUserInput The parsed command, entered by the user with the "delete" keyword.
     * @throws IllegalTaskNumberException If the task number entered is not a number.
     * @throws TaskNotFoundException If the entered task number does not exist.
     */
    public void deleteTask(String[] parsedUserInput) throws IllegalTaskNumberException, TaskNotFoundException {
        String[] holder = parsedUserInput;

        // Checking if a task number is entered and is an integer.
        if (holder.length <= 1 || holder[1].compareTo("") == 0
                || !Character.isDigit(holder[1].charAt(0))) {
            throw new IllegalTaskNumberException("Please Enter An Integer Value For Task Number");
        }

        // Obtaining the task number to be deleted.
        int taskNumber = (Integer.parseInt(holder[1])) - 1;

        // Making sure the task number exists in the list.
        if (taskNumber <= (userTaskList.size() - 1) && taskNumber >= 0) {
            Task task = userTaskList.get(taskNumber);
            userTaskList.remove(taskNumber);
            taskStorage.deleteTask(taskNumber);
            ui.printTaskDeleted(task, taskListLength());
        } else {
            throw new TaskNotFoundException("Sorry! The Task Number Entered Does Not Exist!");
        }
    }

    /**
     * Marks a particular task as done in the task list and storage.
     *
     * @param parsedUserInput The parsed command, entered by the user with the "done" keyword.
     * @throws IllegalTaskNumberException If the task number entered is not a number.
     * @throws TaskNotFoundException If the entered task number does not exist.
     */
    public void markTaskAsDone(String[] parsedUserInput) throws IllegalTaskNumberException, TaskNotFoundException {
        // Parsing the user input.
        String[] holder = parsedUserInput;

        // Checking if a task number is entered and is an integer.
        if (holder.length <= 1 || holder[1].compareTo("") == 0
                || !Character.isDigit(holder[1].charAt(0))) {
            throw new IllegalTaskNumberException("Please Enter An Integer Value For Task Number");
        }

        // Obtaining the task number to be marked as done.
        int taskNumber = (Integer.parseInt(holder[1])) - 1;

        // Making sure the task number exists in the list.
        if (taskNumber <= (userTaskList.size() - 1) && taskNumber >= 0) {
            Task task = userTaskList.get(taskNumber);
            task.markAsDone();
            taskStorage.updateTaskStatusToDone(taskNumber);
            ui.printTaskMarkedAsDone(task);
        } else {
            throw new TaskNotFoundException("Sorry! The Task Number Entered Does Not Exist!");
        }
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

        ui.printTaskFoundByKeyword(matchingTasks);
    }

    /**
     * Prints the tasks stored in the list.
     */
    public void printTaskList() {
        // If the list is empty
        if (userTaskList.size() == 0) {
            System.out.println("My Memory Is Empty, Please Feed Items!");
        } else {
            System.out.println("-> Your Tasks, My Master:");
            for (int i = 0; i < userTaskList.size(); i++) {
                System.out.println("\t" + ((i + 1) + ". " + userTaskList.get(i)));
            }
        }
    }
}

