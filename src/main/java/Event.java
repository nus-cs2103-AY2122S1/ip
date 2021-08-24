class Event extends Task {

    private final String dateTime;

    Event(String eventName, String dateTime) {
        super(eventName);
        this.dateTime = dateTime;
    }

    private Event(Event oldEvent) {
        super(oldEvent);
        this.dateTime = oldEvent.dateTime;
    }
    
    static protected Event createTask(String name, boolean isCompleted, String dateTime) {
        Event e = new Event(name, dateTime);
        if (isCompleted) {
            return new Event(e);
        } else {
            return e;
        }
    }

    @Override
    Event markAsCompleted() {
        return new Event(this);
    }

    @Override
    public String toString() {
        return "E: " + super.toString() + " [" + this.dateTime + "]";
    }
}
