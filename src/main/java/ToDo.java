class ToDo extends Task {
    ToDo(String toDo){
        super(toDo);
    }

    String getType() {
        return "T";
    }

    String getToWrite() {
        return this.getType() + " | " + super.getToWrite();
    }

    @Override
    public String toString() {
        return("[T]" + super.toString());
    }
}