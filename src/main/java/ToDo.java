class ToDo extends Task {
    ToDo(String toDo){
        super(toDo);
    }

    @Override
    public String toString() {
        return("[T]" + super.toString());
    }
}