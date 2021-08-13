import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String intro = "Hey there! I'm Good Duke. How many I help you today?";
        String outro = "That was an excellent chat - I look forward to seeing you again soon!";
        Scanner sc = new Scanner(System.in);
        System.out.println(intro);
        while (true) {
            String userEntry = sc.nextLine();
            if (!userEntry.equals("bye")) {
                System.out.println(userEntry);
            } else {
                System.out.println(outro);
                break;
            }
        }
    }
}
