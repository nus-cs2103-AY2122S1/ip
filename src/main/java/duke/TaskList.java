package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.util.Ui;
import duke.exception.DukeException;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents the list of tasks a user has.
 * Tasks can be added, deleted, completed or simply printed out.
 */
public class TaskList {
	protected ArrayList<Task> tasks;

    /**
     * Constructor for empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList with initial tasks.
     * 
     * @param tasks Initial tasks.
     */
	public TaskList(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}

    /**
     * Marks a task as done.
     * 
     * @param taskNum the task number to mark as done
     * @throws DukeException if file not found
     */
    protected void completeTask(int taskNum) throws DukeException {
        Task task = tasks.get(taskNum - 1);
        task.setDone(true);
        Ui.printFormattedMessage("Good job! I've marked this task as done:\n\n\t" + task + "\n");
        Duke.storage.saveTasks(this.getTasks());
    }

	/**
     * Deletes a task from the ArrayList of tasks.
     * 
     * @param num the task number to delete
     */
    protected void deleteTask(int num) throws DukeException{
        int taskIdx = num - 1;
        Task taskToDelete = tasks.get(taskIdx);
        tasks.remove(taskIdx);
        Ui.printFormattedMessage("Noted. I've removed this task:\n\t" 
                                + taskToDelete
                                + "\n\tNow you have " + tasks.size() + " tasks in the list.\n");
        Duke.storage.saveTasks(this.getTasks());
    } 

    /**
     * Gets the task number in the list.
     * 
     * @param command Command to extract task number from.
     * @return Task number.
     * @throws DukeException Task number not valid
     */
    public int getTaskNum(String command) throws DukeException {
        try {
            return Integer.parseInt(command.split(" ")[1]);
        } catch (NumberFormatException err) {
            throw new DukeException("Please use the task number instead of task name!\n");
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException("I'm sorry, but that task number is out of range.\n");
        }
    }

	/**
     * Handles task deletion.
     * 
     * @param command user input to parse
     * @throws DukeException task not specified
     */
    public void handleDelete(String command) throws DukeException {
        if (command.equals("delete")) {
            throw new DukeException("You need to specify the task!\n");
        }

        int taskNum = getTaskNum(command);
        deleteTask(taskNum);
    }

    /**
     * Handles task completion.
     * 
     * @param command user input to parse
     * @throws DukeException task not specified
     */
    public void handleDone(String command) throws DukeException {
        if (command.equals("done")) {
            throw new DukeException("You need to specify the task!\n");
        }

        int taskNum = getTaskNum(command);
        completeTask(taskNum);
    }

    /**
     * Adds a task to the ArrayList of tasks.
     * 
     * @param t the task to add
     */
    private void addTask(Task t) throws DukeException {
		this.tasks.add(t);
        Ui.printFormattedMessage("Got it. I've added this task:\n\t" 
                                + t 
                                + "\n\tNow you have " + tasks.size() + " tasks in the list.\n"); 
        Duke.storage.saveTasks(this.getTasks());
    } 

    /**
     * Adds ToDo task to the ArrayList of tasks.
     * 
     * @param command user input to extract task
     * @throws DukeException Task not specified
     */
    public void addToDo(String command) throws DukeException {
        if (command.equals("todo")) {
            throw new DukeException("You need to specify which task you want to add!\n");
        }

        String todo = command.split("todo")[1].trim();
        addTask(new ToDo(todo));
    }

    /**
     * Adds event task with datetime.
     * 
     * @param command User input to extract task and datetime
     * @throws DukeException Task not specified
     */
    public void addEvent(String command) throws DukeException {
        if (command.equals("event")) {
            throw new DukeException("You need to specify which event you want to add!\n");
        }

        String eventDetails = command.substring(6);
        String[] commandSplit = splitCommand(eventDetails, "/at");  // "taskName /at datetime"
        String task = getTask(commandSplit);              
        String dateTime = getDateTime(commandSplit);    // dateTime is the 2nd part of the command
        addTask(new Event(task, dateTime));
    }

    /**
     * Adds deadline task with date/time.
     * 
     * @param command User input to extract task and datetime
     * @throws DukeException Task not specified
     */
    public void addDeadline(String command) throws DukeException {
        if (command.equals("deadline")) {
            throw new DukeException("You need to specify which deadline you want to add!\n");
        }

        String taskDetails = command.substring(9);
        String[] commandSplit = splitCommand(taskDetails, "/by");   
        String task = getTask(commandSplit);                         
        String dateTime = getDateTime(commandSplit);     
       
        // Check if the time is in the yyyy-mm-dd datetime format 
        try {
            LocalDate date = LocalDate.parse(dateTime);
            addTask(new Deadline(task, date));
        } catch (DateTimeParseException err) {
            throw new DukeException("Please use the yyyy-mm-dd format for deadline!\n");    
        }
    }

    /**
     * Splits the command into task and datetime.
     * 
     * @param command User input to extract task and dateTime
     * @param by The string to split the command by
     * @return The task and dateTime in a String array
     */
    public String[] splitCommand(String command, String by) throws DukeException {
        String[] commandSplit = command.split(by);
        
        // If cannot split the command, throw an exception
        if (commandSplit.length <= 1) {
            throw new DukeException("You need to provide a date/time!" + "\n");
        }
        return commandSplit;
    }

    /**
     * Gets the task from the split original command.
     */
    public String getTask(String[] commandSplit) throws DukeException {
        String task = commandSplit[0].trim();   // Trim the first part of the original command

        if (task.isEmpty()) {
            throw new DukeException("You need to provide a task!" + "\n");
        }

        return task;
    }

    /**
     * Gets the datetime from the split original command.
     * 
     * @param commandSplit the original command split into 2 parts
     * @return the datetime in String format
     */
    public String getDateTime(String[] commandSplit) {
        return commandSplit[1].trim();  // Get the 2nd part of the command
    }

	/**
     * Prints all the tasks in the ArrayList of tasks.
     */
    public void printTasks(String keyword) {
        if (tasks.isEmpty()) {
            Ui.printFormattedMessage("You have no tasks!\n");
            return;
        }
        
		StringBuilder taskListMessage = new StringBuilder("I present to you, your collection of tasks!\n\n");

        for (int i = 0; i < tasks.size(); i++) {
            int taskNum = i + 1;
            String task = "\t" + taskNum + ". " + tasks.get(i);
            if (task.contains(keyword)) {
                taskListMessage.append(task).append("\n");
            }
        }

		Ui.printFormattedMessage(taskListMessage.toString());
    }

    public void findTasks(String command) throws DukeException{
        if (command.trim().equals("find")) {
            throw new DukeException("You need to specify a keyword!");
        }

        String keyword = command.split(" ")[1];
        printTasks(keyword);
    }
}