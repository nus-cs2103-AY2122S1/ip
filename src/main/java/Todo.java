class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String fileFormat() {
        return String.format("T%s", super.fileFormat());
    }

}
