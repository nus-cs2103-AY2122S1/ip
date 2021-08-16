public class Todo extends Task{

    public Todo(String desc) {
        super(desc, false);

    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + "\n";
    }

}
