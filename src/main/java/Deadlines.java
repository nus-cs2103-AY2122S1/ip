public class Deadlines extends Task{
    protected String description;
    protected boolean isDone;

    public Deadlines(String description){
        super(description);
    }

    @Override
    public void displayTask(){
        System.out.println("[D]"+"[" + getStatusIcon() + "]" + " " + description);
    }
}

