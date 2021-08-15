import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String div = "____________________________________________________________\n";
        System.out.println(div + "Hello! I'm Bobby\nWhat can I do for you?\n" + div);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(div + "Bye. Hope to see you again soon!\n" + div);
                break;
            }
            System.out.println(div + userInput + "\n" + div);
        }
    }


}
