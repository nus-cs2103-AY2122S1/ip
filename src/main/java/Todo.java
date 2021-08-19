/**
 * class to represent todos, a type of task
 *
 */
public class Todo extends Task {

  /**
   * Constructor for Todo
   *
   * @param title title of the todo task
   *
   */
  Todo(String title) { super(title); }

  /**
   * Returns a string representation of the todo task
   *
   * @return string representing todo task
   */
  @Override
  public String toString() {
    if (this.done) {
      return "[T][X] " + this.title;
    } else {
      return "[T][ ] " + this.title;
    }
  }
}
