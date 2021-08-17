import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        while(true){
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();
            if(command.equals("bye")){
                System.out.println("Bye have a good time\n");
                break;
            }else {
                System.out.println(command + "\n");
            }
        }
    }


}
