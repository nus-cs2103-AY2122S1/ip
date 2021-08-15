public class ToDos extends Task {
    protected String by;

    public ToDos(String description, String by){
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public void displayTask(){
        System.out.println(toString());
    }
}
