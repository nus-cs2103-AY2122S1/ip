class Event extends Task {

    private String date;

    Event(String toDo, String date){
        super(toDo);
        this.date = date;
    }

    String getType() {
        return "E";
    }

    String getDate() {
        return date;
    }

    String getToWrite() {
        return super.getToWrite() + " | " + date;
    }

    @Override
    public String toString() {
        return("[E]" + super.toString() + "(at: " + date + ")");
    }
}