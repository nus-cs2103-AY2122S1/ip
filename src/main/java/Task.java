public class Task {

    private final String name;
    private final boolean done;

    Task(String name, boolean done){
        this.name = name;
        this.done = done;
    }

    Task markDone() {
        return new Task(this.name, true);
    }

    Task markUndone() {
        return new Task(this.name, false);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", (this.done)? "X" : " ", this.name);
    }

    String getName(){
        return this.name;
    }

    Boolean getDone(){
        return this.done;
    }


}
