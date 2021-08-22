package Task;

import Duke.DukeException;
import Duke.Duke.Commands;

public abstract class Task {
    private String name;
    private boolean done;

    public Task(String name) throws DukeException {
        if(name.isBlank()) {
            throw new DukeException("Please specify name");
        }
        this.name = name;
        this.done = false;
    }

    public static Task taskFactory(Commands cmd, String rest) throws DukeException{
        Task newTask;
        String[] name_delimit;
        switch (cmd) {
            case TODO:
                newTask = new ToDo(rest.trim());
                break;

            case DEADLINE:
                name_delimit = rest.split("/by");
                checkArg(name_delimit);
                newTask = new Deadline(name_delimit[0].trim(), name_delimit[1].trim());
                break;

            case EVENT:
                name_delimit = rest.split("/at");
                checkArg(name_delimit);
                newTask = new Event(name_delimit[0].trim(), name_delimit[1].trim());
                break;
            default:
                throw new DukeException("command not found");
        }
        return newTask;
    }
    public static Task getTask(String s){
        String[] parts = s.split("\\|");
        Commands cmd = Commands.valueOf(parts[0]);
        Task t = taskFactory(cmd, parts[1]);
        if(parts[2].equals("1")){
            t.markDone();
        }
        return t;
    }
    /**
     * marks this task as done
     */
    public void markDone(){
        this.done = true;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return done;
    }

    private static void checkArg(String[] arg) throws DukeException{
        if(arg.length < 2){
            throw new DukeException("Please specify time");
        }else if(arg.length > 2){
            throw new DukeException("too many argument");
        }
    }
    public abstract String saveTask();

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
