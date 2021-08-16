public class Todo extends Task{
    Todo(String name){
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + (this.done? "[X] " : "[ ] ")
                + this.name;
    }
}
