public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    @Override
    public String getDescription() {
        return super.getDescription().replace("todo ", "");
    }

    @Override
    public void markedDone() {
        this.isDone = true;
    }

    @Override
    public String getStatusIcon() {
        return "[T]" + super.getStatusIcon();
    }

    @Override
    public String printTask() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
