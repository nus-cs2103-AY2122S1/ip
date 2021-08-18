import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you? :)");
        Scanner sc = new Scanner(System.in); //initialize scanner
        String inpt = sc.nextLine(); //scanning user's first input
        ArrayList<String> lst = new ArrayList<>(); // initialize array list

        // printing user's input & adding it to an array list, loops until input is "bye"
        while(!inpt.equals("bye")){
            if(inpt.equals("list")){
                lst.forEach(x -> System.out.println((lst.indexOf(x) + 1) +". " + x ));
            } else {
                System.out.println("added: " + inpt);
                lst.add(inpt);
            }
            inpt = sc.nextLine();
        }
        System.out.println("Goodbye, I will miss you!");
    }
}
