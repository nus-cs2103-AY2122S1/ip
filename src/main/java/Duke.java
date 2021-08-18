import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! This is Jarvis.");
        System.out.println("What can I do for you sir?");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String task = sc.nextLine();
            if (task.equals("bye")) {
                System.out.println("Goodbye Sir. Hope you have a pleasant day sir.");
                break;
            }
            System.out.println(task);
        }
        sc.close();
    }
}
