public abstract class Task {
    private String description;
    private boolean isDone;
    private String icon; 

    public Task(String description, String icon) {
        this.description = description;
        this.icon = icon; 
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
        System.out.println("Nice! I've marked this task as done:\n" + this.toString());
    }
    
    public String toData() {
        String isDoneNum = isDone ? "1" : "0"; 
        return String.format("%s|%s|%s", icon, isDoneNum, description); 
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), description);
    }
}
