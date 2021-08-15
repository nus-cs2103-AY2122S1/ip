import java.util.Scanner;

public class Duke {
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static String greetings = "Greetings! I'm Duke\n\tWhat can I do for you?";
    private static String bye = "Godspeed young padawan!";
    private static String hline = "\t----------------------------";
    private void print(String s) {
        System.out.println(hline);
        System.out.println("\t" + s);
        System.out.println("\n" + hline);
    }
    private void print_logo() {
        System.out.println("Hello from\n" + this.logo);

    }


    public void run() {
        Scanner sc = new Scanner(System.in);
        //initialising Duke
        this.print_logo();
        this.print(greetings);
        String inpt = sc.nextLine();
        while(!inpt.equals("bye")) {
            print(inpt);
            inpt = sc.nextLine();
        }
        this.print(bye);
    }



    public static void main(String[] args) {
        Duke d = new Duke();
        d.run();



    }
}
