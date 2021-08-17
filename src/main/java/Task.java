public abstract class Task {
    private boolean flag;
    private String label;

    public Task(String label) {
        this.label = label;
        this.flag = false;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getLabel() {
        return label;
    }

    public Boolean getFlag() {
        return flag;
    }

    public abstract String getType();

    @Override
    public String toString() {
        if (flag) {
            return "[X] " + label;
        } else {
            return "[ ] " + label;
        }
    }
}
