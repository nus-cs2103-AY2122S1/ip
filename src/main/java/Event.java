public class Event extends Task{
    String date;

    public Event(String actionName, boolean compleated, String date, String type) {
        super(actionName, compleated, type);
        this.date = date; 
    }

    @Override
    public String toString(){
        String check = "[ ]";
        if (this.compleated){
            check = "[X]";
        }
        return "[E] " + check + " " + this.actionName + "(at: " + date + ")";
    }
}
