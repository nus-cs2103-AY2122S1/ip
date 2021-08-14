public class List {

    private Task[] tasks;
    private int index = 0;

    public List(int max) {
        tasks = new Task[max];
    }

    public void addToList(String newTask) {
        tasks[index] = new Task(newTask);
        index++;
    }

    public String taskDone(int id) {
        tasks[id - 1].markAsDone();
        return tasks[id - 1].toString();
    }

    public String getList() {
        int counter = 1;
        String result = "";

        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != null) {
                result = result + counter + "." + tasks[i] + "\n";
                counter++;
            }
        }
        return result;
    }

}
