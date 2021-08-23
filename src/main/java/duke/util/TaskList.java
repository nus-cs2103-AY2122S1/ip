package duke.util;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
  private ArrayList<Task> taskList;

  public TaskList() {
    this.taskList = new ArrayList<>();
  }

  public TaskList(ArrayList<Task> taskList) {
    this.taskList = taskList;
  }

  public ArrayList<Task> getTaskList() {
    return this.taskList;
  }

  public int getSize() {
    return this.taskList.size();
  }

  public Task getTask(int index) {
    return this.taskList.get(index);
  }

  public void addTask(Task task) {
    this.taskList.add(task);
  }

  public void deleteTask(int index) {
    this.taskList.remove(index);
  }

  @Override
  public String toString() {
    String taskListString = "";
    for (int i = 0; i < this.taskList.size(); i++) {
      if (i == this.taskList.size() - 1) {
        taskListString = taskListString + "     " + (i + 1) + "." + this.taskList.get(i);
      } else {
        taskListString = taskListString + "     " + (i + 1) + "." + this.taskList.get(i) + "\n";
      }
    }
    return taskListString;
  }
}
