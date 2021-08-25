package duke;

/**
 * Tasks without any date/time attached to it e.g., visit new theme park
 */
public class Todo extends Task{
    public Todo(String name){
        super(name);
    }

    public String toString(){
        return "[T]" + super.toString();
    }
}
