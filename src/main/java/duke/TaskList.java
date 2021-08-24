package duke;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Task get(int i) {
        return this.taskList.get(i);
    }

    public int size() {
        return this.taskList.size();
    }

    public void readData(String s) {
        String[] args = s.split(",", 4);
        switch (args[0]) {
            case "T":
                this.add("todo", args[2], "");
                break;
            case "D":
                this.add("deadline", args[2], args[3]);
                break;
            case "E":
                this.add("event", args[2], args[3]);
                break;
            default:
                System.out.println("Shouldn't reach here");
                break;
        }
        if (args[1].equals("1")) {
            this.get(this.size() - 1).setCompleted();
        }
    }

    public void markDone(int index) {
        this.taskList.get(index).setCompleted();
    }

    public void add(String taskType, String description, String op) {
        if (taskType.equals("todo")) {
            this.taskList.add(new ToDo(description));
        } else if (taskType.equals("deadline")) {
            String[] s = op.split(" ", 2);
            LocalDate date = LocalDate.parse(s[0]);
            this.taskList.add(new Deadline(description, date, s[1]));
        } else { // is an duke.Event
            this.taskList.add(new Event(description, op));
        }
    }

    public void add(Task t) {
        this.taskList.add(t);
    }

    public void delete(int index) {
        this.taskList.remove(index);
    }
}
