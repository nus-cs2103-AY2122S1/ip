public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public static ToDo load(String[] loadData) {
        boolean done = loadData[1].equals("o");
        String name = loadData[2];

        ToDo todo = new ToDo(name);
        if (done) {
            todo.doTask();
        }

        return todo;
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String getSaveString() {
        return "t," + super.getSaveString();
    }
}
