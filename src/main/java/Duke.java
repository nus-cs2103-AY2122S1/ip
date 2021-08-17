import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        String input = Sc.nextLine();
        while(!input.equals("bye")){
            System.out.println(input);
            input = Sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        Sc.close();
    }
}
