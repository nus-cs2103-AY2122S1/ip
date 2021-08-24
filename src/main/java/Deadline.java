public class Deadline extends Task {
    private String date;
    public Deadline(String name, String date) {
        super(name);
        this.date = date;
    }
    @Override
    public String toString(){
        if(done == true){
            return "[D][X] " + name + " (by: " + date + ")";
        }else{
            return "[D][ ] " + name + " (by: " + date + ")";
        }
    }
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Deadline)){
            return false;
        }else{
            Deadline objTask = (Deadline) obj;
            if(objTask.name.equals(this.name)&&objTask.date.equals(this.date)){
                return true;
            }else{
                return false;
            }
        }
    }
}
