package duke;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }
    @Override
    public String toString(){
        if(done == true){
            return "[T][X] " + name;
        }else{
            return "[T][ ] " + name;
        }
    }
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof ToDo)){
            return false;
        }else{
            ToDo objTask = (ToDo) obj;
            if(objTask.name.equals(this.name)){
                return true;
            }else{
                return false;
            }
        }
    }
}
