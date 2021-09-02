package duke;

import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> tasks;
    public int counter = 0;

    /**
     * Creates a TaskList object that stores a list of tasks in an ArrayList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Prints the user's existing list of tasks.
     *
     * @return List of tasks.
     */
    public String list() {
        String result = "Here are the tasks in your list: \n";

        System.out.println("Here are the tasks in your list:");
        System.out.println(counter);
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            result += String.format("%d. %s", i + 1, currTask.toString());
            result += "\n";
            System.out.println(i + 1 + "." + currTask.toString());
        }
        return result;
    }

    /**
     * Marks the selected task as completed.
     *
     * @param userInput Index of task according to the list displayed.
     * @return String output of completed task.
     */
    public String markDone(String userInput) {
        if (userInput.substring(4).length() == 0) {
            // throw new DukeException("User has not indicated task to mark as complete.");
            return "Sorry, which task do you wish to mark as completed?";

        } else {
            Integer index = Integer.parseInt(userInput.substring(5));
            Task currTask = tasks.get(index - 1);
            currTask.completeTask();

            String result = "Nice! I've marked this task as done: \n";
            result += currTask.toString();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(currTask.toString());
            return result;
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
     * @return String output of deleted task.
     */
    public String delete(String userInput) {
        if (userInput.substring(6).length() == 0) {
            // throw new DukeException("User has not indicated task to delete.");
            return "Sorry, which task do you wish to delete?";

        } else {
            Integer index = Integer.parseInt(userInput.substring(7));
            Task currTask = tasks.get(index - 1);
            tasks.remove(currTask);
            String result = "Noted. I've removed this task: \n";
            result += currTask.toString();

            counter -= 1;
            result += "\n";
            result += String.format("You now have %d task(s) in your list!", counter);
            return result;
        }
    }

    /**
     * Creates a ToDo task and adds it to the user's list.
     *
     * @param userInput Description of task.
     * @return String output of ToDo task.
     */
    public String makeTodo(String userInput) {
        if (userInput.substring(4).length() == 0) {
            // throw new DukeException("YIKES!! The description of a todo cannot be empty!");
            return "YIKES!! The description of a todo cannot be empty!";
        } else {
            tasks.add(new ToDo(userInput.substring(5)));
            String result = "Got it. I've added this task: \n";
            result += tasks.get(counter).toString();
            result += "\n";
            counter += 1;

            result += String.format("You now have %d task(s) in your list.", counter);
            return result;
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
     * @return String output of Event task.
     */
    public String makeEvent(String userInput) {
        if (userInput.substring(5).length() == 0) {
            // throw new DukeException("YIKES!! The description of an Event cannot be empty!");
            return "YIKES!! The description of an Event cannot be empty!";
        } else {
            String output = userInput.substring(6);
            String[] info = output.split("/");
            tasks.add(new Event(info[0], info[1].substring(3)));
            String result = "Got it. I've added this task: \n";
            result += tasks.get(counter).toString();
            result += "\n";
            counter += 1;

            result += String.format("You now have %d task(s) in your list.", counter);
            return result;
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
     * @return String output of Deadline task.
     */
    public String makeDeadline(String userInput) {
        if (userInput.substring(8).length() == 0) {
            // throw new DukeException("YIKES!! The description of a Deadline cannot be empty!");
            return "YIKES!! The description of a Deadline cannot be empty!";
        } else {
            String output = userInput.substring(9);
            String[] info = output.split("/");
            tasks.add(new Deadline(info[0], info[1].substring(3)));
            String result = "Got it. I've added this task: \n";
            result += tasks.get(counter).toString();
            result += "\n";
            counter += 1;

            result += String.format("You now have %d task(s) in your list.", counter);
            return result;
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
     * @return List of tasks that fit user's criteria.
     */
    public String find(String userInput) {
        if (userInput.substring(4).length() == 0) {
            return "Uh Oh!! Please specify the keyword of a task!";

        } else {
            String keyword = userInput.substring(5);
            ArrayList<Task> matchedTasks = new ArrayList<Task>();

            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getDescription().contains(keyword)) {
                    matchedTasks.add(tasks.get(i));
                }
            }

            String result = "Here are the tasks that fit your criteria: \n";
            for (int i = 0; i < matchedTasks.size(); i++) {
                result += String.format("%d. %s", i + 1, matchedTasks.get(i).toString());
            }
            return result;
        }
    }

    /**
     * Alerts user to an invalid command.
     */
    public String error() {
        return "OOPS!! I don't know how to respond to this command! :-(";
    }

}
