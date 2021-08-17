public class Todo extends Task{

    public static Todo fromCmd(String[] cmd_args) throws DukeException{
        if (cmd_args.length != 2 ){
            throw new DukeException ("todo takes exactly 1 argument");
        }
        return new Todo(cmd_args[1]);
    }

    Todo(String name){
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + (this.done? "[X] " : "[ ] ")
                + this.name;
    }
}
