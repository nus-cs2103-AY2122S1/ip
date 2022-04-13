package duke;

import java.time.LocalDate;

/**
 * Tasks without any date/time attached to it e.g., visit new theme park
 */
public class Todo extends Task{
    public Todo(String name){
        super(name);
    }

    @Override
    public void setTime(LocalDate time) throws DukeException{
        throw new DukeException("Cannot edit time for 'Todo' task");
    }


    public String toString(){
        return "[T]" + super.toString();
    }
}
