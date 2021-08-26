import java.io.IOException;

public class Ui {
    String logo;
    Storage storage;
    TaskList list;

    public Ui(Storage storage, TaskList taskList) {
        this.storage = storage;
        this.list = taskList;
        this.logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    }

    public void drawLine() {
        System.out.println("___________________________________________");
    }

    public void greet() {
        System.out.println(logo);
        drawLine();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        drawLine();
    }

    public void goodbye() throws IOException {
        drawLine();
        storage.write(list.getTaskList(), storage.getFilePath());
        System.out.println("\tBye. Hope to see you again soon!");
        drawLine();
    }

    public void listTasks() {
        drawLine();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < list.length(); i++) {
            System.out.println("\t" + (i + 1) + ". " + list.getTask(i).toString());
        }
        drawLine();
    }

    public void markDone(int taskNumber) {
        drawLine();
        System.out.println("\tNice! I've marked this task as done:");
        list.getTask(taskNumber).markAsDone();
        System.out.println("\t\t[" + list.getTask(taskNumber).getStatusIcon() + "] "
                + list.getTask(taskNumber).getDescription());
        drawLine();
    }

    public void delete(int taskNumber) {
        drawLine();
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + list.getTask(taskNumber));
        list.deleteTask(taskNumber);
        System.out.println("\tNow you have " + list.length() + " in the list.");
        drawLine();
    }

    public void add(Task task) {
        drawLine();
        list.addTask(task);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tNow you have " + list.length() + " tasks in the list.");
        drawLine();
    }
}
