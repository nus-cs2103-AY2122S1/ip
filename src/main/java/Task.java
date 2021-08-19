public abstract class Task {
    private String name;
    private boolean done;

    public Task(String name) throws DukeException{
        if(name.equals("")) {
            throw new DukeException("empty name");
        }
        this.name = name;
        this.done = false;
    }

    public static Task taskFactory(Duke.Commands cmd, String rest) throws DukeException{
        Task newTask;
        switch (cmd) {
            case TODO:
                newTask = new ToDo(rest.trim());
                break;

            case DEADLINE:
                String[] name_by = rest.split("/by");
                if(name_by.length != 2){
                    throw new DukeException("invalid argument length for deadline");
                }
                newTask = new Deadline(name_by[0].trim(), name_by[1].trim());
                break;

            case EVENT:
                String[] name_at = rest.split("/at");
                if(name_at.length != 2){
                    throw new DukeException("invalid argument length for event");
                }
                newTask = new Event(name_at[0].trim(), name_at[1].trim());
                break;
            default:
                throw new DukeException("command not found");
        }
        return newTask;
    }
    /**
     * marks this task as done
     */
    public void markDone(){
        this.done = true;
    }

    /**
     * override toString method to add a check box beside task
     * @return String
     */
    @Override
    public String toString(){
        String check = done ? "X" : " ";
        return "[" + check + "] " + name;
    }
}
