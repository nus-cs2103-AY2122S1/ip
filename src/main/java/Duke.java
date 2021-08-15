import java.util.*;
public class Duke {
    List<String> commands = new ArrayList<>();

    void list(){
        for(int i = 0; i < commands.size(); i++){
            System.out.println((i+1) + ". " + commands.get(i));
        }
    }
    void add(String command){
        commands.add(command);
        System.out.println("Added task: " + command);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
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
            } else if(command.equalsIgnoreCase("list")){
                duke.list();
            }
            else {
                duke.add(command);
            }
            System.out.println("____________________________________________________________");
        }
        sc.close();
    }
}
