class TaskList {
    private Task[] list;
    private int size;

    public TaskList() {
        this.list = new Task[100];
        this.size = 0;
    }

    public void add(Task task) {
        this.list[this.size] = task;
        this.size += 1;
    }

    public Task get(int index) {
        return this.list[index];
    }
    public int size() {
        return this.size;
    }
    public void display() {
        for (int i = 0; i < this.size; i++) {
            System.out.println(String.format("%s. %s", i+1, this.list[i].toString()));
        }
    }
}
