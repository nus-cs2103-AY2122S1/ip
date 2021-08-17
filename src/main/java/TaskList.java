public class TaskList {
    private Task[] taskList;
    private int length;

    public TaskList() {
        this.taskList = new Task[100];
        this.length = 0;
    }

    public void add(String newItem) {
        if (!newItem.equals("")) {
            Task newTask = new Task(newItem);
            taskList[this.length] = newTask;
            System.out.println("added: " + newItem);
            this.length++;
        }
    }

    public void done(String strNum) {
        int index = Integer.parseInt(strNum) - 1;
        Task toMark = taskList[index];
        toMark.markAsDone();
    }

    @Override
    public String toString() {
        if (length == 0) {
            return "Your list is empty :(";
        }
        String result = "Here's your list!";
        for (int i = 0; i < length; i++) {
            String curr = this.taskList[i].toString();
            result += String.format("\n %s. %s", i + 1, curr);
        }
        return result;
    }
}
