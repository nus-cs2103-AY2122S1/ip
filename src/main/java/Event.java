public class Event extends Task{
    private String at;
    
    // dummy constructor for Jackson
    public Event(){ 
        super(); 
    }

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    
    // getters & setters (needed for jackson)
    protected void setAt(String at) {
        this.at = at;
    }

    @Override
    public String toString() {
        return "[Event]    " + super.toString() + " (at: " + at + ")";
    }
}
