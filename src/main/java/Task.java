public class Task{
    String actionName;
    boolean compleated;

    public Task(String actionName, boolean compleated){
        this.actionName = actionName;
        this.compleated = compleated;
    } 

    public String toString(){
        String check = "[ ]";
        if (this.compleated){
            check = "[X]";
        }
        return check + " " + this.actionName;
    }

    public void doneTask(){
        this.compleated = true;
    }
}
