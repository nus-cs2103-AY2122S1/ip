public class Event extends Item {
    private String time;

    public Event(String[] strings) {
        String line = String.join(" ", strings);
        int idx = line.indexOf("/at");
        this.setName(line.substring(6, idx));
        this.time = line.substring(idx + 4, line.length());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + time + ")";
    }
}
