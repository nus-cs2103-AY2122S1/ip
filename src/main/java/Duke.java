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
        System.out.println(logo + divHead + "⚜  Hello, i'm Ai-su! How may I help you today?  ⚜" + divTail);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(divHead + ' ' + input + divTail);
            input = sc.nextLine();
        }
        System.out.println(divHead + " See you next time!" + divTail);
    }
}
