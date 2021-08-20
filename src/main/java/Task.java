public class Task{
    String actionName;
    boolean compleated;
    String type;

    public Task(String actionName, boolean compleated, String type){
        this.actionName = actionName;
        this.compleated = compleated;
        this.type = type;
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

    public String getActionName(){
        return this.actionName;
    }

    public boolean getCompleted(){
        return this.compleated;
    }

    public String getType(){
        return this.type;
    }
}
