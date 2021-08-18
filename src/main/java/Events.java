public class Events extends Task {

    private String time;

    public Events(String name,String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        if (isDone) {
            return ("[E] [X] " + name  + " (at: " + time + ")");
        } else {
            return ("[E] [ ] " + name  + " (at: " + time + ")");
        }
    }
}
