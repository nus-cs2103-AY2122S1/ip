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

    public static Task taskFactory(String cmd, String rest) throws DukeException{
        Task newTask;
        switch (cmd) {
            case "todo":
                newTask = new ToDo(rest.trim());
                break;

            case "deadline":
                String[] name_by = rest.trim().split("/by");
                if(name_by.length != 2){
                    throw new DukeException("invalid argument length for deadline");
                }
                newTask = new Deadline(name_by[0], name_by[1]);
                break;

            case "event":
                String[] name_at = rest.trim().split("/at");
                if(name_at.length != 2){
                    throw new DukeException("invalid argument length for event");
                }
                newTask = new Event(name_at[0], name_at[1]);
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
