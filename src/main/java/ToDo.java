public class ToDo extends Task {
    public ToDo(String name){
        super(name, false);
    }

    public ToDo(String name, boolean isDone){
        super(name, isDone);
    }

    @Override
    public String toCsvRow() {
        return String.join(",", "todo", name, String.valueOf(isDone));
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
