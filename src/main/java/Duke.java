import java.util.Scanner;
public class Duke {

    public static void getInput(Scanner scanner)
    {
        String input = scanner.next();
        if(input.equals("bye"))
        {
            System.out.println("  ---------------------------------------------");
            System.out.println("    Bye. Hope to see you again soon!");
            System.out.println("  ---------------------------------------------");
            scanner.close();
        } else
        {
            System.out.println("  ---------------------------------------------");
            System.out.println("    " + input);
            System.out.println("  ---------------------------------------------");
            getInput(scanner);
        }
    }

    public static void main(String[] args) {
        System.out.println("  ---------------------------------------------");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("  ---------------------------------------------");

        Scanner scanner = new Scanner(System.in);
        getInput(scanner);

    }
}
