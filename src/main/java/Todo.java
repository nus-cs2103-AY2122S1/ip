public class Todo extends Task {

  public Todo(String description) {
    super(description, Task.Type.TODO);
  }

  @Override
  public String toString() {
    return "[T]" + super.toString();
  }
}
