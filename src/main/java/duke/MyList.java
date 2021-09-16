package duke;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import duke.tasktype.Deadline;
import duke.tasktype.Task;

/**
 * Class that represents the list that stores the user's tasks.
 *
 * @author Houten Teo
 * @version CS2103T week 6
 */
public class MyList {

    /**
     * An ArrayList Object to store all the items in the list.
     */
    private ArrayList<Task> myList;
    public enum TaskType {
        EVENT,
        TODO,
        DEADLINE
    }

    /**
     * Constructor for the duke.MyList class.
     * Adds the duke.tasks from the Data.txt file into the list if any.
     */
    public MyList() {
        this.myList = new ArrayList<Task>();
    }

    /**
     * Returns the size of the list
     * @return The number of items in the list.
     */
    public int getListSize() {
        return this.myList.size();
    }

    /**
     * Returns the task at the specified index.
     * @param index The specified index.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.myList.get(index);
    }

    /**
     * Adds an item into the list
     * Subsequently prints out the total number of items in the list.
     * @param t the duke.tasks.Task to be added into the list
     */
    public String addTask(Task t) {
        myList.add(t);
        return Ui.getAddTaskMessage(t, this);
    }

    /**
     * Lists out all the items in the list.
     */
    public String listAll() {
        return Ui.getListAllMessage(this);
    }

    /**
     * Marks a certain task in the list as completed.
     * @param index The index of the item in the list to be completed.
     *              If the index specified is invalid, prompt the user
     *              for another input.
     */
    public String markComplete(int index) {
        try {
            return myList.get(index - 1).markComplete();
        } catch (IndexOutOfBoundsException e) {
            return Ui.getInvalidIndexMessage();
        }
    }

    /**
     * Deletes a certain task from the list.
     * @param index The index of the item in the list to be deleted.
     *              If the index specified is invalid, prompt the user
     *              for another input.
     */
    public String deleteTask(int index) {
        assert (index > 0);
        Task removed = this.myList.remove(index - 1);
        return Ui.getDeleteTaskMessage(removed, this);
    }

    /**
     * Finds all tasks with a description containing the keyword.
     * Not case-sensitive.
     * @param keyword The keyword to be looking for.
     */
    public String find(String keyword) {
        int counter = 0;
        Task[] matchingList = new Task[getListSize()];
        for (int i = 0; i < getListSize(); i++) {
            Task t = getTask(i);
            String taskDescription = t.getDescription().toLowerCase();
            if (taskDescription.contains(keyword.toLowerCase().trim())) {
                matchingList[i] = t;
                counter++;
            }
        }
        String s = Ui.getContainsKeywordMessage(counter);
        s += Ui.getTaskWithKeywordMessage(matchingList);
        return s;
    }

    /**
     * Loads a task into the list.
     * This method does not print anything to the screen.
     * Used in conjuntion with the load method in storage.
     * @param t The task to be laaded into the list.
     */
    public void loadTask(Task t) {
        this.myList.add(t);
    }

    /**
     * Generates Duke's response to the 'remind' command.
     * @param noOfDays The number of days specified by the user.
     * @return Duke's response.
     */
    public String findDeadlineWithin(int noOfDays) {
        String result = "";
        LocalDate currentDate = LocalDate.now();
        int noOfDeadlines = 0;

        for (int i = 0; i < getListSize(); i++) {
            Task t = this.myList.get(i);
            if (t.getTaskType().equals(TaskType.DEADLINE.toString())) {
                // Safe typecast since it has already been verified that t is of type deadline
                Deadline d = (Deadline) t;
                LocalDate deadline = d.getDeadline();
                long daysBetween = ChronoUnit.DAYS.between(currentDate, deadline);
                if (daysBetween <= noOfDays && !d.isDone()) {
                    noOfDeadlines++;
                    result += Ui.getWithinDeadlineMessage(d, daysBetween, i + 1);
                }
            }
        }

        result = Ui.getRemindHeader(noOfDeadlines, noOfDays) + result;
        System.out.println(result);
        return result;
    }

    /**
     * Clears all items in the list.
     */
    public void clearList() {
        this.myList.clear();
    }
}
