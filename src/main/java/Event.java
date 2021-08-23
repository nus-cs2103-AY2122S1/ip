class Event extends Task {
    protected String at;
    Event(String content, String at) {
        super(content);
        this.at = at;
    }

    @Override
    public String encoding() {
        return "E|" + super.encoding() + "|" + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}