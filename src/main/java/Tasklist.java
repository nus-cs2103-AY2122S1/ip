import java.io.FileWriter;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

/**
 * Class that contains the initialization of a list to store the tasks
 *
 * @author: Wei Yangken
 */
public class Tasklist {
    private ArrayList<Task> taskList;
    private int currCount = 0;

    /**
     * Constructor to create a new taskList to store tasks
     */
    Tasklist(ArrayList<Task> taskList) {
       this.taskList = taskList;
    }

    /**
     * Adds task to list and notifies the user if task has not been added properly
     * @param task Task to be added
     */
    public void add(Task task) {
        try {
            if(task == null) {
                throw new DukeException.TaskNotAddedException("Task has not been added successfully.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println(Ui.breakline);
            return;
        }

        this.taskList.add(task);
        currCount += 1;
        System.out.println("Got it. I've added this task:");
        String addMsg = String.format("%s", task.toString());
        String counterMsg = String.format("Now you have %d tasks in the list.", currCount);
        System.out.println(addMsg);
        System.out.println(counterMsg);
        System.out.println(Ui.breakline);
    }

    /**
     * Lists out the tasks present in tasklist.
     * Checks for the scenario where list is empty.
     * @return
     */
    public void list() {
        try {
            if(this.taskList.size() == 0) {
                throw new DukeException.EmptyListException("List is empty :(");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println(Ui.breakline);
            return;
        }

        System.out.println("Here are the tasks in your list:");
        for(int i=0;i < taskList.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, this.taskList.get(i).toString());
        }
        System.out.println(Ui.breakline);

    }

    /**
     * Deletes the indicated task from tasklist.
     * Accounts for situations where task could not be found.
     * @param idx Ranking of task to be deleted
     */
    public void delete(int idx) {
        Task removedTask;
        try {
            removedTask = this.taskList.remove(idx - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("Item %d cannot be found.", idx);
            System.out.println(Ui.breakline);
            return;
        }

        String removeMsg = String.format("Noted. I've removed this task:\n%s",removedTask.toString());
        System.out.println(removeMsg);
        currCount = currCount - 1;
        System.out.printf("Now you have %d task(s) in the list.\n", currCount);
        System.out.println(Ui.breakline);
    }

    /**
     * Returns the task of a particular index in arraylist
     * @param idx Index in the arraylist
     * @return Task of a particular index
     */
    public Task getTask(int idx) {
        return taskList.get(idx);
    }

    public void setCount(int count) {
        currCount = count;
    }

    public int size() {
        return currCount;
    }

    public Task get(int idx) {
        return taskList.get(idx);
    }
}


