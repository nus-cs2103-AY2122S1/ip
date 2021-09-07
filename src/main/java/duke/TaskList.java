package duke;

import duke.exception.*;

import java.util.ArrayList;

/**
 * A class that represents a list of Tasks.
 *
 * It contains a constructor, a getter,
 * a method to print a list, add a task to a list,
 * mark a task as done and delete a task.
 *
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor to create a new TaskList.
     *
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor to create a new TaskList.
     *
     * @param tasks An ArrayList of Task that contains Tasks to be put inside the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Method to print out tasks inside a TaskList.
     *
     * @return String representation of all the tasks inside the current TaskList.
     */
    public String printList() {
        String str = "Here are the tasks on your list: \n";
        for (int i = 1; i <= tasks.size(); i++) {
            str = str + i + ". " + tasks.get(i-1) + "\n";
        }
        return str;
    }

    /**
     * A method to mark a task as done.
     *
     * @param str User input which indicates details of the task to be marked as done.
     * @return Task after it is marked as done.
     * @throws OutOfBoundException Thrown when user inputs task number that is out of the given task range.
     * @throws NumberFormatException Thrown when user gives a non-number as the task number.
     */
    public String markAsDone(String str) throws OutOfBoundException, NumberFormatException {
        String i = str.substring(str.length()-1);
        int index = Integer.parseInt(i);

        if (index < 0 || index > tasks.size()) {
            throw new OutOfBoundException();
        }
        Task task = tasks.get(index - 1);
        task.markAsDone();
        String result = "Nice! I have marked this task as done!\n" + task.toString() ;
        return result;
    }

    /**
     * A method to add a Task inside a TaskList
     *
     * @param str User inputs which indicates details of the task to be added.
     * @return Task after it is added.
     * @throws EmptyDescriptionException Thrown when users do not give a task description.
     * @throws InvalidTaskException Thrown when users give incorrect input for task.
     * @throws InvalidDeadlineException Thrown when users give an incorrect deadline.
     */

    public String addTask(String str) throws DukeException {
        //if the task only contain 1 word
        if (str.split(" ").length == 1) {
            if (str.startsWith("todo") || str.startsWith("deadline") || str.startsWith("event")) {
                throw new EmptyDescriptionException(str);
            }
            throw new InvalidTaskException();
        }

        //if task contains more than 1 word, but keyword is wrong
        if (!str.startsWith("todo") && !str.startsWith("deadline") && !str.startsWith("event")) {
            throw new InvalidTaskException();
        }

        Task task;
        if (str.startsWith("todo")) {
            task = new ToDos(str.substring(5));
            tasks.add(task);
        } else if (str.startsWith("deadline")) {
            String[] message = str.split("/by ");
            if (message.length != 2) {
                throw new InvalidTaskException();
            }
            task = new Deadline(message[0].substring(9), message[1]);
            tasks.add(task);
        } else {
            String[] message = str.split("/at ");
            if (message.length != 2) {
                throw new InvalidTaskException();
            }
            task = new Event(message[0].substring(6), message[1]);
            tasks.add(task);
        }

        String result = "Got it. I've added this task.\n"
                + tasks.get(tasks.size() - 1).toString() + "\r\n"
                + "Now you have " + tasks.size()
                + (tasks.size() == 1 ? " task in the list" : " tasks in the list.");
        return result;
    }

    /**
     * A method to delete a certain in a TaskList.
     *
     * @param str User inputs which indicates detail of task to be deleted.
     * @return Task after it is deleted.
     * @throws OutOfBoundException Thrown when user gives a task number that is outside of the range of TaskList.
     */
    public String deleteTask(String str) throws OutOfBoundException {
        String i = str.substring(str.length() - 1);
        int index = Integer.parseInt(i);

        if (index < 0 || index > tasks.size()) {
            throw new OutOfBoundException();
        }
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);

        String result = "Noted. I've removed this task: \n" + task.toString() + "\r\n"
                + "Now you have " + tasks.size()
                + (tasks.size() == 1 ? " task in the list" : " tasks in the list.");

        return result;
    }

    /**
     * Method to find a task given an input string.
     *
     * @param str Input string from user to find a certain task.
     * @return TaskList which contains all the tasks that match.
     * @throws TaskDoesNotExistException when there is no task that matches.
     */

    public String findTask(String str)throws TaskDoesNotExistException {
        String t = str.substring(5);
        ArrayList<Task> l = new ArrayList<>();

        for (Task task: tasks) {
            if (task.getTitle().contains(t)) {
                l.add(task);
            }
        }

        if (l.isEmpty()) {
            throw new TaskDoesNotExistException();
        }
        String result = "Here are the matching tasks in your list: \n";
        TaskList list = new TaskList(l);
        result += list.printList() + "\r\n";
        return result;
    }

    /**
     * Getter to get ArrayList
     *
     * @return ArrayList of the current tasks.
     */
    public ArrayList<Task> getList() {
        return tasks;
    }
}
