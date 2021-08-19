import java.util.Scanner;

public class Task {
    private String[] tasks;
    private int count;

    public Task() {
        this.tasks = new String[100];
        this.count = 0;
    }

    public void taskListener() {
        Scanner sc = new Scanner(System.in);

        String separator = "-----------------------------------------------------------------";

        while (sc.hasNext()) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                String farewell = "Bye-bye! Hope to see you again soon!";

                System.out.println(separator + "\n"
                        + "added: " + farewell + "\n"
                        + separator + "\n");
                break;
            } else if (input.equals("list")) {
                int n = listTasks();
            } else {
                int n = addTask(input);
            }
        }
    }

    public int listTasks() {
        String separator = "-----------------------------------------------------------------";

        int n = 0;

        System.out.println(separator);

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