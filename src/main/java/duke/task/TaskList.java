package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t \t " + task);
        System.out.println(taskSummary());
    }

    public String taskSummary() {
        int numTasks = this.taskList.size();
        String size = numTasks == 0 ? "no" : String.valueOf(numTasks);
        String maybePlural = numTasks == 1 ? " task " : " tasks ";
        return "\t You have " + size + maybePlural + "in the list.\n";
    }

    public void deleteTask(Task task) {
        this.taskList.remove(task);
        System.out.println("\t Got it. I've removed this task:");
        System.out.println("\t \t " + task);
        System.out.println(taskSummary());
    }

    public List<String> formatStorage() {
        return taskList.stream().map(Task::storageString).collect(Collectors.toList());
    }

    public boolean isEmpty() {
        return this.taskList.size() == 0;
    }

    @Override
    public String toString() {
        String toPrint = "";

        for (int i = 0; i < this.taskList.size(); i++) {
            int index = i + 1;
            toPrint += ("\t " + index + ". " + this.taskList.get(i) + "\n");
        }

        return toPrint;
    }

    public void clearTasks() {
        taskList.clear();
    }

    public Task getTask(int taskNum) {
        if (taskNum > this.taskList.size() || taskNum < 1) {
            return null;
        } else {
            return this.taskList.get(taskNum - 1);
        }
    }
}
