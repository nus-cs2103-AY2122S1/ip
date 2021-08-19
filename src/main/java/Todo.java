public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public void printTask() {
        System.out.print("[T][");
        if(isDone) {
            System.out.print("✗");
        } else {
            System.out.print(" ");
        }
        System.out.println("] " + description);
    }
}
