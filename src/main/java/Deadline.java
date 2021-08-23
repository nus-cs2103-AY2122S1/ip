import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task{
    String by;
    LocalDate byDate;

    public static Deadline fromCmd(String[] cmd_args) throws DukeException{
        if (cmd_args.length != 2 ){
            throw new DukeException ("Usage: deadline <task name> /by <deadline> ");
        }
        String[] name_by = cmd_args[1].split("/by",2);
        if (name_by.length != 2){
            throw new DukeException ("Usage: deadline <task name> /by <deadline> ");
        }
        return new Deadline(name_by[0],name_by[1]);
    }

    /**
     * default constructor for a new task
     *
     * @param name task name
     * @param by   deadline for deadline task
     */
    Deadline(String name,String by){
        this(name, false, by);
    }

    /**
     * default constructor for a new task
     * @param name task name
     * @param done boolean state of task done
     * @param by   deadline for deadline task
     */
    Deadline(String name, boolean done, String by) {
        super(name);
        this.by = by;
        try{
            this.byDate = LocalDate.parse(by.strip());
        } catch (DateTimeParseException e){
            this.byDate = null;
        }
        System.out.println(this.byDate);
    }


    public String serialize() {
        return "Task:deadline\n" +
                String.format("\tName:%s\n", this.name) +
                String.format("\tDone:%s\n", this.done) +
                String.format("\tBy:%s\n", this.by);
    }

    @Override
    public String toString() {
        String out =  "[D]" + (this.done? "[X] " : "[ ] ")
                + this.name;
        if (this.byDate == null){
            out += String.format("(by: %s)",this.by);
        } else{
            out += String.format(" by: %d days left",LocalDate.now().until(this.byDate,ChronoUnit.DAYS));
        }
        return out;
    }
}
