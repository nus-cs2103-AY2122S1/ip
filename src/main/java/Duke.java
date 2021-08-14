import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static List<String> toDoList = new ArrayList<>();

    public static void main(String[] args) {
        String dottedLines = "----------------------------------------------------------------------------------------";
        System.out.println(dottedLines);
        System.out.println("Hello I'm LOTTERY-A");
        System.out.println("Also known as the List Of Tasks That Eventually Require Your Attention");
        System.out.println("What can I do for you?");
        System.out.println(dottedLines);

        Scanner keyboard = new Scanner(System.in);
        while(true) {
            String userInput = keyboard.nextLine();
            if(userInput.equals("bye")) {
                System.out.println(dottedLines);
                System.out.println("Bye. Don't forget, these tasks will still require your attention when you return!");
                System.out.println(dottedLines);
                break;
            } else if(userInput.equals("list")) {
                for(int i = 0; i < toDoList.size(); i++) {
                    System.out.println(i+1 + ". " + toDoList.get(i));
                }
            } else {
                toDoList.add(userInput);
                System.out.println(dottedLines);
                System.out.println("added: " + userInput);
                System.out.println(dottedLines);
            }
        }
    }
}