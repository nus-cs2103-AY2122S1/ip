public class Event extends Task {

    private String time;

    public static Event createEvent(String desc) {
        String arr[] = desc.split("/at");
        return new Event(arr[0].trim(), arr[1].trim());
    }

    private Event(String details, String time) {
        super(details);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
