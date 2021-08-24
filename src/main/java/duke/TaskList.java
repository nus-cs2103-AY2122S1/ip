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
    private ArrayList<Task> list;

    /**
     * Constructor to create a new TaskList.
     *
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Constructor to create a new TaskList.
     *
     * @param list An ArrayList of Task that contains Tasks to be put inside the TaskList.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Method to print out tasks inside a TaskList.
     *
     * @return String representation of all the tasks inside the current TaskList.
     */
    public String printList() {
        String str = "";
        for (int i = 1; i <= list.size(); i++) {
            str = str + i + ". " + list.get(i-1) + "\n";
        }
        System.out.println(str);
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
    public Task markAsDone(String str) throws OutOfBoundException, NumberFormatException {
        String i = str.substring(str.length()-1);
        int index = Integer.parseInt(i);
        if (index < 0 || index > list.size()) {
            throw new OutOfBoundException();
        } else {
            Task task = list.get(index - 1);
            task.markAsDone();
            System.out.println("Nice! I have marked this task as done!");
            System.out.println(task);
            return task;
        }
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

    public Task addTask(String str) throws EmptyDescriptionException
            , InvalidTaskException, InvalidDeadlineException {
        //first check if the task only contain 1 word
        if (str.split(" ").length == 1) {
            //check if task if valid
            if (str.startsWith("todo") || str.startsWith("deadline") || str.startsWith("event")) {
                throw new EmptyDescriptionException(str);
            } else { //check for invalid task
                throw new InvalidTaskException();
            }
        }

        //task that contain more than 1 word.
        //check for invalid task. otherwise, add the correct task to the list.
        else {
            if (!str.startsWith("todo") && !str.startsWith("deadline") && !str.startsWith("event")) {
                throw new InvalidTaskException();
            }
            else {
                Task task;
                if (str.startsWith("todo")) {
                    task = new ToDos(str.substring(5));
                    list.add(task);
                } else if (str.startsWith("deadline")) {
                    String[] message = str.split("/by ");
                    if (message.length == 1) {
                        throw new InvalidTaskException();
                    } else {
                        task = new Deadline(message[0].substring(9), message[1]);
                        list.add(task);
                    }
                } else {
                    String[] message = str.split("/at ");
                    if (message.length == 1) {
                        throw new InvalidTaskException();
                    } else {
                        task = new Events(message[0].substring(6), message[1]);
                        list.add(task);
                    }
                }

                System.out.println("Got it. I've added this task.");
                System.out.println(list.get(list.size() - 1));
                System.out.println("Now you have " + list.size()
                        + (list.size() == 1 ? " task in the list" : " tasks in the list."));
                return task;
            }
        }
    }

    /**
     * A method to delete a certain in a TaskList.
     * @param str User inputs which indicates detail of task to be deleted.
     * @return Task after it is deleted.
     * @throws OutOfBoundException Thrown when user gives a task number that is outside of the range of TaskList.
     */
    public Task deleteTask(String str) throws OutOfBoundException {
        String i = str.substring(str.length()-1);
        int index = Integer.parseInt(i);

        if (index < 0 || index > list.size()) {
            throw new OutOfBoundException();
        } else {
            Task task = list.get(index - 1);
            list.remove(index - 1);
            System.out.println("Noted. I've removed this task: ");
            System.out.println(task);
            System.out.println("Now you have " + list.size()
                    + (list.size() == 1 ? " task in the list" : " tasks in the list."));
            return task;
        }
    }

    public TaskList findTask(String str)throws TaskDoesNotExistException {
        String t = str.substring(5);
        ArrayList<Task> l = new ArrayList<>();

        for (Task task: list) {
            if (task.getTitle().contains(t)) {
                l.add(task);
            }
        }

        if (l.isEmpty()) {
            throw new TaskDoesNotExistException();
        } else {
            System.out.println("Here are the matching tasks in your list:");
            TaskList list = new TaskList(l);
            list.printList();
            return list;
        }
    }

    public ArrayList<Task> getList() {
        return list;
    }
}
