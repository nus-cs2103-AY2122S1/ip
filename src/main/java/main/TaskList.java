package main;
import task.Task;

import java.util.ArrayList;

/**
 * Handle all list related operations: Add and Delete
 * Handle list and done_check
 */
public class TaskList {
    ArrayList<Task> list;
    ArrayList<String> doneCheck;
    private int taskNumber;

    public TaskList() {
        this.list = new ArrayList<Task>();
        this.doneCheck = new ArrayList<String>();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public ArrayList<String> getDoneCheck() {
        return this.doneCheck;
    }


    /**
     * output the current task list when user calls "list"
     */
    public String outputList() {
        int count = 1;
        String returned = null;
        returned = "Do these soon:" + "\n";
        for (int i = 0; i < this.list.size(); i++) {
            returned = returned + count
                    + ". ["
                        + this.list.get(i).getType()
                            + "]["
                                + doneCheck.get(i)
                                    + "]"
                                        + list.get(i).getTask() + "\n";
            count = count + 1;
        }
        return returned;
    }

    public String markTaskAsDone(String next_line) {
        String returned = "";
        returned = returned + "Yay! you have finished this task!" + "\n";
        taskNumber = Integer.valueOf(next_line.substring(5)) - 1;
        this.doneCheck.set(taskNumber, "X");
        returned = returned + "["
                + this.list.get(taskNumber).getType()
                    + "][" + doneCheck.get(taskNumber)
                        + "]"
                            + list.get(taskNumber).getTask();
        return returned;
    }

    public String deleteTask(String next_line) {
        String returned = "";
        taskNumber = Integer.valueOf(next_line.substring(7));
        returned = returned + "Congrats! You have completed this task!" + "\n";
        returned = returned + "["
                + this.list.get(taskNumber - 1).getType()
                    + "][] "
                        + list.get(taskNumber - 1).getTask() + "\n";
        this.doneCheck.remove(taskNumber - 1);
        this.list.remove(taskNumber - 1);
        returned = returned + this.list.size()
                + " more to go!! Press on!!";
        return returned;
    }

    public String addTodo(String next_line, Task todo) {
        String returned = "";
        this.list.add(todo);
        doneCheck.add(" ");
        returned = returned + "Added the task! :)" + "\n";
        returned = returned + "["
                + todo.getType()
                    + "][ ]"
                        + todo.getTask() + "\n";
        returned = returned + "Jiayou! you have "
                + list.size()
                    + " tasks in the list." + "\n";
        return returned;
    }

    public String addDeadline(String next_line, Task deadline) {
        this.list.add(deadline);
        doneCheck.add(" ");
        String returned = "Added the task! :)" + "\n";
        returned = returned + "["
                + deadline.getType()
                    + "][ ]"
                        + deadline.getTask() + "\n";
        returned = returned + "Jiayou! you have "
                + this.list.size()
                    + " tasks in the list.";
        return returned;
    }

    public String addEvent(String next_line, Task event) {
        this.list.add(event);
        doneCheck.add(" ");
        String returned = "Added the task! :)" + "\n";
        returned = returned + "["
                + event.getType()
                    + "][ ]"
                        + event.getTask() + "\n";
        returned = returned + "Jiayou! you have "
                + this.list.size()
                    + " tasks in the list.";
        return returned;
    }



}
