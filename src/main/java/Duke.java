import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {
        System.out.println("Hello I am Duke. \nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        String echo = scanner.nextLine();
        while(true) {
            if (echo.equals("bye")) {
                System.out.println("Bye! See you next time!");
                break;
            }
            System.out.println(echo);
            echo = scanner.nextLine();
        }

    }
}
