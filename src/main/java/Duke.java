import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        ArrayList<String> storage = new ArrayList<>();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        String input = Sc.nextLine();

        while(true){
            if(input.equals("bye")){break;}

            switch (input){
                case "list":
                    for (int i = 0; i < storage.size(); i++) {
                        System.out.println(storage.get(i));
                    }
                    break;
                default:
                    storage.add(input);
                    System.out.println("added: " + input);

            }
            input = Sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        Sc.close();
    }
}
