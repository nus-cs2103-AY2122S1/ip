import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {

    private static List<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printFormatted("Hola! I'm Blitz :) \n\tWhat can I do for you?");
        String command = scanner.nextLine();
        while(!command.equals("bye")) {
            if(command.equals("list")) {
                printList();
            } else {
                tasks.add(command);
                printFormatted("added: " + command);
            }
            command = scanner.nextLine();
        }
        printFormatted("Adiós. Hope to see you again soon!");
    }

    public static void printFormatted(String str) {
        System.out.print('\t');
        for(int i = 0; i < 40; i++) {
            System.out.print('_');
        }
        System.out.println("\n \t" + str);
        System.out.print('\t');
        for(int i = 0; i < 40; i++) {
            System.out.print('_');
        }
        System.out.println('\n');
    }

    public static void printList() {
        System.out.print('\t');
        for(int i = 0; i < 40; i++) {
            System.out.print('_');
        }
        System.out.print('\n');
        int ctr = 1;
        for(String task: tasks) {
            System.out.println("\t" + ctr + ". " + task);
            ctr++;
        }
        System.out.print('\t');
        for(int i = 0; i < 40; i++) {
            System.out.print('_');
        }
        System.out.println('\n');
    }
}
