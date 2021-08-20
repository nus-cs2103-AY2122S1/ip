public class ToDo extends Task{

    public ToDo(String actionName, boolean compleated, String type) {
        super(actionName, compleated, type);
    }

    @Override
    public String toString(){
        String check = "[ ]";
        if (this.compleated){
            check = "[X]";
        }
        return "[T] " + check + " " + this.actionName;
    }
}
