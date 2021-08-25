package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String writeToFile() {
        String s = "D" + " | ";
        if (this.isDone) {
            s += "1";
        } else {
            s += "0";
        }
        s = s + " | " + description + " | ";
        return s;
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
