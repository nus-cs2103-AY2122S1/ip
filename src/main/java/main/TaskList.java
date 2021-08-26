package main;
import task.Task;

import java.util.ArrayList;

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

<<<<<<< HEAD
    public void output_list(String next_line) {
=======
    /**
     * output the current task list when user calls "list"
     */
    public void outputList() {
>>>>>>> 7f3ded1 (Follow Coding Standard)
        int count = 1;
        System.out.println("Do these soon:" + "\n");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(count
                    + ". ["
                        + this.list.get(i).getType()
                            + "]["
                                + doneCheck.get(i)
                                    + "]"
                                        + list.get(i).getTask());
            count = count + 1;
        }
    }

    public void markTaskAsDone(String next_line) {
        System.out.println("Yay! you have finished this task!");
        taskNumber = Integer.valueOf(next_line.substring(5)) - 1;
        this.doneCheck.set(taskNumber, "X");
        System.out.println("["
                + this.list.get(taskNumber).getType()
                    + "][" + doneCheck.get(taskNumber)
                        + "]"
                            + list.get(taskNumber).getTask());
    }

    public void deleteTask(String next_line) {
        taskNumber = Integer.valueOf(next_line.substring(7));
        System.out.println("Congrats! You have completed this task!");
        System.out.println("["
                + this.list.get(taskNumber - 1).getType()
                    + "][] "
                        + list.get(taskNumber - 1).getTask());
        this.doneCheck.remove(taskNumber - 1);
        this.list.remove(taskNumber - 1);
        System.out.println(this.list.size()
                + " more to go!! Press on!!");
    }

    public void addTodo(String next_line, Task todo) {
        this.list.add(todo);
        doneCheck.add(" ");
        System.out.println("Added the task! :)");
        System.out.println("["
                + todo.getType()
                    + "][ ]"
                        + todo.getTask());
        System.out.println("Jiayou! you have "
                + list.size()
                    + " tasks in the list.");
    }

    public void addDeadline(String next_line, Task deadline) {
        this.list.add(deadline);
        doneCheck.add(" ");
        System.out.println("Added the task! :)");
        System.out.println("["
                + deadline.getType()
                    + "][ ]"
                        + deadline.getTask());
        System.out.println("Jiayou! you have "
                + this.list.size()
                    + " tasks in the list.");
    }

    public void addEvent(String next_line, Task event) {
        this.list.add(event);
        doneCheck.add(" ");
        System.out.println("Added the task! :)");
        System.out.println("["
                + event.getType()
                    + "][ ]"
                        + event.getTask());
        System.out.println("Jiayou! you have "
                + this.list.size()
                    + " tasks in the list.");
    }



}
