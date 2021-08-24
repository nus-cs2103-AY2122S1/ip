package duke;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Gets the i-th task in the TaskList.
     *
     * @param i the index of the task the user would like to get.
     *
     * @return the i-th task in the TaskList
     */
    public Task get(int i) {
        return this.taskList.get(i);
    }

    /**
     * Gets the number of tasks the user has added into the TaskList.
     * @return the number of tasks in the TaskList.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Adds the respective task into the TaskList.
     *
     * @param s the user input string
     */
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

    /**
     * Marks the index-th task in the TaskList as completed.
     *
     * @param index the index of the task to be marked done
     */
    public void markDone(int index) {
        this.taskList.get(index).setCompleted();
    }

    /**
     * Adds the given task into the TaskList.
     *
     * @param taskType the String representing the type of Task
     * @param description the name of the task
     * @param op the date of the Event
     */
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

    /**
     * Adds the given task into the TaskList.
     *
     * @param t The Task to be added
     */
    public void add(Task t) {
        this.taskList.add(t);
    }

    /**
     * Deletes the given index-th task in TaskList.
     *
     * @param index the index of the task in the TaskList to be deleted
     */
    public void delete(int index) {
        this.taskList.remove(index);
    }

    public ArrayList<Integer> findTask(String keyword) {
        ArrayList<Integer> matches = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            String name = taskList.get(i).getName();
            if (name.contains(keyword)) {
                matches.add(i);
            }
        }
        return matches;
    }
}
