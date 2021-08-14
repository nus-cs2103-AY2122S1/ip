public class DukeTask {
    final String name;
    boolean isDone = false;

    public DukeTask(String name) {
        this.name = name;
    }

   public void markAsDone() {
        isDone = true;
   }

   public String getName() {
        return name;
   }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", name);
    }
}

