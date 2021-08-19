import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello...\nWhat do you want?");
        Scanner myScanner = new Scanner(System.in);
        String answer = myScanner.nextLine();
        while (!answer.equals("bye")) {
            System.out.println(answer);
            answer = myScanner.nextLine();
        }
        System.out.println("Whatever...");
    }
}
