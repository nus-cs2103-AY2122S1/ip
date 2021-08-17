public class Deadline extends Task{
    String by;

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

    Deadline(String name,String by){
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + (this.done? "[X] " : "[ ] ")
                + this.name
                +String.format("(by:%s)",this.by);
    }
}
