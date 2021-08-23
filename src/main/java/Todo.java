public class Todo extends Task{
    private String name;
    private boolean isDone;
    private int index;

    public Todo(String name, boolean isDone, int index) {
        super();
        this.name = name;
        this.isDone = isDone;
        this.index = index;
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
    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "T"+  name  +" "+ isDone  +" "+ index + '\n';
    }

}