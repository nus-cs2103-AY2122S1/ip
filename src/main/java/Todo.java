public class Todo extends Task {

    Todo(String content){
        super(content);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
