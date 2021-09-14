package duke.general;

import java.util.ArrayList;
import java.util.Stack;

import duke.command.ErrorCommand;
import duke.command.Revertible;
import duke.error.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Contains the task list, and has operations to add/delete from the list
 */
public class Tasklist {
    private ArrayList<Task> list;
    private Stack<Revertible> history = new Stack<>();

    /**
     * Constructs the Tasklist based from a list provided
     * @param list list of tasks
     */
    public Tasklist(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds task based on the Type given and input to the tasklist, returns the task formed
     * @param t Type of task
     * @param inputSplit Additional info such as description, completion status etc.
     * @return duke.task.Task that was added to the duke.general.Tasklist
     */
    public Task addTask(TaskType t, String[] inputSplit) throws DukeException {
        Task temp;
        if (inputSplit.length < 2) {
            throw new DukeException("Description of task cannot be empty!");
        }
        String input = inputSplit[1];
        switch (t) {
        case TODO:
            temp = new ToDo(input);
            break;
        case DEADLINE:
            if (input.split(" /by ", 2).length < 2) {
                throw new DukeException("Deadline not specified!");
            }
            temp = new Deadline(input.split(" /by ", 2)[0], input.split(" /by ", 2)[1]);
            break;
        case EVENT:
            if (input.split(" /at ", 2).length < 2) {
                throw new DukeException("Date of event not specified!");
            }
            temp = new Event(input.split(" /at ", 2)[0], input.split(" /at ", 2)[1]);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + t);
        }
        list.add(temp);
        return temp;
    }



    /**
     * Marks a task as done from the Tasklist based on input given
     * @param inputSplit input given by user
     * @return Task that is mark as done, but null if Exception occurs
     * @throws DukeException occurs when input is invalid
     */
    public Task doneTask(String[] inputSplit) throws DukeException {
        Task task = null;
        if (inputSplit.length < 2) {
            throw new DukeException("Please enter the number of task to mark as completed!");
        }
        try {
            int index = Integer.parseInt(inputSplit[1]);
            if (index > list.size() || index <= 0) {
                throw new DukeException("That number is not in the list!");
            }
            task = list.get(index - 1);
            task.toggleCompleted();
        } catch (NumberFormatException e) {
            System.out.println("Please input a proper number pls");
        }
        return task;
    }

    /**
     * Deletes a task from the Tasklist based on input given
     * @param inputSplit input given by user
     * @return Task that is being deleted
     */
    public Task deleteTask(String[] inputSplit) throws DukeException {
        Task t;
        try {
            int i = Integer.parseInt(inputSplit[1]);
            t = list.get(i - 1);
            list.remove(i - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That is an invalid index!!");
            throw new DukeException("That is an invalid index!!");
        }
        return t;
    }

    /**
     * Finds tasks that are related to the keyword and returns them in a ArrayList
     * @param keyword Keyword that user is searching for
     * @return ArrayList of tasks that are related to the keyword
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task t : this.list) {
            if (t.getName().contains(keyword)) {
                foundTasks.add(t);
            }
        }
        return foundTasks;
    }

    /**
     * Creates new deep copy of the list
     * @return Deep copy of the list
     */
    public ArrayList<Task> copyList() {
        ArrayList<Task> copy = new ArrayList<>();
        for (Task t : list) {
            copy.add(t.duplicate());
        }
        return copy;
    }


    /**
     * Replaces the list in the Tasklist with a given one
     * @param lst new list to replace the existing one
     */
    public void replaceList(ArrayList<Task> lst) {
        this.list = lst;
    }

    /**
     * Adds a revertible command that has been executed to the history stack
     * @param c Revertible command
     */
    public void addHistory(Revertible c) {
        history.add(c);
    }

    /**
     * Pops off the most recent command off the history stack.
     * If history stack is empty, returns a ErrorCommand
     * @return Revertible command found at top of history stack, if empty returns ErrorCommand
     */
    public Revertible popHistory() {
        if (history.isEmpty()) {
            return new ErrorCommand();
        } else {
            return history.pop();
        }
    }

    public int size() {
        return list.size();
    }

    public Task get(int i) {
        return list.get(i);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }
}
