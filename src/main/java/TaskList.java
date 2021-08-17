public class TaskList {
    private String[] taskList;
    private int length;

    public TaskList() {
        this.taskList = new String[100];
        this.length = 0;
    }

    public void add(String newItem) {
        if (!newItem.equals("")) {
            taskList[this.length] = newItem;
            System.out.println("added: " + newItem);
            this.length++;
        }
    }

    @Override
    public String toString() {
        if (length == 0) {
            return "Your list is empty :(";
        }
        String result = "Here's your list!";
        for (int i = 0; i < length; i++) {
            String curr = this.taskList[i];
            result += String.format("\n %s. %s", i + 1, curr);
        }
        return result;
    }
}
