import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String dottedLines = "----------------------------------------------------------------------------------------";
        System.out.println(dottedLines);
        System.out.println("Hello I'm LOTTERY-A");
        System.out.println("Also known as the List Of Tasks That Eventually Require Your Attention");
        System.out.println("What can I do for you?");
        System.out.println(dottedLines);

        Scanner keyboard = new Scanner(System.in);
        while(true) {
            String userInput = keyboard.next();
            if(userInput.equals("bye")) {
                System.out.println(dottedLines);
                System.out.println("Bye. Don't forget, these tasks will still require your attention when you return!");
                System.out.println(dottedLines);
                break;
            } else {
                System.out.println(dottedLines);
                System.out.println(userInput);
                System.out.println(dottedLines);
            }
        }
    }
}