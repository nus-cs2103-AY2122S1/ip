/**
 *  This class represents a ToDo
 *  A ToDo: tasks without any date/time attached to it
 * @author Ryan Tian Jun
 */
public class ToDo extends Task{

    public ToDo(String description, TYPE type) {
        super(description, type);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }
}
