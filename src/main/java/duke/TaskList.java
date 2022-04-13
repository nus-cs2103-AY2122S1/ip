package duke;

import duke.Deadline;
import duke.Event;
import duke.Task;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.lang.StringBuilder;

/**
 * Contains a task list and manages the tasks in it.
 */
public class TaskList {
    private ArrayList<Task> list;
    private int numTask;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<String> tasks) {
        this.list = new ArrayList<>();
        for (String s: tasks) {
            numTask++;
            String[] array = s.split("\\|");

            switch (array[0]) {
            case "T": {
                this.loadTask(new Todo(array[2], Boolean.parseBoolean(array[1])));
                break;
            }
            case "D": {
                this.loadTask(new Deadline(array[2], LocalDateTime.parse(array[3]), Boolean.parseBoolean(array[1])));
                break;
            }
            case "E": {
                this.loadTask(new Event(array[2], LocalDateTime.parse(array[3]), Boolean.parseBoolean(array[1])));
                break;
            }

            case "R": {
                this.loadTask(new RecurTask(array[2], LocalDate.parse(array[3]), Boolean.parseBoolean(array[1])));
            }
            }
        }

    }

    /**
     * Finds all the task with keyword
     */

    public String find(String keyword) {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list: \n");
        for(int i = 0; i < list.size(); i++) {
            int index = i + 1;
            if (list.get(i).getDescription().contains(keyword)) {
                sb.append( index + ". " + list.get(i) + "\n");
            }
        }
        if (sb.length() == 0) {
            return "There are no matching tasks.";
        }
        return (sb.toString());
    }

    /**
     * Adds a task at the end of the task list.
     * Prints out a response to indicate task was successfully added.
     *
     * @param t task to be added
     */
    public String addTask(Task t) {
        list.add(t);
        numTask++;
        return ("I have added this task: \n" + t);
    }

    /**
     * Adds a task at the end of the task list.
     * Used to load tasks when Duke is initialised.
     *
     * @param t task to be added
     */
    public void loadTask(Task t) {
        list.add(t);
        numTask++;
    }

    /**
     * Deletes task at the specified index in the arraylist.
     *
     * @param i index of the deleted task
     */
    public String deleteTask(int i) {
        numTask--;
        return ("Noted. I have removed this task: \n" + list.remove(i));
    }

    /**
     * Prints out all the tasks in the arraylist
     */
    public String printList() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list: \n");

        for(int i = 0; i < list.size(); i++) {
            int index = i + 1;
            sb.append( index + ". " + list.get(i) + "\n");
        }
        return (sb.toString());
    }

    /**
     * Set the task at the specified index
     * in the arraylist as completed.
     *
     * @param i index of the completed task
     */
    public String setDone(int i) {
        this.list.get(i).setDone();
        return ("I've marked this task as done: \n" + list.get(i));
    }

    /**
     * Returns the ArrayList stored.
     *
     * @return arraylist
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Returns the number of tasks in the arraylist.
     */
    public int getNumTask() {
        return numTask;
    }
}
