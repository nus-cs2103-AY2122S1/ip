package duke;

import java.util.ArrayList;

public class TaskList {
    static final int DEADLINE_LENGTH = 8;
    static final int DELETE_LENGTH = 6;
    static final int DONE_LENGTH = 4;
    static final int EMPTY = 0;
    static final int EVENT_LENGTH = 5;
    static final int FIND_LENGTH = 4;
    static final int TODO_LENGTH = 4;
    static final int WHEN = 3;
    private final ArrayList<Task> tasks;
    private int counter = 0;

    /**
     * Creates a TaskList object that stores a list of tasks in an ArrayList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns an ArrayList of tasks.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Prints the user's existing list of tasks.
     *
     * @return List of tasks in String form.
     */
    public String list() {

        if (counter == EMPTY) {
            return "You have no tasks in your list!";
        }

        String result = "Here are the tasks in your list: \n";


        assert counter > 0 : "Counter should be larger than 0";
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            result += String.format("%d. %s \n", i + 1, currTask.toString());
        }
        return result;
    }

    /**
     * Marks the selected task as completed.
     *
     * @param userInput Index of task according to the list displayed.
     * @return String output of completed task.
     */
    public String markDone(String userInput) throws DukeException {
        if (userInput.substring(DONE_LENGTH).length() == EMPTY) {
            String errorMsg = "Sorry, which task do you wish to mark as completed? Type 'done <TASK NO.>'";
            throw new DukeException(errorMsg);

        } else {
            int index = Integer.parseInt(userInput.substring(DONE_LENGTH + 1));
            Task currTask = tasks.get(index - 1);
            currTask.completeTask();

            String result = "Nice! I've marked this task as done: \n";
            result += currTask.toString();
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
    public String delete(String userInput) throws DukeException {
        if (userInput.substring(DELETE_LENGTH).length() == EMPTY) {
            String errorMsg = "Sorry, which task do you wish to delete? Type 'delete <TASK NO.>'";
            throw new DukeException(errorMsg);

        } else {
            int index = Integer.parseInt(userInput.substring(DELETE_LENGTH + 1));
            Task currTask = tasks.get(index - 1);
            tasks.remove(currTask);
            String result = "Noted. I've removed this task: \n";
            result += currTask.toString();

            counter -= 1;
            result += String.format("\nYou now have %d task(s) in your list!", counter);
            return result;
        }
    }

    /**
     * Creates a ToDo task and adds it to the user's list.
     *
     * @param userInput Description of task.
     * @return String output of ToDo task.
     */
    public String makeTodo(String userInput) throws DukeException {
        if (userInput.substring(TODO_LENGTH).length() == EMPTY) {
            String errorMsg = "YIKES!! The description of a todo cannot be empty! Type 'todo <description>'";
            throw new DukeException(errorMsg);
        } else {
            return makeTask(new ToDo(userInput.substring(TODO_LENGTH + 1)));
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
    public String makeEvent(String userInput) throws DukeException {
        if (userInput.substring(EVENT_LENGTH).length() == EMPTY) {
            String errorMsg = "YIKES!! The description of an Event cannot be empty! "
                + "Type 'event <description> /at <date/time>'";
            throw new DukeException(errorMsg);
        } else {
            String output = userInput.substring(EVENT_LENGTH + 1);
            String[] info = output.split("/");
            return makeTask(new Event(info[0], info[1].substring(WHEN)));
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
        tasks.add(new Event(info[0], info[1].substring(WHEN)));
        counter += 1;
    }

    /**
     * Creates a Deadline task and adds it to the user's list.
     *
     * @param userInput Details of the Deadline task.
     * @return String output of Deadline task.
     */
    public String makeDeadline(String userInput) throws DukeException {
        if (userInput.substring(DEADLINE_LENGTH).length() == EMPTY) {
            String errorMsg = "YIKES!! The description of a Deadline cannot be empty! "
                    + "Type 'deadline <description> /by <YYYY-MM-DD>'";
            throw new DukeException(errorMsg);
        } else {
            String output = userInput.substring(DEADLINE_LENGTH + 1);
            String[] info = output.split("/");
            return makeTask(new Deadline(info[0], info[1].substring(WHEN)));
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
        tasks.add(new Deadline(info[0], info[1].substring(WHEN)));
        counter += 1;
    }

    /**
     * Finds tasks with keywords that match user's input.
     * Prints these tasks in a list for user's perusal.
     *
     * @param userInput Keyword provided by user.
     * @return List of tasks that fit user's criteria.
     */
    public String find(String userInput) throws DukeException {
        if (userInput.substring(FIND_LENGTH).length() == EMPTY) {
            String errorMsg = "Uh Oh!! Please specify the keyword of a task! Type 'find <keyword>'";
            throw new DukeException(errorMsg);

        } else {
            String keyword = userInput.substring(FIND_LENGTH + 1);
            ArrayList<Task> matchedTasks = new ArrayList<>();

            for (Task task : tasks) {
                if (task.getDescription().contains(keyword)) {
                    matchedTasks.add(task);
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
     * Auxiliary method that adds specified task to TaskList and returns success message.
     *
     * @param task Task input provided by method.
     * @return Success message upon addition of Task.
     */
    private String makeTask(Task task) {
        tasks.add(task);
        String result = "Got it. I've added this task: \n";
        result += tasks.get(counter).toString();
        counter += 1;

        result += String.format("\nYou now have %d task(s) in your list.", counter);
        return result;
    }

    /**
     * Returns instructions and sample commands for user to follow.
     *
     * @return Guide for users to adhere to when using Duke.
     */
    public String getHelp() {
        String result = "Here are the commands you can give Duke:\n";
        result += "Create tasks: todo, deadline, event\n";
        result += "Manage tasks: list, delete, done, find\n";
        result += "Exit: bye";
        return result;
    }

    /**
     * Alerts user to an invalid command.
     */
    public String displayError() throws DukeException {
        throw new DukeException("OOPS!! I don't know how to respond to this command!"
                + "Type 'help' to view list of commands available.");
    }

}
