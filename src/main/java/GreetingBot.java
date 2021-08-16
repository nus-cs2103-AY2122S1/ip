import java.util.Scanner;
import java.util.LinkedList;
public class GreetingBot {

    public GreetingBot() {

    }

    public void startBot() {
        greet();
        store();
        exit();
    }


    private void greet() {
        String greetingMessage = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greetingMessage);

    }

    private void store() {
        Scanner inputScanner = new Scanner(System.in);
        LinkedList<String> myList = new LinkedList<>();
        while (true) {
            String nextLine = inputScanner.nextLine();
            if (nextLine.equals("list")) {
                list(myList);
            } else if  (nextLine.equals("bye")) {
                break;
            } else {
                myList.add(nextLine);
                System.out.println("added: " + nextLine);
            }
        }
    }

    private void list(LinkedList<String> myList) {
        int counter = 0;
        while(counter < myList.size()) {
            System.out.println((counter + 1) + ". " + myList.get(counter));

            counter += 1;
        }



    }


    private void echo() {
        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            String nextLine = inputScanner.nextLine();
            if (nextLine.equals("bye")) {
              break;
            } else {
                System.out.println(nextLine);
            }
        }
    }

    private void exit() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
    }
}
