public class Todo extends Task {

    public Todo(String description, int number) {
        super(description, number);
    }

    @Override
    public String getTask() {
        return number + "." + this.getTaskNoNum();
    }

    @Override
    public String getTaskNoNum() {
        return "[T]" + super.getTaskNoNum();
    }
}