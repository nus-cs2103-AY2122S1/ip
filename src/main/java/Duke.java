import java.util.Scanner;

public class Duke {
    static String[] listOfText = new String[100];

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void list(String line) {
        for(int i = 0; i < 100; i++) {
            if(listOfText[i] == null) {
                listOfText[i] = line;
                break;
            }
        }
        System.out.println("added: " + line);
    }

    private static void printList() {
        for(int i = 0; i < 100; i++) {
            if(listOfText[i] != null) {
                System.out.println(i+1 + ". " + listOfText[i]);
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
