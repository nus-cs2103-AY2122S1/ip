public abstract class Task {
    protected String desc;
    protected boolean done;

    public Task() {}

    public Task(String desc) {
            this.desc = desc;
    }
    
    public Task(String desc, boolean done) {
        this.desc = desc;
        this.done = done;
    }

    public void addDesc(String desc) {
        this.desc = desc;
    }

    public void markComplete() {
        done = true;
    }
    
    public abstract String toDB();

    @Override
    public String toString() {
        return (done ? "[X] " : "[ ] ") + desc;
    }

}
