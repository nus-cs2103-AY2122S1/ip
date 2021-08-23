public class Todo extends Task{

    public static Todo fromCmd(String[] cmd_args) throws DukeException{
        if (cmd_args.length != 2 ){
            throw new DukeException ("todo takes exactly 1 argument");
        }
        return new Todo(cmd_args[1]);
    }

    /**
     * default constructor for a new task
     * @param name task name
     */
    Todo(String name){
        this(name, false);
    }

    /**
     * default constructor for a new task
     * @param name task name
     * @param done state of task done
     */
    Todo(String name, boolean done) {
        super(name, done);
    }

    public String serialize() {
        return "Task:todo\n" +
                String.format("\tName:%s\n", this.name) +
                String.format("\tDone:%s\n", this.done);
    }

    @Override
    public String toString() {
        return "[T]" + (this.done? "[X] " : "[ ] ")
                + this.name;
    }
}
