package tasks;

public abstract class Task {
  
  public boolean done = false;

  /**
   * Mark a task as complete
   */
  public void markDone() {
    this.done = true;
  }

  /**
   * Get completion status of task 
   * 
   * @return true iff task is complete
   */
  public boolean getTaskDone() {
    return this.done;
  }

  @Override
  public String toString() {
    String taskChecked = this.done ? "X" : " ";
    return String.format("[%s][%s] %s", this.getTaskSymbol(), taskChecked, this.getTaskDesc());
  } 

  /**
   * Get a task's descrption details
   * 
   * @return task decsription string 
   */
  abstract String getTaskDesc();

  /**
   * Get a task's symbol
   * 
   * @return task's symbol string
   */
  abstract String getTaskSymbol();
}
