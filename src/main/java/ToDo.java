public class ToDo extends Task {
    public ToDo(String description) throws DukeException1 {
        super(description);
        if(description.equals("todo")) {
            throw new DukeException1();
        }
    }

    public String getInfo() {
        return getDescription();
    }

    public String getType() {
        return "T";
    };

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
