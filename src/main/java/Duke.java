import java.util.*;
class User {
    public String command() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        return str;
    }
}
class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void markAsDone() {
        this.isDone = true;
    }
}
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hope you are doing well. How can I help you?");
        User user1 = new User();
        List<Task> userList = new ArrayList<>();
        while (true) {
            String command = user1.command();
            if (command.equals("bye")) {
                System.out.println("   Bye. Have a great day!");
                break;
            } else if (command.equals("list")) {
                int count = 1;
                for (int i = 0; i < userList.size(); i++) {
                    Task t = userList.get(i);
                    System.out.println("   " + count + ".[" + t.getStatusIcon() + "] " + t.description);
                    count++;
                }
            } else if (command.startsWith("done") && Character.isDigit(command.charAt(command.length() - 1))
                    && command.length() <= 8 && !Character.isAlphabetic(command.charAt(command.length() - 2))
                    && Character.isDigit(command.charAt(5))) {
                int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
                if (value > userList.size()) {
                    System.out.println("   Sorry the task doesn't exist yet, please try again!");
                } else {
                    Task t = userList.get(value - 1);
                    t.markAsDone();
                    System.out.println("   Nice! I've marked this task as done: ");
                    System.out.println("     [" + t.getStatusIcon() + "] " + t.description);
                }
            } else {
                Task task = new Task(command);
                userList.add(task);
                System.out.println("   added: " + command);
            }
        }
    }
}
