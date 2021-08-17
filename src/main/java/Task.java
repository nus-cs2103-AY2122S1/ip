public class Task {

  final String title;

  Task(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return this.title;
  }
}
