public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }


    @Override
    public String toString() {
        return "[T][" + this.getStatus() + "] " + this.name;
    }

}
