public class TaskList {
    private Task[] taskList;
    private int length;

    public TaskList() {
        this.taskList = new Task[100];
        this.length = 0;
    }

    public void add(Task newTask) {
        taskList[this.length] = newTask;
        System.out.println("Just added:\n" + newTask.toString());
        this.length++;
        System.out.println("You currently have " + length + " tasks in the list.");
    }

    public void done(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("Pls specify the task index");
        }
        String num = input[1];
        int index = Integer.parseInt(num) - 1;
        if (index >= this.length) {
            throw new DukeException("There is no such task in your list D:");
        } else {
            Task toMark = taskList[index];
            toMark.markAsDone();
        }
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
