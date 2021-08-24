public class ToDo extends Task{

    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getRecordString() {
        int done = this.isDone ? 1 : 0;
        String record = String.format("T | %d | %s", done, this.name);
        return record;
    }
}
