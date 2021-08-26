package bubbles;

class ToDo extends Task {
    private ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public static ToDo addToDo(String input, boolean isDone) throws EmptyTaskException {
        if (input.equals("")) {
            throw new EmptyTaskException("todo");
        }

        ToDo item = new ToDo(input, isDone);

        return item;
    }

    @Override
    public String format() {
        String format = "T | ";

        if (this.isDone) {
            format += "1 | ";
        } else {
            format += "0 | ";
        }

        format += this.description;

        return format;
    }

    @Override
    public String toString() {
        String res = "[T] [" + this.getStatus() + "] " + this.description;

        return res;
    }
}
