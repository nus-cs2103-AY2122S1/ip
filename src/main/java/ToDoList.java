public class ToDoList {
    private String[] tasks;
    private int count;

    public ToDoList() {
        this.tasks = new String[100];
        this.count = 0;
    }

    public int taskListener(String input) {
        return (input.equals("list"))
                ? listTasks()
                : addTask(input);
    }

    public int listTasks() {
        String separator = "-----------------------------------------------------------------";

        int n = 0;

        System.out.println(separator);
        System.out.println("Below are your to-dos!");

        while (this.tasks[n] != null) {
            System.out.println((n + 1) + ". " + this.tasks[n]);

            n++;
        }

        System.out.println(separator);

        return 0;
    }

    public int addTask(String todo) {
        String separator = "-----------------------------------------------------------------";

        this.tasks[count] = todo;
        count++;

        System.out.println(separator + "\n"
                + "added: " + todo + "\n"
                + separator);

        return 0;
    }
}