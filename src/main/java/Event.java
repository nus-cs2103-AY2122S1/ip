public class Event extends Task {
    private String at;

    public Event(String desc, String at) {
        super(desc, "E");
        this.at = at;
    }
    
    public String toData() {
        return super.toData() + "~S~" + at; 
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
