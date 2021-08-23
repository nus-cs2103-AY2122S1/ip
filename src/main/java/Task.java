/**
 * Task created by Duke
 */
abstract public class Task {

    String name;
    boolean done = false;


    /**
     * default constructor for a new task
     * @param name task name
     */
    Task(String name){
        this(name,false);
    }

    /**
     * Full constructor with all fields exposed
     * @param name task name
     * @param done done state
     */
    Task(String name, boolean done){
        this.name = name;
        this.done = done;
    }

    public String addMsg(){
        return "added: " + this.name;
    }

    public void markDone(){
        this.done = true;
    }

    public String getName(){
        return this.name;
    }

    /**
     * json-like representation of object data
     * @return String representation of data
     */
    abstract String serialize();
    // newline is the only? reliable way to divide data without escape characters

    public String toString(){
        return (this.done? "[X] " : "[ ] ")
            + this.name;
    }
}

