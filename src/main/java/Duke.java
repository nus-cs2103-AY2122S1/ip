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
    //hashmap cannot enumerate
    //array of inputs capped at 100
    private String[] inputs =  new String[100];
    //pointer to the last location of inputs available
    private int ptr = 0;

    /**
     * Dukes own method to print a string
     * in his own special way. (only single line inputs though, multi line requires tabs)
     *
     * @param s The string to be printed.
     */
    private void print(String s) {
        System.out.println(hline);
        System.out.println("\t" + s);
        System.out.println("\n" + hline);
    }

    /**
     * Method to print the Logo for Duke.
     */
    private void print_logo() {
        System.out.println("Hello from\n" + this.logo);

    }

    /**
     * Checks if the input string has been added before or not.
     *
     * @param s The string to check.
     * @return True if the string has been added and false otherwise.
     */
    private boolean isAdded(String s) {
        for (int i = 0; i < ptr; i++) {
            if (s.equals(inputs[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds the input String to the list.
     *
     * @param s Input string.
     */
    private void add(String s) {
        this.inputs[ptr] = s;
        this.ptr++;
        this.print(s);
    }

    /**
     * Prints all the strings added.
     */
    private void list() {
        String output = "";
        if (ptr == 0) return;
        output += "1. " + inputs[0];
        for (int i = 1; i < ptr; i++) {
            int indi = i + 1;
            output += "\n\t" + indi + ". " + inputs[i];
        }
        this.print(output);
    }

    /**
     * Running Duke.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        //initialising Duke
        //via greetings
        this.print_logo();
        this.print(greetings);
        String inpt = sc.nextLine();
        while(!inpt.equals("bye")) {


            if (inpt.equals("list")) {
                this.list();
                inpt = sc.nextLine();
                continue;
            }

            //checks if the input has been added
            //if the input has been added print added: {input}
            //else if the input has not been added add it and print
            //the input
            if (this.isAdded(inpt)) {
                this.print("added: " + inpt);
            } else {
                this.add(inpt);
            }
            inpt = sc.nextLine();
        }
        this.print(bye);
    }



    public static void main(String[] args) {
        Duke d = new Duke();
        d.run();



    }
}
