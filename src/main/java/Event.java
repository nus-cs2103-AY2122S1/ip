public class Event extends Task{
    private String at;
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    public void printTask() {
        System.out.print("[E][");

        if(isDone) {
            System.out.print("✗");
        } else {
            System.out.print(" ");
        }

        System.out.println("] " + description + "(at: " + at + ")");
    }

}
