package duke;
public class Todo extends Task{

    /**
     * Constructor for a todo Class
     * @param description for the activity
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * toString method which returns the string representation of a todo
     * @return string representation of a todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * toStringConvert method which returns the string representation of a todo, to be printed in a file
     * @return description of the todo in a specific format, to be printed in a file
     */
    @Override
    public String toStringConvert(){
        if(this.isCompleted()) {
            return "T | 1 | " +  this.getString() ;
        } else {
            return "T | 0 | " +  this.getString() ;
        }
    }
}
