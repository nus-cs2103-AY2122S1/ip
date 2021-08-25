package duke.task;

public class ToDo extends Task {

    public ToDo(String toDo){
        super(toDo);
    }

    String getType() {
        return "T";
    }

    public String getToWrite() {
        return this.getType() + " | " + super.getToWrite();
    }

    @Override
    public String toString() {
        return("[T]" + super.toString());
    }
}