public class Todo extends Task {
    private String marker = "[T]";

    public Todo(String name) {
        super(name);
    }

//    public String getMarker() {
//        return this.marker;
//    }

    @Override
    public String toString() {
        return this.marker + super.toString();
    }
}