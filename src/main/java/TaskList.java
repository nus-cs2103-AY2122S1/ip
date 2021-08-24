package duke;

import duke.Deadline;
import duke.Event;
import duke.Storage;
import duke.Task;
import duke.ToDo;

import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> tasks;
    public int counter = 0;
    static String dash = "__________________________________";

    /**
     * Creates a TaskList object that stores a list of tasks in an ArrayList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Prints the user's existing list of tasks.
     */
    public void list() {
        System.out.println(dash);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            Task currTask = tasks.get(i);
            System.out.println(i+1 + "." + currTask.toString());
        }
        System.out.println(dash);
    }

    /**
     * Marks the selected task as completed.
     *
     * @param userInput Index of task according to the list displayed.
     */
    public void markDone(String userInput) {
        if (userInput.substring(4).length() == 0) {
            System.out.println(dash + '\n' + "Sorry, which task do you wish to mark as completed?" + '\n'+ dash);
            // throw new DukeException("User has not indicated task to mark as complete.");

        } else {
            Integer index = Integer.parseInt(userInput.substring(5));
            Task currTask = tasks.get(index - 1);
            currTask.completeTask();

            System.out.println(dash);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(currTask.toString());
            System.out.println(dash);
        }
    }

    /**
     * Reads data from text file and marks the task as completed.
     * Only performed during Duke's initialisation.
     *
     * @param num Index of task according to the data stored.
     */
    public void addDone(int num) {
        Task currTask = tasks.get(num - 1);
        currTask.completeTask();
    }

    /**
     * Deletes task from user's list.
     *
     * @param userInput Index of task according to the list displayed.
     */
    public void delete(String userInput) {
        if (userInput.substring(6).length() == 0) {
            System.out.println(dash + '\n' + "Sorry, which task do you wish to delete?" + '\n'+ dash);
            // throw new DukeException("User has not indicated task to delete.");

        } else {
            Integer index = Integer.parseInt(userInput.substring(7));
            Task currTask = tasks.get(index - 1);
            tasks.remove(currTask);

            System.out.println(dash);
            System.out.println("Noted. I've removed this task:");
            System.out.println(currTask.toString());

            counter -= 1;
            System.out.println("You now have " + counter + " task(s) in your list!");
            System.out.println(dash);
        }
    }

    /**
     * Creates a ToDo task and adds it to the user's list.
     *
     * @param userInput Description of task.
     */
    public void makeTodo(String userInput) {
        if (userInput.substring(4).length() == 0) {
            System.out.println(dash + '\n' + "YIKES!! The description of a todo cannot be empty!" + '\n'+ dash);
            // throw new DukeException("YIKES!! The description of a todo cannot be empty!");
        } else {
            tasks.add(new ToDo(userInput.substring(5)));

            System.out.println(dash);
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.get(counter).toString());
            counter += 1;

            System.out.println("You now have " + counter + " task(s) in the list.");
            System.out.println(dash);
        }
    }

    /**
     * Reads data from text file and creates a ToDo task.
     * Only performed during Duke's initialisation.
     *
     * @param userInput Description of task from text file.
     */
    public void addTodo(String userInput) {
        tasks.add(new ToDo(userInput));
        counter += 1;
    }

    /**
     * Creates an Event and adds it to the user's list.
     *
     * @param userInput Details of the Event.
     */
    public void makeEvent(String userInput) {
        if (userInput.substring(5).length() == 0) {
            System.out.println(dash + '\n' + "YIKES!! The description of an Event cannot be empty!" + '\n'+ dash);
            // throw new DukeException("YIKES!! The description of an Event cannot be empty!");
        } else {
            String output = userInput.substring(6);
            String[] info = output.split("/");
            tasks.add(new Event(info[0], info[1].substring(3)));

            System.out.println(dash);
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.get(counter).toString());
            counter += 1;

            System.out.println("You now have " + counter + " task(s) in the list.");
            System.out.println(dash);
        }
    }

    /**
     * Reads data from text file and creates an Event.
     * Only performed during Duke's initialisation.
     *
     * @param userInput Description of Event from text file.
     */
    public void addEvent(String userInput) {
        String[] info = userInput.split("/");
        tasks.add(new Event(info[0], info[1].substring(3)));
        counter += 1;
    }

    /**
     * Creates a Deadline task and adds it to the user's list.
     *
     * @param userInput Details of the Deadline task.
     */
    public void makeDeadline(String userInput) {
        if (userInput.substring(8).length() == 0) {
            System.out.println(dash + '\n' + "YIKES!! The description of a Deadline cannot be empty!" + '\n'+ dash);
            // throw new DukeException("YIKES!! The description of a Deadline cannot be empty!");
        } else {
            String output = userInput.substring(9);
            String[] info = output.split("/");
            tasks.add(new Deadline(info[0], info[1].substring(3)));

            System.out.println(dash);
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.get(counter).toString());
            counter += 1;

            System.out.println("You now have " + counter + " task(s) in the list.");
            System.out.println(dash);
        }
    }

    /**
     * Reads data from text file and creates a Deadline task.
     * Only performed during Duke's initialisation.
     *
     * @param userInput Description of Deadline task from text file.
     */
    public void addDeadline(String userInput) {
        String[] info = userInput.split("/");
        tasks.add(new Deadline(info[0], info[1].substring(3)));
        counter += 1;
    }

    /**
     * Finds tasks with keywords that match user's input.
     * Prints these tasks in a list for user's perusal.
     *
     * @param userInput Keyword provided by user.
     */
    public void find(String userInput) {
        if (userInput.substring(4).length() == 0) {
            System.out.println(dash + '\n' + "Uh Oh!! Please specify the keyword of a task!" + '\n'+ dash);

        } else {
            String keyword = userInput.substring(5);
            ArrayList<Task> matchedTasks = new ArrayList<Task>();

            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getDescription().contains(keyword)) {
                    matchedTasks.add(tasks.get(i));
                }
            }

            System.out.println(dash);
            System.out.println("Here are the tasks that fit your criteria:");
            for (int i = 0; i < matchedTasks.size(); i++) {
                System.out.println(i+1 + "." + matchedTasks.get(i).toString());
            }
            System.out.println(dash);
        }
    }

    /**
     * Alerts user to an invalid command.
     */
    public void error() {
        System.out.println(dash + '\n' + "OOPS!! I don't know how to respond to this command! :-(" + '\n'+ dash);
        // throw new DukeException("OOPS!! I don't know how to respond to this command! :-(");
    }

}