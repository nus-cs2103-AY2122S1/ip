package tasks;

import java.time.LocalDate;
import ui.Ui;

/**
 * This class implements the tasks to be tracked by the
 * duke.Duke assistant
 */
public class Task {
  /**
   * Description of the task
   */
  private final String task;

  /**
   * Status of the task represented by String
   */
  private String isDone;

  /**
   * Constructor for a tasks.Task
   * @param s the input string to describe the task
   */
  public Task(String s) {
    this.task = s;
    this.isDone = "[ ]";
  }

  /**
   * To mark a task as complete by changing
   * the String representation
   */
  public void setIsDone() {
    String result = "";
    if (this.isDone.equals("[ ]")) {
      this.isDone = "[X]";
      result = "Well done! The task is completed!";
    } else {
      result = "You have already completed this task before!";
    }
    Ui.showInput(result, this.getType() + isDone + " " + this.getTask());
  }

  public void markAsDone() {
    this.isDone = "[X]";
  }

  /**
   * To retrieve the status of the task
   * whether it is complete or not
   *
   * @return the string representation of the task's state
   */
  public String getStatus() {
    return this.isDone;
  }

  /**
   * To retrieve the description of the task
   *
   * @return the String that is the description of the task
   */
  public String getTask() {
    return this.task;
  }

  /**
   * To retrieve the information on the type of tasks.Task
   *
   * @return the String description of the type of tasks.Task
   */
  public String getType() {
    return "regular";
  }

  public String getSaveFormat() {
    return "regular";
  }

  public LocalDate getLocalDate() {
    return null;
  }
}
