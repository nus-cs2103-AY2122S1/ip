package duke.task;

public abstract class Task {
    private int done;
    private String toDo;

    Task(String toDo) {
        this(0, toDo);
    }

    Task(int done, String toDo){
        this.done = done;
        this.toDo = toDo;
    }

    public void complete(){
        this.done = 1;
    }

    String getToDo() {
        return toDo;
    }

    String getDone() {
        return String.valueOf(done);
    }

    public String getToWrite() {
        return  this.getDone() + " | "  + this.getToDo();
    }

    @Override
    public String toString(){
        if(done == 1){
            return String.format("[X] %s", toDo);
        } else {
            return String.format("[ ] %s", toDo);
        }
    }

}
