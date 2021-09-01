package Task;
import Task.TaskTypes;

import java.util.ArrayList;

//contains the task list e.g., it has operations to add/delete tasks in the list
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();
    /**
     * Getter to obtain the TaskList.
     * @return the TaskList.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * function adds a new Task into the taskList if valid.
     * @param newTask is the newly created Task to be added to the task list.
     */
    public void addTask(Task newTask) {
        taskList.add(newTask);
        System.out.println("Got it. I've added this task: ");
        System.out.println("    " + newTask);
        System.out.format("Now you have %d tasks in the list.\n", taskList.size());
    }

    /**
     * Function adds a new Task into the tempList as a result of find request without
     * breaking the encapsulation. So no need to print message.
     * @param newTask is the newly created Task to be added to the tempList.
     */
    public void addTaskWithoutMessage(Task newTask) {
        taskList.add(newTask);
    }
    /**
     * function removes the task located at the index of the task list.
     *
     * @param newInput contains information for the Task to be deleted.
     * @throws Exception if the index provided is not within valid range.
     */
    public void deleteTask(String[] newInput ) throws Exception{

        if (newInput.length < 2) {
            throw new Exception("invalid Syntax for delete instruction");
        } else {
            int index = Integer.parseInt(newInput[1]);
            if (index > 0 && index <= taskList.size()) {
                System.out.println("Noted. I've removed this task: ");
                System.out.println("    " + taskList.get(index - 1).toString());
                taskList.remove(index - 1);
                System.out.format("Now you have %d tasks in the list.\n", taskList.size());
            } else {
                throw new Exception("index not within valid range");
            }
        }
    }

    /**
     * create a message to show all the tasks and their states.
     */
    public void showList() {
        int count = 0;
        for (Task action : taskList ) {
            count++;
            System.out.format("%d. " + action + "\n", count);
        }
    }
}