public class ToDo extends Task {
    public ToDo(String name){
        super(name);
    }

    @Override
    public String toString(){
        if (this.isDone){
            return "[T][X] " + this.name;
        }
        else {
            return "[T][ ] " + this.name;
        }
    }
}
