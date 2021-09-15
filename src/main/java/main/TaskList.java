package main;

import java.util.ArrayList;

import task.Task;


/**
 * Handle all list related operations: Add and Delete.
 * Handle list and done_check.
 */
public class TaskList {
    private ArrayList<Task> list;
    private ArrayList<String> doneCheck;
    private int taskNumber;

    /**
     * constructor for creating a Tasklist.
     * while initialising the doneCheck arrayList and list arrayList.
     */
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
     * output the current task list when user calls "list".
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
      
        assert returned.equals(null) : "the list is not outputted correctly";
      
        return returned;
    }

    /**
     * Marks task as done in the Task List.
     *
     * @param next_line String command line inputed by user.
     * @return the task success text to return to the user.
     */
    public String markTaskAsDone(String next_line) {
        taskNumber = Integer.valueOf(next_line.substring(5)) - 1;
        assert taskNumber > 0 : "the task number cannot be negative";
        this.doneCheck.set(taskNumber, "X");

        String returned = "";
        returned = returned + "Yay! you have finished this task!" + "\n";
        returned = returned + "["
                + this.list.get(taskNumber).getType()
                    + "][" + doneCheck.get(taskNumber)
                        + "]"
                            + list.get(taskNumber).getTask();
        return returned;
    }

    /**
     * Delete task from the Task List.
     *
     * @param next_line String command line inputed by user.
     * @return the task success text to return to the user.
     */
    public String deleteTask(String next_line) {
        taskNumber = Integer.valueOf(next_line.substring(7));
        assert taskNumber > 0 : "the task number cannot be negative";

        String returned = "";
        returned = returned + "Congrats! You have completed this task!" + "\n";
        returned = returned + "["
                + this.list.get(taskNumber - 1).getType()
                    + "][] "
                        + list.get(taskNumber - 1).getTask() + "\n";
        returned = returned + this.list.size()
                + " more to go!! Press on!!";
        this.doneCheck.remove(taskNumber - 1);
        this.list.remove(taskNumber - 1);
        return returned;
    }

    /**
     * Add task type todo to the Task List.
     *
     * @param todo Task inputted by the user.
     * @return the task success text to return to the user.
     */
    public String addTodo(Task todo) {
        this.list.add(todo);
        doneCheck.add(" ");

        String returned = "";
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

    /**
     * Add task type deadline to the Task List.
     *
     * @param deadline Task inputted by the user.
     * @return the task success text to return to the user.
     */
    public String addDeadline(Task deadline) {
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

    /**
     * Add task type deadline to the Task List.
     *
     * @param event Task inputted by the user.
     * @return the task success text to return to the user.
     */
    public String addEvent(Task event) {
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
