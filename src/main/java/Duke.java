import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner userSc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String name = "JARVIS";
        System.out.println("Hello I am " + name +"\nIs there anything I can do for you Sir?\n");

        String userInput = userSc.nextLine();
        while(!userInput.equals("bye")){
            echo(userInput);
            userInput = userSc.nextLine();
        }
        System.out.println("Goodbye Sir! Will take good care of your plants in the meantime.");
    }

    public static void echo(String userInput){
        System.out.println(userInput);
    }


}
