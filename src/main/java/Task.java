public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setDone(){
        this.isDone = true;
    }

    public void addToList(Task[] taskList, int counter) {
        taskList[counter] = this;
        System.out.printf("added: " + this.toString()
                  + "\nNow you have %s tasks in your list\n" +
                "----------------------------------------------------\n", counter + 1);
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }
}