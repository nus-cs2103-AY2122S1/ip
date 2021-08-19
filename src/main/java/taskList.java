public class taskList {
    private final Task[] lst;
    private final String[] emptyList =
            new String[] {"The list is empty."};
    private int count;

    public taskList(int len) {
        this.lst = new Task[len];
        this.count = 0;
    }

    public String addItem(String item) {
        // Checks if the list is full
        if (this.count >= this.lst.length) {
            return "Length of list exceeded.";
        } else {
            this.lst[this.count++] = new Task(item);
            return "added: " + item;
        }
    }

    public String[] getList() {
        // Returns emptylist if the list contains no items
        if (this.count == 0) {
            return this.emptyList;
        }

        String[] temp = new String[count + 1];
        temp[0] = "Here are the tasks in your list:";
        for (int i = 1; i < this.count + 1; ++i) {
            temp[i] = i + "." + this.lst[i - 1].toString();
        }

        return temp;
    }

    public String markAsDone(int n) {
        if (n < 1 || n > count) {
            return "There is no task " + n;
        } else {
            return "Nice! I've marked this task as done:\n" +
                    this.lst[n - 1].completeTask();
        }
    }
}
