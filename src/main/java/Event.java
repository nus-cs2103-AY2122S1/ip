public class Event extends Task{
    String at;

    public static Event fromCmd(String[] cmd_args) throws DukeException{
        if (cmd_args.length != 2 ){
            throw new DukeException ("Usage: event <task name> /at <deadline> ");
        }
        String[] name_at = cmd_args[1].split("/at",2);
        if (name_at.length != 2){
            throw new DukeException ("Usage: event <task name> /at <deadline> ");
        }
        return new Event(name_at[0],name_at[1]);
    }

    Event(String name, String at){
        super(name);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + (this.done? "[X] " : "[ ] ")
                + this.name
                +String.format("(at:%s)",this.at);
    }
}
