public class ToDos extends Task {
    protected String description;
    protected boolean isDone;

    public ToDos(String description){
        super(description);
    }

    @Override
    public void displayTask(){
        System.out.println("[T]"+"[" + getStatusIcon() + "]" + " " + description);
    }
}
