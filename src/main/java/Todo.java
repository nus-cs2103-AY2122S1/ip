
public class Todo extends Task{

    Todo(String title) {
        super(title);
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[T][X] " + this.title;
        } else {
            return "[T][ ]" + this.title;
        }
    }
}
