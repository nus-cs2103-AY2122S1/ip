class Task {
    private int done;
    private String toDo;

    Task(String toDo) {
        this(0, toDo);
    }

    Task(int done, String toDo){
        this.done = done;
        this.toDo = toDo;
    }

    void complete(){
        this.done = 1;
    }

    @Override
    public String toString(){
        if(done == 1){
            return String.format("[X] %s", toDo);
        } else {
            return String.format("[ ] %s", toDo);
        }
    }

}
