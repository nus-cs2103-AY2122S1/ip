import java.util.Scanner;

public class Duke {
    static Task[] listOfText = new Task[100];

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

//    private static void list(String line) {
//        for(int i = 0; i < 100; i++) {
//            if(listOfText[i] == null) {
//                listOfText[i] = new Task(line);
//                break;
//            }
//        }
//        System.out.println("added: " + line);
//    }

    private static void list(Task task) {
        int counter = 0;
        for(int i = 0; i < 100; i++) {
            if(listOfText[i] == null) {
                listOfText[i] = task;
                break;
            }
        }

        for(int j = 0; j < 100; j++) {
            if(listOfText[j] != null) {
                counter++;
            }
        }
        System.out.println("Got it. I've added this task:\n" + "  "+ task.toString() +"\nNow you have "+counter+" tasks in the list.");
    }

    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < 100; i++) {
            if(listOfText[i] != null) {
                System.out.println(i+1 + "." +  listOfText[i].logo() + listOfText[i].getStatusIcon() + " " + listOfText[i].description);
            }
        }
    }

    private static void changeStatus(int number) { //need check if number is valid
        if ((number > 0) && (number <= 100)) {
            if(listOfText[number-1] != null) {
                listOfText[number-1].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n  " + listOfText[number-1] );
            }
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String input = sc.nextLine();
        while(!(input.equals("bye"))) {

            if(input.equals("list")) {
                printList();
                input = sc.nextLine();
            }else if (input.length() > 5 && input.substring(0, 5).equals("done ")) {
                String[] split = input.split("\\s+");
                int number = Integer.parseInt(split[1]);
                changeStatus(number);
                input = sc.nextLine();
            }else if (input.length() > 9 && input.substring(0, 9).equals("deadline ")) {
                String[] split = input.substring(9).split(" /by ");
                list(new Deadline(split[0], split[1]));
                input = sc.nextLine();
            } else if (input.length() > 5 && input.substring(0, 5).equals("todo ")) {
                list(new ToDo(input.substring(5)));
                input = sc.nextLine();
            } else if (input.length() > 6 && input.substring(0, 6).equals("event ")) {
                String[] split = input.substring(6).split(" /at ");
                list(new Event(split[0], split[1]));
                input = sc.nextLine();
            }
        }

        if(input.equals("bye")) {
            bye();
        }
    }
}

