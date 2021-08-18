import java.util.Scanner;
public class Duke {
    public static Task[] tasks;
    public static Integer counter1 = 0;

    public static class Task {
        protected String description;
        protected boolean isDone;
        protected Integer order;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
            this.order = 0;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void markAsDone() {
            this.isDone = true;
        }
        //...
    }

    public Task newTask(String intro) {
        return new Task(intro);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String message = "Hello! I'm Duke \n" + "What can I do for you? \n";
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Task[] tasks = new Task[100];
        //String[] tasks = new String[100];
        int counter = 0;
        while(!input.equals("bye")) {
            if(input.equals("list")) {
                System.out.println(("\t Here are the tasks in your list:"));
                for(Task s:tasks) {
                    if(s != null) {
                        System.out.println(("\t" + s.order.toString() + ".[" + s.getStatusIcon() + "] " + s.description));
                    }
                }
                input = scanner.nextLine();
            } else if(input.split(" ")[0].equals("done")) {
                Integer count = Integer.valueOf(input.split(" ")[1]);
                tasks[count - 1].markAsDone();
                System.out.println("\t Nice! I've marked this task as done: \n \t \t" +
                        " [" + tasks[count - 1].getStatusIcon() + "] " + tasks[count - 1].description);
                input = scanner.nextLine();
            }
            else {
                counter = counter + 1;
                Task Task1 = new Task(input);
                Task1.order = counter;
                tasks[counter - 1] = Task1;
                //tasks[counter - 1] = Integer.toString(counter) + ". " + input;
                System.out.println("\t" + "added: " + input);
                input = scanner.nextLine();
            }
        }
        String ending = "Bye. Hope to see you again soon!";

        System.out.println("\t" + ending);


    }
}
