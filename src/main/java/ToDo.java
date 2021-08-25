public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, boolean isDone){
        super(name, isDone);
    }

    @Override
    public String toStringInStorage() {
        int done = this.isDone ? 1 : 0;
        return String.format("T/%d/%s", done, this.name);
    }
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
