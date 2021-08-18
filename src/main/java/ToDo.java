public class ToDo extends Task{

    public ToDo(String description){
        super(description);
    }

    @Override
    public String checkStatus() {
        return (isDone ? "[T]" : "[]" + " " + this.showDescription());
    }

}
