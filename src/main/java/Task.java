import java.util.ArrayList;

public class Task {
  protected static ArrayList<Task> tasks = new ArrayList<>();
  protected String description;
  protected boolean status;

  public Task(String description) {
    this.description = description;
    this.status = false;
  }

  public String getStatusIcon() {
    return (status ? "X" : " ");
  }

  public void markAsDone() {
    status = true;
  }
}
