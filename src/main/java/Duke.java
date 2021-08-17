import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String greetingMessage = "Henlo, this is Duke.\nHow may I help?";
        System.out.println(greetingMessage);
        String listCommand = "list";
        String byeString = "bye";

        String[] userItemList = new String[100];
        int currentCapacity = 0;

        while (true) {
            String response = sc.next();

            if (response.equals(byeString)) {
                System.out.println("Byebye!! Hehe..");
                break;
            }

            if (!response.equals(listCommand)) {
                if (currentCapacity < 100) {
                    userItemList[currentCapacity] = response;
                    currentCapacity += 1;
                    System.out.printf("added: %s\n", response);
                } else {
                    System.out.println("Your current list is full! Unable to add any more items!");
                }
            } else {
                String toPrint = "";
                for (int i = 0; i < currentCapacity; i++) {
                    toPrint += String.format("%d. %s\n", i + 1, userItemList[i]);
                }
                System.out.print(toPrint);
            }
        }

    }
}
