public class Deadline extends Task{
    private String dateTime;
    public Deadline(String description, String dateTime){
        super(description);
        this.dateTime = dateTime;
    }
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                getStatusIcon(), description, dateTime);
    }
}
