package duke;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;
    private int numOfTask;

    TaskList() {
        this.taskList = new ArrayList<Task>();
        this.numOfTask = 0;
    }

    TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.numOfTask = taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }


    public void markAsDone(String taskNumber) {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            checkCanDeleteOrMarkAsDone(taskNumber, index);
            this.taskList.get(index).markAsDone();
            System.out.println();
        } catch (NumberFormatException ex) {
            System.out.println("Task must be an integer!");
            System.out.println();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }
    }

    public void addTask(Task task) {
        this.taskList.add(task);
        System.out.println("Got it! I have added this task:");
        System.out.println(this.taskList.get(this.numOfTask));
        this.numOfTask = this.numOfTask + 1;
        if (this.numOfTask > 1) {
            System.out.printf("Now you have %s tasks in your list.\n", this.numOfTask);
        } else {
            System.out.printf("Now you have 1 task in your list.\n");
        }
        System.out.println();
    }

    public void deleteTask(String taskNumber) {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            checkCanDeleteOrMarkAsDone(taskNumber, index);
            System.out.println("Noted. I've removed this task:");
            System.out.println(this.taskList.get(index));
            this.taskList.remove(index);
            this.numOfTask = this.numOfTask - 1;
            if (this.numOfTask > 1) {
                System.out.printf("You have %s tasks left on your list.\n", this.numOfTask);
            } else {
                System.out.printf("You have %s task left on your list.\n", this.numOfTask);
            }
            System.out.println();
        } catch (NumberFormatException ex) {
            System.out.println("Task must be an integer!");
            System.out.println();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }
    }

    private void checkCanDeleteOrMarkAsDone(String taskNumber, int index) throws DukeException {
        if (index > this.numOfTask - 1) {
            if (this.numOfTask > 1) {
                throw new DukeException(String.format("Cannot find task %s." +
                        "There are only %s tasks in your list.", taskNumber, this.numOfTask));
            } else if (this.numOfTask == 1) {
                throw new DukeException(String.format("Cannot find task %s." +
                        "There is only %s task in your list.", taskNumber, this.numOfTask));
            } else {
                throw new DukeException(String.format("Cannot find task %s." +
                        "There is no task in your list.", taskNumber));
            }
        } else if (index < 0) {
            throw new DukeException(String.format("There is no task %s.", taskNumber));
        }
    }

}
