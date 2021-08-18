public class Todo extends Task{

    public Todo(String description) {
        super(description.replace("todo ", ""), "[T]");
    }

}
