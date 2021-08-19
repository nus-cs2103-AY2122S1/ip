import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String tab = "      ";
        String line = "------------------------------------------------------------";
        System.out.println(line);
        System.out.println("Hello. My name is Necro.");
        System.out.println("What can I do for you on this horrible day?");
        System.out.println(line);
        System.out.println("");

        Scanner sc = new Scanner(System.in);

        // Level-1
//        while (true) {
//            String input = sc.nextLine();
//
//            if (input.equals("bye")) {
//                System.out.println(tab + line);
//                System.out.println(tab + " " + "Farewell, may we never meet again.");
//                System.out.println(tab + line);
//                break;
//            } else {
//                System.out.println(tab + line);
//                System.out.println(tab + " " + input);
//                System.out.println(tab + line);
//            }
//        }

        // Level-2
//        String[] arr = new String[101];
//        int i = 1;
//        while (true) {
//            String input = sc.nextLine();
//
//            if (input.equals("list")) {
//                System.out.println(tab + line);
//                for (int j = 1; j < i; j++) {
//                    System.out.println(tab + " " + j + ". " + arr[j]);
//                }
//                System.out.println(tab + line);
//            } else if (input.equals("bye")) {
//                System.out.println(tab + line);
//                System.out.println(tab + " " + "Farewell, may we never meet again.");
//                System.out.println(tab + line);
//                break;
//            } else {
//                System.out.println(tab + line);
//                System.out.println(tab + " added: " + input);
//                System.out.println(tab + line);
//                arr[i] = input;
//                i++;
//            }
//        }

        // Level 3
//        Task[] tasks = new Task[101];
//        int i = 1;
//        while (true) {
//            String input = sc.nextLine();
//
//            if (input.contains("done ")) {
//                int taskNumber = input.charAt(input.length() - 1) - 48;
//                input.split("/");
//                if (taskNumber < i) {
//                    tasks[taskNumber].complete();
//                    System.out.println(tab + "Wow. Congratulations. You have completed the following task:");
//                    System.out.println(tab + " " + tasks[taskNumber].status);
//                    System.out.println(tab + "Are you happy now?");
//                } else {
//                    System.out.println("You have entered an invalid task number. Fool.");
//                }
//            } else if (input.equals("list")) {
//                System.out.println(tab + line);
//                System.out.println("Here are the tasks in your list:");
//                for (int j = 1; j < i; j++) {
//                    System.out.println(tab + " " + j + ". " + tasks[j].status);
//                }
//                System.out.println(tab + line);
//            } else if (input.equals("bye")) {
//                System.out.println(tab + line);
//                System.out.println(tab + " " + "Farewell, may we never meet again.");
//                System.out.println(tab + line);
//                break;
//            } else {
//                System.out.println(tab + line);
//                System.out.println(tab + " added: " + input);
//                System.out.println(tab + line);
//                tasks[i] = new Task(input);
//                i++;
//            }
//        }

        // Level-4
        Task[] tasks = new Task[101];
        int i = 1;
        while (true) {
            String input = sc.nextLine();

            if (input.contains("done ")) {
                int taskNumber = input.charAt(input.length() - 1) - 48;
                input.split("/");
                if (taskNumber < i) {
                    tasks[taskNumber].complete();
                    System.out.println(tab + "Wow. Congratulations. You have completed the following task:");
                    System.out.println(tab + " " + tasks[taskNumber].status);
                    System.out.println(tab + "Are you happy now?");
                } else {
                    System.out.println("You have entered an invalid task number. Fool.");
                }
            } else if (input.equals("list")) {
                System.out.println(tab + line);
                System.out.println(tab + "Here are the tasks in your list:");
                for (int j = 1; j < i; j++) {
                    System.out.println(tab + " " + j + ". " + tasks[j].status);
                }
                System.out.println(tab + line);
            } else if (input.equals("bye")) {
                System.out.println(tab + line);
                System.out.println(tab + " " + "Farewell, may we never meet again.");
                System.out.println(tab + line);
                break;
            } else {
                String output = "";
                if (input.contains("todo ")) {
                    String[] strings = input.split(" ", 2);
                    ToDo toDo = new ToDo(strings[1]);
                    output = toDo.status;
                } else if (input.contains("deadline ")) {
                    String[] strings = input.split("/", 2);
                    // if strings[1] has more than one "/", then there is a problem. Catch error here.
                    String[] strings1 = strings[0].split(" ", 2);
                    String[] strings2 = strings[1].split(" ", 2);
                    Deadline deadline = new Deadline(strings1[1], strings2[1]);
                    output = deadline.status;
                } else if (input.contains("event ")) {
                    String[] strings = input.split("/", 2);
                    // if strings[1] has more than one "/", then there is a problem. Catch error here.
                    String[] strings1 = strings[0].split(" ", 2);
                    String[] strings2 = strings[1].split(" ", 2);
                    Event event = new Event(strings1[1], strings2[1]);
                    output = event.status;
                } else {
                    System.out.println("You have entered an invalid message. Please do not commit this idiocy again.");
                    continue;
                }
                System.out.println(tab + line);
                System.out.println(tab + "Fine. I've added this sorry task to your list: ");
                System.out.println(tab + " --> " + output);
                System.out.println(tab + "Satisfied now? You have " + i + " items in your list.");
                System.out.println(tab + line);
                tasks[i] = new Task(output);
                tasks[i].status = tasks[i].status.substring(4);
                i++;
            }
        }

    }
}
