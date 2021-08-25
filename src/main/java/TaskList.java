import java.util.ArrayList;
import java.time.LocalDate;

import java.time.format.DateTimeParseException;

/**
 * Represents the list of tasks a user has.
 * Tasks can be added, deleted, completed or simply printed out.
 */
public class TaskList {
	protected static ArrayList<Task> tasks;

	public TaskList(ArrayList<Task> tasks) {
		TaskList.tasks = tasks;
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}

	
    /**
     * Mark a task as done.
     * 
     * @param taskNum the task number to mark as done
     */
    protected void completeTask(int taskNum) {
        Task task = tasks.get(taskNum - 1);
        task.setDone(true);
        Duke.printFormattedMessage("Good job! I've marked this task as done:\n\n\t" + task + "\n");  
    }

	/**
     * Delete a task from the ArrayList of tasks.
     * 
     * @param num the task number to delete
     */
    protected void deleteTask(int num) {
        int taskIdx = num - 1;
        Task taskToDelete = tasks.get(taskIdx);
        tasks.remove(taskIdx);
        Duke.printFormattedMessage("Noted. I've removed this task:\n\t" 
                                + taskToDelete
                                + "\n\tNow you have " + tasks.size() + " tasks in the list.\n"); 
    } 


	/**
     * Delete or mark a task as done.
     * 
     * @param command user input to extract task number
     * @param action delete or mark as done
     * @throws DukeException
     */
    protected void handleTasksOperation(String command, String action) throws DukeException {
        if (command.equals(action)) {
            throw new DukeException("You need to specify the task!\n");
        }

        try {
            int taskNum = Integer.parseInt(command.split(" ")[1]);
            if (action.equals("done")) completeTask(taskNum);
            else deleteTask(taskNum);
        } catch (NumberFormatException err) {
            throw new DukeException("Please use the task number instead of task name!\n");
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException("I'm sorry, but that task number is out of range.\n");
        }
    }

	    /**
     * Add a task to the ArrayList of tasks.
     * 
     * @param t the task to add
     */
    private static void addTask(Task t) throws DukeException {
		tasks.add(t);
        Duke.printFormattedMessage("Got it. I've added this task:\n\t" 
                                + t 
                                + "\n\tNow you have " + tasks.size() + " tasks in the list.\n"); 
    } 

    /**
     * Add ToDo task to the ArrayList of tasks.
     * 
     * @param command user input to extract task
     * @throws DukeException
     */
    protected void addToDo(String command) throws DukeException {
        if (command.equals("todo")) {
            throw new DukeException("You need to specify which task you want to add!\n");
        }

        String todo = command.split("todo")[1].trim();
        addTask(new ToDo(todo));
    }

    /**
     * Add deadline or event task with date/time.
     * 
     * @param command user input to extract task and date/time
     * @param type    type of task to add
     * @throws DukeException
     */
    protected void addTaskWithTime(String command, String type) throws DukeException {
        // ToDo: Split into addDeadline and addEvent and create helper methods
        boolean isDeadline = type.equals("deadline");
        String splitWord = isDeadline ? "/by " : "/at ";
        String[] commandSplit = command.split(splitWord);   // Split the task from the date/time

        // If cannot split the command, throw an exception
        if (commandSplit.length <= 1) {
            throw new DukeException("You need to provide a date/time!" + "\n");
        }
        
        String task = commandSplit[0].split(type)[1].trim();   // Everything after the action
        if (task.isEmpty()) {
            throw new DukeException("You need to provide a task!" + "\n");
        }

        String dateTime = commandSplit[1];      // dateTime is the second part of the command

        if (isDeadline) {
            // Check if the time is in the yyyy-mm-dd datetime format 
            try {
                LocalDate date = LocalDate.parse(dateTime);
                addTask(new Deadline(task, date));
            } catch (DateTimeParseException err) {
                throw new DukeException("Please use the dd/mm/yyyy format for deadline!\n");    
            }
        } else {
            addTask(new Event(task, dateTime));
        }
    }

	/**
     * Print all the tasks in the ArrayList of tasks.
     */
    protected void printTasks() throws DukeException{
        if (tasks.isEmpty()) {
            Duke.printFormattedMessage("You have no tasks!\n");
            return;
        }
        
		String taskListMessage = "I present to you, your collection of tasks!\n\n";

        for (int i = 0; i < tasks.size(); i++) {
            int taskNum = i + 1;
            String task = "\t" + taskNum+ ". " + tasks.get(i);
            taskListMessage += task + "\n";
        }

		Duke.printFormattedMessage(taskListMessage);
    }
}