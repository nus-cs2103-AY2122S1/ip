import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        System.out.println("*******************************************");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("*******************************************");

        Scanner myScanner= new Scanner(System.in);
        Boolean hasQuit = false;

        while (!hasQuit && myScanner.hasNextLine()) {
            String userInput = myScanner.nextLine();
            if (userInput.equals("bye")) {
                hasQuit = true;
                System.out.println("Bye. Hope to see you again soon!");
            } else {
                System.out.println(userInput);
            }
            System.out.println("*******************************************");
        }
        myScanner.close();
    }
}

