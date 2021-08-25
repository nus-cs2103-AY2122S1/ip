package duke.util;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the list of tasks in the Duke program.
 */
public class TaskList {
  private ArrayList<Task> taskList;

  /**
   * Constructs a TaskList with no task.
   */
  public TaskList() {
    this.taskList = new ArrayList<>();
  }

  /**
   * Constructs a TaskList with given list of tasks.
   *
   * @param taskList List of tasks.
   */
  public TaskList(ArrayList<Task> taskList) {
    this.taskList = taskList;
  }

  /**
   * Returns the list of tasks.
   *
   * @return the list of tasks.
   */
  public ArrayList<Task> getTaskList() {
    return this.taskList;
  }

  /**
   * Returns the number of tasks.
   *
   * @return the number of tasks.
   */
  public int getSize() {
    return this.taskList.size();
  }

  /**
   * Returns the task of the given index.
   *
   * @param index Index of the task to be returned.
   * @return the task of the given index.
   */
  public Task getTask(int index) {
    return this.taskList.get(index);
  }

  /**
   * Adds the given task to the existing list of tasks.
   *
   * @param task Task to be added to the existing list of tasks.
   */
  public void addTask(Task task) {
    this.taskList.add(task);
  }

  /**
   * Deletes the task of the given index from the existing list of tasks.
   *
   * @param index Index of the task to be deleted from the existing list of tasks
   */
  public void deleteTask(int index) {
    this.taskList.remove(index);
  }

  /**
   * Returns the string representation of the list of tasks.
   *
   * @return the string representation of the list of tasks.
   */
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
