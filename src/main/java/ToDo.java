class ToDo extends Task {

    ToDo(String taskName) {
        super(taskName);
    }
    
    private ToDo(ToDo oldTask) {
        super(oldTask);
    }
    
    static protected ToDo createTask(String name, boolean isCompleted) {
        ToDo t = new ToDo(name);
        if (isCompleted) {
            return new ToDo(t);
        } else {
            return t;
        }
    } 

    @Override
    ToDo markAsCompleted() {
        return new ToDo(this);
    }

    @Override
    public String toString() {
        return "T: " + super.toString();
    }
}
