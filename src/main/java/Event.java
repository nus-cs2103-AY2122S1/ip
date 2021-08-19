class Event extends Task {

    private String date;

    Event(String toDo, String date){
        super(toDo);
        this.date = date;
    }

    @Override
    public String toString() {
        return("[E]" + super.toString() + "(at: " + date + ")");
    }
}