import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        System.out.println("Hello! This is Duke :)" + "\n" + "What can I do for you?");
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ". " + list.get(i-1));
                }
            } else {
                list.add(input);
                System.out.println("added: " + input);
            }
            input = sc.nextLine();
        }
        System.out.println("Bye! See you again soon!!");
    }
}
