package duke;

public class ToDo extends Task{

    public ToDo(String description){
        super(description);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    @Override
    public String getIcon(){
        return "T";
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof ToDo){
            ToDo toDo = (ToDo) obj;
            return toDo.toString().equals(this.toString());
        } else {
            return false;
        }
    }
}
