public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public void printTask() {
        System.out.print("[D][");

        if(isDone) {
            System.out.print("✗");
        } else {
            System.out.print(" ");
        }

        System.out.println("] " + description + "(by: " + by + ") ");
    }
}
