package task;

import duke.DukeException;
import duke.Parser;

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

    public static Task createTask(String type, String rest) throws DukeException{
        Task newTask;
        String[] name_delimit;
        switch (type) {
            case "todo":
                newTask = new ToDo(rest);
                break;

            case "deadline":
                name_delimit = Parser.parseArgs(rest, "/by");
                newTask = new Deadline(name_delimit[0], name_delimit[1]);
                break;

            case "event":
                name_delimit = Parser.parseArgs(rest, "/at");
                newTask = new Event(name_delimit[0], name_delimit[1]);
                break;

            default:
                throw new DukeException("command not found");
        }
        return newTask;
    }

    public static Task getTask(String s){
        String[] parts = Parser.parseStorage(s);
        Task t = createTask(parts[0], parts[1]);
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
