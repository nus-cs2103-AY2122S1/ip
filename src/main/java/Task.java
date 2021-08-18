public abstract class Task {
    private boolean flag;
    protected String label;

    //necessary default constructor
    public Task() {
        label = "";
        flag = false;
    }

    public Task(String label) {
        this.label = label;
        this.flag = false;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        if (flag) {
            return "[X] " + label;
        } else {
            return "[ ] " + label;
        }
    }
}
