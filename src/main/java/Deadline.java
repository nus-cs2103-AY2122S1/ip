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
    }


    public String serialize() {
        return "Task:deadline\n" +
                String.format("\tName:%s\n", this.name) +
                String.format("\tDone:%s\n", this.done) +
                String.format("\tBy:%s", this.by);
    }

    @Override
    public String toString() {
        return "[D]" + (this.done? "[X] " : "[ ] ")
                + this.name
                +String.format("(by:%s)",this.by);
    }
}
