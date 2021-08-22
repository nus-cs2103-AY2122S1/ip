public class ToDo extends Task{

    public ToDo(String actionName, boolean compleated, String type) {
        super(actionName, compleated, type, "1/1/2000 0000");
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
