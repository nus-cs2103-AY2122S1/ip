public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    protected static ToDo stringToToDo(String s) {
        return new ToDo(s.substring(12));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String PopulateSaveData() {
        return "T | " + (this.isDone ? 1 : 0) + " | " + this.description;
    }
}
