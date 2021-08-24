public class ToDos extends Task {
    private String type;

    ToDos(String title) {
        super(title);
        this.type = "T";
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString();
    }

    @Override
    String writeTask() {
        return type + " | " + super.writeTask();
    }
}
