// class that handles ToDo tasks 
// -> ToDo is a type of Task with no date/time
public class ToDo extends Task {
    
    // Constructor for a ToDo
    public ToDo(String description) {
        super(description);
    }

    // String representation of a ToDo
    @Override
    public String toString() {
        if (this.isCompleted()) {
            return ("[T][X] " + this.getDescription());
        } else {
            return ("[T][ ] " + this.getDescription());
        }
    }
}
