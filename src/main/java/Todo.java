class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    public Todo(String name, String isDone) {
        super(name, isDone.equals("1"));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveString() {
        return "Todo~" + super.toSaveString();
    }
}