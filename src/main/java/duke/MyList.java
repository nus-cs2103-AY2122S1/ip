package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import duke.tasktype.Deadline;
import duke.tasktype.Task;

/**
 * duke.MyList class that encapsulates the bot list object and functionalities.
 *
 * @author Houten Teo
 * @version CS2103T week 3
 */
public class MyList {

    /**
     * An ArrayList Object to store all the items in the list.
     */
    private ArrayList<Task> myList;
    public enum taskType {
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
     * Method to return the size of the list
     * @return The number of items in the list.
     */
    public int getListSize() {
        return this.myList.size();
    }

    /**
     * Method the return the task at the specified index.
     * @param index The specified index.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.myList.get(index);
    }

    /**
     * Method to add an item into the list
     * Subsequently prints out the total number of items in the list.
     * @param t the duke.tasks.Task to be added into the list
     */
    public String addTask(Task t) {
        myList.add(t);
        return Ui.addTaskMessage(t, this);
    }

    /**
     * Method to list out all the items in the list.
     */
    public String listAll() {
        return Ui.listAllMessage(this);
    }

    /**
     * Method to mark a certain task in the list as completed.
     * @param index The index of the item in the list to be completed.
     *              If the index specified is invalid, prompt the user
     *              for another input.
     */
    public String markComplete(int index) {
        try {
            return myList.get(index - 1).markComplete();
        } catch (IndexOutOfBoundsException e) {
            return Ui.invalidIndexMessage();
        }
    }

    /**
     * Method to delete a certain task from the list.
     * @param index The index of the item in the list to be deleted.
     *              If the index specified is invalid, prompt the user
     *              for another input.
     */
    public String deleteTask(int index) {
        assert (index > 0);
        Task removed = this.myList.remove(index - 1);
        return Ui.deleteTaskMessage(removed, this);
    }

    /**
     * Method to find all tasks with a description containing the keyword.
     * Not case-sensitive.
     * @param keyword The keyword to be looking for.
     */
    public String find(String keyword) {
        int counter = 0;
        Task[] matchingList = new Task[getListSize()];
        for (int i = 0; i < getListSize(); i++) {
            Task t = getTask(i);
            String taskDescription = t.getDescription().toLowerCase();
            if (taskDescription.contains(keyword.toLowerCase())) {
                matchingList[i] = t;
                counter++;
            }
        }
        String s = Ui.containsKeyword(counter);
        s += Ui.containsKeywordTask(matchingList);
        return s;
    }

    /**
     * Method to load a task into the list.
     * This method does not print anything to the screen.
     * Used in conjuntion with the load method in storage.
     * @param t The task to be laaded into the list.
     */
    public void loadTask(Task t) {
        this.myList.add(t);
    }

    /**
     * Method to help generate Duke's response to the remind command.
     * @param noOfDays The number of days specified by the user.
     * @return Duke's response.
     */
    public String findDeadlineWithin(int noOfDays) {
        String result = "";
        LocalDate currentDate = LocalDate.now();
        int noOfDeadlines = 0;

        for (int i = 0; i < getListSize(); i++) {
            Task t = this.myList.get(i);
            if (t.getTaskType().equals(taskType.DEADLINE.toString())) {
                // Safe typecast since it has already been verified that t is of type deadline
                Deadline d = (Deadline) t;
                LocalDate deadline = d.getDeadline();
                long daysBetween = ChronoUnit.DAYS.between(currentDate, deadline);
                if (daysBetween <= noOfDays) {
                    noOfDeadlines++;
                    result += Ui.withinDeadlineMessage(d, daysBetween, i + 1);
                }
            }
        }

        result = Ui.getRemindHeader(noOfDeadlines, noOfDays) + result;
        System.out.println(result);
        return result;
    }
}
