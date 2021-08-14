public class ToDo extends Task{

    public ToDo(String actionName, boolean compleated) {
        super(actionName, compleated);
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
