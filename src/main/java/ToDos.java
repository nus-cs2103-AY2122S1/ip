public class ToDos extends Task {
    private boolean isDone;

    public ToDos(String description) {
        super(description);
        this.isDone = false;
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
        return super.printTask();
    }
}
