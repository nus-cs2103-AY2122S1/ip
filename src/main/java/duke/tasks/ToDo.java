package duke.tasks;
/**
 * 
 * This represents a ToDo task without a time limit.
 * 
 * @author Rishabh Anand
 * @version CS2103, AY21/22 Semester 1
 * 
 */
public class ToDo extends Task {
    public ToDo(String desc) {
        super(desc);
    }
   
    // returns the string representation of the ToDo task in the list
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toTextRepresentation() {
        String binaryStatus = this.isDone ? "1" : "0";
        return String.format("T; %s; %s", binaryStatus, this.description.strip());
    }
}