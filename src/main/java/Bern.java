import java.util.Scanner;

public class Bern {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        Task[] listTask = new Task[100];
        int available = 0;

        System.out.println("Hi! I'm Bern, your trustworthy chatbot.\nWhat can I do for you?");

        while (true) {
            String input = myObj.nextLine();
            if (input.length() >= 6 && input.substring(0, 4).equals("done")){
                int index = Integer.parseInt(input.substring(5)) - 1;
                listTask[index].markAsDone();
                System.out.println("Good job! I've marked this task as done:\n"
                        + listTask[index].toString());
            } else if (input.length() >= 10 && input.substring(0, 8).equals("deadline")) {
                String task = input.substring(9, input.indexOf('/') - 1);
                String by = input.substring(input.indexOf('/') + 4);
                listTask[available] = new Deadline(task, by);
                System.out.println("Got it. I've added this task:\n"
                        + listTask[available].toString() + "\n"
                        + "Now you have " + String.valueOf(available + 1)
                        + (available == 0 ? "task in the list" : " tasks in the list")
                );
                available += 1;
            } else if (input.length() >= 7 && input.substring(0, 5).equals("event")) {
                String task = input.substring(6, input.indexOf('/') - 1);
                String at = input.substring(input.indexOf('/') + 4);
                listTask[available] = new Event(task, at);
                System.out.println("Got it. I've added this task:\n"
                        + listTask[available].toString() + "\n"
                        + "Now you have " + String.valueOf(available + 1)
                        + (available == 0 ? "task in the list" : " tasks in the list")
                );
                available += 1;
            } else if (input.length() >= 6 && input.substring(0, 4).equals("todo")) {
                listTask[available] = new ToDo(input.substring(5));
                System.out.println("Got it. I've added this task:\n"
                        + listTask[available].toString() + "\n"
                        + "Now you have " + String.valueOf(available + 1)
                        + (available == 0 ? " task in the list" : " tasks in the list")
                );
                available += 1;
            } else if (input.equals("bye")){
                System.out.println("Bye. Hope to see you soon and hope you found my service useful!");
                break;
            } else if (input.equals("list")){
                for (int i = 0; i < available; i++) {
                    System.out.println(String.valueOf(i + 1)
                            + ". "
                            + listTask[i].toString());
                }
                if (available == 0) {
                    System.out.println("There is no task.");
                }
            } else {
                System.out.println("Command not understood");
            }
        }
    }
}
