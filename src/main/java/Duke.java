import java.util.Scanner;

public class Duke {
    private static final String horizontalBars = "\n____________________________________________________________\n";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = "Duchess";
        PrettyPrint("Good day. I am " + name + "\nWhat can I do for you?");
        String output = sc.nextLine();
        while (!output.equals("bye"))
        {
            PrettyPrint(output);
            output = sc.nextLine();
        }
        PrettyPrint("I bid thee farewell.");
    }
    public static void PrettyPrint(String input)
    {
        System.out.println(horizontalBars + input + horizontalBars);
    }


}
