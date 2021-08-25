package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The ToDoList is a class that encapsulates the attributes and behaviours of a list of things to do.
 *
 * @author leezhixuan
 */
public class ToDoList {

    private String name;
    private ArrayList<Task> record = new ArrayList<>();
    private Integer totalNumber = 0;

    /**
     * Creates an instance of ToDoList.
     *
     * @param name This is the name of the chat bot.
     */
    public ToDoList(String name) {
        this.name = name;
    }

    /**
     * Displays the content in ToDoList as a numbered list.
     */
    public void displayList() {
        Integer number = 1;
        System.out.println("========== " + this.name + " ===========");
        for (Task a : this.record) {
            if (a.isCompleted()) {
                System.out.println(number.toString() + "." + a.logo() + "[X] " + a.toString());
            } else {
                System.out.println(number.toString() + "." + a.logo() + "[ ] " + a.toString());
            }
            number++;
        }
        System.out.println("========== " + this.name + " ===========\n");
    }

    /**
     * Marks the item at index num on the ToDoList as done.
     *
     * @param num The index of the item that is to be marked as done.
     */
    public void markAsDone(int num) {
        this.record.get(num-1).setCompleted();
        System.out.println("========== " + this.name + " ===========");
        System.out.println("Good! Good! Took you long enough to complete this:");
        System.out.println("  " + "[X] " + record.get(num-1).toString());
        System.out.println("========== " + this.name + " ===========\n");
    }

    /**
     * Adds a ToDo Task to the ToDoList.
     *
     * @param item Name of ToDo.
     */
    public void addToDo(String item) {
        ToDo todo = new ToDo(item);
        this.record.add(todo);
        this.totalNumber++;
        System.out.println("========== " + this.name + " ===========");
        System.out.println("Aye Aye Capt'. I've added this To Do:");
        System.out.println("  [T][ ] " + item);
        System.out.println("Now you have " + this.totalNumber.toString() + " in the list. Wew.....");
        System.out.println("========== " + this.name + " ===========\n");
    }

    /**
     * Adds an Event Task to the ToDoList.
     *
     * @param item Name of Event.
     * @param duration Time between start and end of Event.
     */
    public void addEvent(String item, String duration) {
        Event event = new Event(item, duration);
        this.record.add(event);
        this.totalNumber++;
        System.out.println("========== " + this.name + " ===========");
        System.out.println("Aye Aye Capt'. I've added this Event:");
        System.out.println("  [E][ ] " + item + " (at: " + duration + ")");
        System.out.println("Now you have " + this.totalNumber.toString() + " in the list");
        System.out.println("========== " + this.name + " ===========\n");
    }

    /**
     * Adds a Deadline Task to the ToDoList.
     *
     * @param item Name of Task.
     * @param deadline Date and Time at which the Task is due.
     */
    public void addDeadline(String item, LocalDateTime deadline) {
        Deadline dl = new Deadline(item, deadline);
        this.record.add(dl);
        this.totalNumber++;
        System.out.println("========== " + this.name + " ===========");
        System.out.println("Aye Aye Capt'. I've added this Deadline:");
        System.out.println("  [D][ ] " + dl.toString());
        System.out.println("Now you have " + this.totalNumber.toString() + " in the list");
        System.out.println("========== " + this.name + " ===========\n");
    }

    /**
     * Deletes the Task at index on the ToDoList.
     *
     * @param index Index of the Task to be deleted from ToDoList.
     */
    public void delete(int index) {
        Task removed = this.record.remove(index-1);
        String status = removed.isCompleted() ? "[X]" : "[ ]";
        this.totalNumber--;
        System.out.println("========== " + this.name + " ===========");
        System.out.println("Got it sir. I've removed this task:");
        if (removed instanceof ToDo) {
            System.out.println("  [T]" + status + " " + removed.toString());
        } else if (removed instanceof Event) {
            System.out.println("  [E]" + status + " " + removed.toString());
        } else {
            System.out.println("  [D]" + status + " " + removed.toString());
        }
        System.out.println("Now you have " + this.totalNumber.toString() + " in the list");
        System.out.println("========== " + this.name + " ===========\n");
    }

    /**
     * Returns the ArrayList of Tasks.
     *
     * @return ArrayList of Tasks.
     */
    protected ArrayList<Task> getRecord() {
        return this.record;
    }

    protected Task getTask(int index) {
        return this.record.get(index);
    }
}

