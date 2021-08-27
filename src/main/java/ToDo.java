public class ToDo extends Task  {

    public ToDo(String description, boolean isDone){
        super(description, isDone, TaskType.TODO);
    }

    @Override
    public String getStatus(){
        return "[T]" + super.getStatus();
    }

    @Override
    public String toString(){
        return "T" + " | " + super.toString();
    }
}
