public class ToDo extends Task{

    public ToDo(String description){
        super(description);
    }

    @Override
    public String checkStatus() {
        return "[T]" + (isDone ? "[X]" : "[ ]") + " " + this.showDescription();
    }

}
