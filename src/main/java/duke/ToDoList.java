package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ToDoList {

    private String name;
    private ArrayList<Task> record = new ArrayList<>();
    private Integer totalNumber = 0;

    public ToDoList(String name) {
        this.name = name;
    }

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

    public void markAsDone(int num) {
        this.record.get(num-1).setCompleted();
        System.out.println("========== " + this.name + " ===========");
        System.out.println("Good! Good! Took you long enough to complete this:");
        System.out.println("  " + "[X] " + record.get(num-1).toString());
        System.out.println("========== " + this.name + " ===========\n");
    }


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

    protected ArrayList<Task> getRecord() {
        return this.record;
    }

    protected Task getTask(int index) {
        return this.record.get(index);
    }

    public void find(String target) {
        ArrayList<Task> temp = new ArrayList<>();
        for (Task t : this.record) {
            if (t.getName().contains(target)) {
                temp.add(t);
            }
        }
        Integer number = 1;
        System.out.println("========== " + this.name + " ===========");
        if (temp.size() > 0) {
            System.out.println("Here are the matching tasks in your list:");
            for (Task t : temp) {
                if (t.isCompleted()) {
                    System.out.println(number.toString() + "." + t.logo() + "[X] " + t.toString());
                } else {
                    System.out.println(number.toString() + "." + t.logo() + "[ ] " + t.toString());
                }
                number++;
            }
        } else {
            System.out.println("No luck here.. Sorry buddy.");
        }
        System.out.println("========== " + this.name + " ===========\n");
    }
}

