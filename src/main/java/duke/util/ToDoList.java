package duke.util;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


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
     *
     * @return A String representation of the TodoList.
     */
    public String displayList() {
        Integer number = 1;
        String response = "Here are the Tasks on your ToDoList: \n";
        for (Task a : this.record) {
            if (a.isCompleted()) {
                response += number.toString() + "." + a.logo() + "[X] " + a.toString() + "\n";
            } else {
                response += number.toString() + "." + a.logo() + "[ ] " + a.toString() + "\n";
            }
            number++;
        }
        return response;
    }

    /**
     * Marks the item at index num on the ToDoList as done.
     *
     * @param num The index of the item that is to be marked as done.
     * @return A success message to indicate the Task has been marked as done.
     */
    public String markAsDone(int num) {
        this.record.get(num - 1).setCompleted();
        String response = "Good! Good! Took you long enough to complete this: \n";
        response += "  " + "[X] " + record.get(num - 1).toString() + "\n";
        return response;
    }

    /**
     * Adds a ToDo Task to the ToDoList.
     *
     * @param item Name of ToDo.
     * @return A success message indicating that a ToDo has been added to the ToDoList.
     */
    public String addToDo(String item) {
        ToDo todo = new ToDo(item);
        this.record.add(todo);
        this.totalNumber++;
        String response = "Aye Aye Capt'. I've added this To Do: \n";
        response += "  [T][ ] " + item + "\n";
        response += "Now you have " + this.totalNumber.toString() + " in the list. Wew.....";
        return response;
    }

    /**
     * Adds an Event Task to the ToDoList.
     *
     * @param item Name of Event.
     * @param duration Time between start and end of Event.
     * @return A success message indicating that an Event has been added to the ToDoList.
     */
    public String addEvent(String item, String duration) {
        Event event = new Event(item, duration);
        this.record.add(event);
        this.totalNumber++;
        String response = "Aye Aye Capt'. I've added this Event: \n";
        response += "  [E][ ] " + item + " (at: " + duration + ")\n";
        response += "Now you have " + this.totalNumber.toString() + " in the list";
        return response;
    }

    /**
     * Adds a Deadline Task to the ToDoList.
     *
     * @param item Name of Task.
     * @param deadline Date and Time at which the Task is due.
     * @return A success message indicating that a Deadline has been added to the ToDoList.
     */
    public String addDeadline(String item, LocalDateTime deadline) {
        Deadline dl = new Deadline(item, deadline);
        this.record.add(dl);
        this.totalNumber++;
        String response = "Aye Aye Capt'. I've added this Deadline:\n";
        response += "  [D][ ] " + dl.toString() + "\n";
        response += "Now you have " + this.totalNumber.toString() + " in the list";
        return response;
    }

    /**
     * Deletes the Task at index on the ToDoList.
     *
     * @param index Index of the Task to be deleted from ToDoList.
     * @return A success message indicating that the Task-to-be-deleted has been deleted from ToDoList.
     */
    public String delete(int index) {
        Task removed = this.record.remove(index - 1);
        String status = removed.isCompleted() ? "[X]" : "[ ]";
        this.totalNumber--;

        String response = "Got it sir. I've removed this task: \n";
        if (removed instanceof ToDo) {
            response += "  [T]" + status + " " + removed.toString() + "\n";
        } else if (removed instanceof Event) {
            response += "  [E]" + status + " " + removed.toString() + "\n";
        } else {
            response += "  [D]" + status + " " + removed.toString() + "\n";
        }
        response += "Now you have " + this.totalNumber.toString() + " in the list";
        return response;
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

    /**
     * Looks for the Task which has a name that contains the specified string
     *
     * @param target String to look out for in the Task's name
     * @return A String representation of the List of items that matched.
     */
    public String find(String target) {
        ArrayList<Task> matchingResults = new ArrayList<>();
        for (Task t : this.record) {
            if (t.getName().contains(target)) {
                matchingResults.add(t);
            }
        }
        Integer number = 1;
        String response = "";
        if (matchingResults.size() > 0) {
            response += "Here are the matching tasks in your list: \n";
            for (Task t : matchingResults) {
                if (t.isCompleted()) {
                    response += number.toString() + "." + t.logo() + "[X] " + t.toString() + "\n";
                } else {
                    response += number.toString() + "." + t.logo() + "[ ] " + t.toString() + "\n";
                }
                number++;
            }
        } else {
            response += "No luck here.. Sorry buddy.";
        }
        return response;
    }
}
