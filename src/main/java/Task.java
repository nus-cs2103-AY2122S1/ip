/**
 * Task created by Duke
 */
public class Task {

    String name;
    boolean done = false;


    Task(String name){
        this.name = name;
        this.done = false;
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

    public String toString(){
        return (this.done? "[X] " : "[ ] ")
            + this.name;
    }
}

