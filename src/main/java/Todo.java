public class Todo extends Task{
    public Todo(String description,int number){
        super(description,number);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
