public class ToDo extends Task {
    private ToDo(String description) {
        super(description);
    }

    public static ToDo addToDo(String input) {
        ToDo item = new ToDo(input);

        return item;
    }

    @Override
    public String toString() {
        String res = "[T] [" + this.getStatus() + "] " + this.description;

        return res;
    }
}
