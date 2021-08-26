package main;
import task.Task;

import java.util.ArrayList;

/**
 * Handle all list related operations: Add and Delete
 * Handle list and done_check
 */
public class TaskList {
    ArrayList<Task> list;
    ArrayList<String> done_check;
    int task_number;

    public TaskList() {
        this.list = new ArrayList<Task>();
        this.done_check = new ArrayList<String>();
    }

    public ArrayList<Task> get_list() {
        return this.list;
    }

    public ArrayList<String> get_done_check() {
        return this.done_check;
    }

    /**
     * output the current task list when user calls "list"
     */
    public void output_list() {
        int count = 1;
        System.out.println("Do these soon:" + "\n");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(count + ". [" + this.list.get(i).get_type() + "][" + done_check.get(i) + "]" + list.get(i).get_task());
            count = count + 1;
        }
    }

    public void mark_task_as_done(String next_line) {
        System.out.println("Yay! you have finished this task!");
        task_number = Integer.valueOf(next_line.substring(5)) - 1;
        this.done_check.set(task_number, "X");
        System.out.println("[" + this.list.get(task_number).get_type() + "][" + done_check.get(task_number) + "]" + list.get(task_number).get_task());
    }

    public void delete_task(String next_line) {
        task_number = Integer.valueOf(next_line.substring(7));
        System.out.println("Congrats! You have completed this task!");
        System.out.println("[" + this.list.get(task_number - 1).get_type() + "][] " + list.get(task_number - 1).get_task());
        this.done_check.remove(task_number - 1);
        this.list.remove(task_number - 1);
        System.out.println(this.list.size() + " more to go!! Press on!!");
    }

    public void Add_todo(String next_line, Task todo) {
        this.list.add(todo);
        done_check.add(" ");
        System.out.println("Added the task! :)");
        System.out.println("[" + todo.get_type() + "][ ]" + todo.get_task());
        System.out.println("Jiayou! you have " + list.size() + " tasks in the list.");
    }

    public void Add_deadline(String next_line, Task deadline) {
        this.list.add(deadline);
        done_check.add(" ");
        System.out.println("Added the task! :)");
        System.out.println("[" + deadline.get_type() + "][ ]" + deadline.get_task());
        System.out.println("Jiayou! you have " + this.list.size() + " tasks in the list.");
    }

    public void Add_event(String next_line, Task event) {
        this.list.add(event);
        done_check.add(" ");
        System.out.println("Added the task! :)");
        System.out.println("[" + event.get_type() + "][ ]" + event.get_task());
        System.out.println("Jiayou! you have " + this.list.size() + " tasks in the list.");
    }



}
