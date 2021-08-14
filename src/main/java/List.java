public class List {

    private String[] tasks;
    private int index = 0;

    public List(int max) {
        tasks = new String[max];
    }

    public void addToList(String newTask) {
        tasks[index] = newTask;
        index++;
    }

    public String getList() {
        int counter = 1;
        String result = "";

        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != null) {
                result = result + counter + ". " + tasks[i] + "\n";
                counter++;
            }
        }
        return result;
    }

}
