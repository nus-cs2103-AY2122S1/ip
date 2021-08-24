public class Event extends Task {
    private String date;
    public Event(String name, String date) {
        super(name);
        this.date = date;
    }
    @Override
    public String toString(){
        if(done == true){
            return "[E][X] " + name +  " (at: " + date + ")";
        }else{
            return "[E][ ] " + name + " (at: " + date + ")";
        }
    }
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Event)){
            return false;
        }else{
            Event objTask = (Event) obj;
            if(objTask.name.equals(this.name) && objTask.date.equals(this.date)){
                return true;
            }else{
                return false;
            }
        }
    }
}