public class ToDos extends Task {
    ToDos(String name, boolean done){
        super(name, done);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", (getDone())? "X" : " ", getName());
    }

    @Override
    Task markDone() {
        return new ToDos(getName(), true);
    }
}
