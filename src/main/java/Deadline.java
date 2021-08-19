class Deadline extends Task {
    private String date;

    Deadline(String toDo, String date){
        super(toDo);
        this.date = date;
    }

    @Override
    public String toString() {
        return("[D]" + super.toString() + "(by: " + date + ")");
    }
}