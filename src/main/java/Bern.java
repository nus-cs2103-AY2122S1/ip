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

        System.out.println("Hi! I'm Bern, your trustworthy chatbot. \nWhat can I do for you?");

        while (true) {
            String input = myObj.nextLine();
            if (input.length() >= 6 && input.substring(0, 4).equals("done") && Integer.parseInt(input.substring(5)) <= available){
                int index = Integer.parseInt(input.substring(5)) - 1;
                listTask[index].markAsDone();
                System.out.println("Good job! I've marked this task as done:\n"
                        + "[" + listTask[index].getStatusIcon() + "]"
                        + listTask[index].getDescription());
            } else if (!input.equals("bye") && !input.equals("list")) {
                System.out.println("added: " + input);
                listTask[available] = new Task(input);
                available += 1;
            } else if (input.equals("bye")){
                System.out.println("Bye. Hope to see you soon and hope you found my service useful!");
                break;
            } else { // input equals "list"
                for (int i = 0; i < available; i++) {
                    System.out.println(String.valueOf(i + 1)
                            + ". "
                            + "[" + listTask[i].getStatusIcon() + "] "
                            + listTask[i].getDescription());
                }
                if (available == 0) {
                    System.out.println("There is no task.");
                }
            }
        }
    }
}
