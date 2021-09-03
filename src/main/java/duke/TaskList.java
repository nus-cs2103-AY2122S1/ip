package duke;

import duke.Deadline;
import duke.Event;
import duke.Task;

import java.util.ArrayList;
import java.time.LocalDateTime;

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
            }
        }

    }

    /**
     * Adds a task at the end of the task list.
     * Prints out a response to indicate task was successfully added.
     *
     * @param t task to be added
     */
    public void addTask(Task t) {
        list.add(t);
        numTask++;
        System.out.println("I have added this task: \n" + t);
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
    public void deleteTask(int i) {
        numTask--;
        System.out.println("Noted. I have removed this task: \n" + list.remove(i));
    }

    /**
     * Prints out all the tasks in the arraylist
     */
    public void printList() {
        System.out.println("Here are the tasks in your list: \n");
        for(int i = 0; i < list.size(); i++) {
            int index = i + 1;
            System.out.println( index + ". " + list.get(i));
        }
    }

    /**
     * Set the task at the specified index
     * in the arraylist as completed.
     *
     * @param i index of the completed task
     */
    public void setDone(int i) {
        this.list.get(i).setDone();
        System.out.println("I've marked this task as done: \n" + list.get(i));
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
//    private void writeToFile(String text) throws IOException {
//
//        fw.write(text);
//        fw.close();
//    }



}
