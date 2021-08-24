package duke;

public class Task {
    String name;
    boolean done;
    public Task(String name){
        this.name = name;
        done = false;
    }
    public void makeDone(){
        done = true;
    }
    public void makeUndone(){
        done = false;
    }
    @Override
    public String toString(){
        if(done == true){
            return "[X] " + name;
        }else{
            return "[ ] " + name;
        }
    }
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Task)){
            return false;
        }else{
            Task objTask = (Task) obj;
            if(objTask.name.equals(this.name)){
                return true;
            }else{
                return false;
            }
        }
    }

}
