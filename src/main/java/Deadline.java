class Deadline extends Task {
    private String date;

    Deadline(String toDo, String date){
        super(toDo);
        this.date = date;
    }

    String getType() {
        return "D";
    }
    
    String getDate() {
        return date;
    }

    String getToWrite() {
        return this.getType() + " | " + super.getToWrite() + " | " + date;
    }

    @Override
    public String toString() {
        return("[D]" + super.toString() + "(by: " + date + ")");
    }
}