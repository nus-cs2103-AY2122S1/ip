import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! This is Duke :)" + "\n" + "What can I do for you?");
        String input = "";
        while (!input.equals("bye")) {
            input = sc.nextLine();
            System.out.println(input);
        }
        System.out.println("Bye! See you again soon!!");
    }
}
