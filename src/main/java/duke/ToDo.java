package duke;

public class ToDo extends Item {

    public ToDo(String[] strings) {
        super(strings);
        String line = String.join(" ", strings);
        this.setName(line.substring(5, line.length()));
    }

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
