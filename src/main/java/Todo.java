import java.time.LocalDate;

public class Todo extends Task{
    private String name;
    private boolean isDone;

    public Todo(String name, boolean isDone) {
        super();
        this.name = name;
        this.isDone = isDone;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isDone() {
        return isDone;
    }


    @Override
    public String toString() {
        return "T" + " " +  name  +" "+ isDone  + '\n';
    }

}