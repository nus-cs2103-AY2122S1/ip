public class ToDo extends Task{
    public ToDo(String taskContent) {
        super(taskContent);
    }
    @Override
    public String toString() {
        if(super.isCompleted()) {
            return "[T][X] " + super.getTaskContent();
        }else {
            return "[T][ ] " + super.getTaskContent();
        }
    }
}
