public class Deadline extends Task{
    private String name;
    private boolean isDone;
    private int index;

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

    private String time;

    public Deadline(String name, boolean isDone, int index, String time) {
        super();
        this.name = name;
        this.isDone = isDone;
        this.index = index;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "D" +  name  +" "+ isDone  +" "+ index  +" "+ time + '\n';
    }


}