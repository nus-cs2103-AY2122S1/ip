import java.util.Scanner;

public class Duke {
    static Task[] listOfText = new Task[100];

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void list(String line) {
        for(int i = 0; i < 100; i++) {
            if(listOfText[i] == null) {
                listOfText[i] = new Task(line);
                break;
            }
        }
        System.out.println("added: " + line);
    }

    private static void printList() {
        for(int i = 0; i < 100; i++) {
            if(listOfText[i] != null) {
                System.out.println(i+1 + "." + listOfText[i].getStatusIcon() + " " + listOfText[i].description);
            }
        }
    }

    private static void changeStatus(int number) { //need check if number is valid
        if ((number > 0) && (number <= 100)) {
            if(listOfText[number-1] != null) {
                listOfText[number-1].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n[X] " + listOfText[number-1].description );
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
                int number= Integer.parseInt(split[1]);
                changeStatus(number);
                input = sc.nextLine();
            } else {
                list(input);
                input = sc.nextLine();
            }
        }

        if(input.equals("bye")) {
            bye();
        }
    }
}

