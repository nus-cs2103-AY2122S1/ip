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

    @Override
    Event markAsCompleted() {
        return new Event(this);
    }

    @Override
    public String toString() {
        return "E: " + super.toString() + " [" + this.dateTime + "]";
    }
}
