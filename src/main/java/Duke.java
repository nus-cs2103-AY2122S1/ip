import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        class Task {
            protected String description;
            protected boolean isDone;
            protected int number;

            public Task(String description, int number) {
                this.description = description;
                this.isDone = false;
                this.number = number;
            }

            public String getStatusIcon() {
                return (isDone ? "X" : " "); // mark done task with X
            }

            public void markAsDone() {
                this.isDone = true;
            }

            public String getTask() {
                return number + ". [" + this.getStatusIcon() + "] " + this.description;
            }

            public String getTaskNoNum() {
                return "[" + this.getStatusIcon() + "] " + this.description;
            }
        }

        Scanner myObj = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        boolean exit = false;
        Task[] tasks = new Task[100];
        int current = 0;
        while (!exit) {
            String userInput = myObj.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                exit = true;
            } else if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < current; i++) {
                    System.out.println(tasks[i].getTask());
                }
                System.out.println("____________________________________________________________");
            } else if (isDoneCall(userInput)) {
                int index = Integer.parseInt(userInput.substring(5));
                System.out.println("____________________________________________________________");
                if (tasks[index - 1] != null) {
                    tasks[index - 1].markAsDone();
                    System.out.println(" Nice! I've marked this task as done: ");
                    System.out.println("   " + tasks[index - 1].getTaskNoNum());
                } else {
                    System.out.println("There is no such task.");
                }
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("added: " + userInput);
                System.out.println("____________________________________________________________");
                Task task = new Task(userInput, current + 1);
                tasks[current] = task;
                current++;
            }
        }
    }
    public static boolean isDoneCall (String strNum) {
        if (strNum == null) {
            return false;
        }
        if (strNum.length() < 6) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum.substring(5));
        } catch (NumberFormatException nfe) {
            return false;
        }
        if (!strNum.startsWith("done ")) {
            return false;
        }
        return true;
    }
}
