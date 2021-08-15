import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = "   _       _           \n"
                + "  /_\\  ___| |__  _   _ \n"
                + " //_\\\\/ __| '_ \\| | | |\n"
                + "/  _  \\__ \\ | | | |_| |\n"
                + "\\_/ \\_/___/_| |_|\\__, |\n"
                + "                 |___/ \n";

        //Greeting message
        System.out.println("Hello! I'm\n" +logo+ "\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        //Echo
        while(sc.hasNext()){
            //Exit command
            System.out.println("............................................................");
            String command = sc.nextLine().trim();
            if(command.equalsIgnoreCase("bye")){
                System.out.println("\n\tByeBye! Hope to see you again!");
                break;
            }else {
                System.out.println("\n\t"+command);
            }
            System.out.println("____________________________________________________________");
        }
        sc.close();
    }
}
