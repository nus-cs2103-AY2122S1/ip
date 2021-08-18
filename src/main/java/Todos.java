public class Todos extends Task {

    public Todos(String name) {
        super(name);
    }

    @Override
    public String toString() {
        if (isDone) {
            return ("[T] [X] " + name);
        } else {
            return ("[T] [ ] " + name);
        }
    }
}
