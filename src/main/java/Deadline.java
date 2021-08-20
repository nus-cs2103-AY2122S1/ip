public class Deadline extends Task{

    String date;

    public Deadline(String actionName, boolean compleated, String date, String type) {
        super(actionName, compleated, type);
        this.date = date; 
    }

    @Override
    public String toString(){
        String check = "[ ]";
        if (this.compleated){
            check = "[X]";
        }
        return "[D] " + check + " " + this.actionName + "(by: " + date + ")";
    }
}


