import java.util.Scanner; // import the Scanner class

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String answer;
        answer = input.nextLine();
        if (answer.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else {
            System.out.println(answer);
        }
    }
}
