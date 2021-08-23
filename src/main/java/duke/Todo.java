package duke;
public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStringConvert(){
        if(this.isCompleted()) {
            return "T | 1 | " +  this.getString() ;
        } else {
            return "T | 0 | " +  this.getString() ;
        }
    }
}
