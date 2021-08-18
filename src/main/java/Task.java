public abstract class Task {
    private String name;
    private boolean done;
    public Task(String name){
        this.name = name;
        this.done = false;
    }

    /**
     * marks this task as done
     */
    public void markDone(){
        this.done = true;
    }

    /**
     * override toString method to add a check box beside task
     * @return String
     */
    @Override
    public String toString(){
        String check = done ? "X" : " ";
        return "[" + check + "] " + name;
    }
}
