class TaskList {
    private String[] list;
    private int size;

    public TaskList() {
        this.list = new String[100];
        this.size = 0;
    }

    public void add(String val) {
        this.list[this.size] = val;
        this.size += 1;
    }

    public void display() {
        for (int i = 0; i < this.size; i++) {
            System.out.println(String.format("%s. %s", i+1, this.list[i]));
        }
    }
}
