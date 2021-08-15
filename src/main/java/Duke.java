import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " █████╗  ██╗      ███████╗██╗   ██╗ \n" +
                      " ██╔══██╗██║      ██╔════╝██║   ██║ \n" +
                      " ███████║██║█████╗███████╗██║   ██║ \n" +
                      " ██╔══██║██║╚════╝╚════██║██║   ██║ \n" +
                      " ██║  ██║██║      ███████║╚██████╔╝ \n" +
                      " ╚═╝  ╚═╝╚═╝      ╚══════╝ ╚═════╝ \n";

        String divHead = "╔═══════════*.·:·.☽✧    ✦    ✧☾.·:·.*═══════════╗\n";
        String divTail = "\n╚═══════════*.·:·.☽✧    ✦    ✧☾.·:·.*═══════════╝\n";

        String[] list = new String[100];
        int numItems = 0;

        System.out.println(logo + divHead + "⚜  Hello, i'm Ai-su! How may I help you today?  ⚜" + divTail);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(divHead);
                if (numItems == 0) {
                    System.out.println("There's nothing added to the list yet!");
                } else {
                    for (int i = 0; i < numItems; i++) {
                        System.out.println(i + 1 + ". " + list[i]);
                    }
                }
                System.out.println(divTail);
            } else {
                list[numItems] = input;
                numItems += 1;
                System.out.println(divHead + ' ' + "added: " + input + divTail);
            }
            input = sc.nextLine();
        }
        System.out.println(divHead + " See you next time!" + divTail);
    }
}
