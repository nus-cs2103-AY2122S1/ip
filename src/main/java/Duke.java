import java.util.Scanner;

public class Duke {
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";




    private static final String taskComplete = "Nice, I've marked this task as done";
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String greetings = "Greetings! I'm Duke\n\tWhat can I do for you?";
    private static final String bye = "Godspeed young padawan!";
    private static final String hline = "\t----------------------------";
    //hashmap cannot enumerate
    //array of inputs capped at 100
    private Task[] inputs =  new Task[100];
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
     * Checks if the input task has been added before or not.
     *
     * @param t The Task to check.
     * @return True if the string has been added and false otherwise.
     */
    private boolean isAdded(String t) {
        for (int i = 0; i < ptr; i++) {
            if (inputs[i].equals(t)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds the input task to the list.
     *
     * @param t Input task
     */
    private void add(Task t) {
        this.inputs[ptr] = t;
        this.ptr++;
        this.print("Got it, I've added this task\n\t  " + t.toString() +
                "\n\tNow you have " + ptr + " tasks in the list.");

    }

    /**
     * Prints all the strings added.
     */
    private void list() {
        String output = "";
        if (ptr == 0) return;
        output += "1." + inputs[0];
        for (int i = 1; i < ptr; i++) {
            int indi = i + 1;
            output += "\n\t" + indi + "." + inputs[i];
        }
        this.print(output);
    }

    /**
     * Creates a todo task with the ipt String
     *
     * @param ipt
     */
    private void todo(String ipt) {
        Task t = new ToDos(ipt);
        this.add(t);

    }

    /**
     * Creates a deadLine task with the input string
     *
     * @param input
     */
    private void deadline(String input) {
        String[] arr = input.split("/by", 2);
        Task t = new Deadlines(arr[0], arr[1]);
        this.add(t);

    }

    private void event(String input) {
        String[] arr = input.split("/at", 2);
        Task t = new Events(arr[0], arr[1]);
        this.add(t);
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
            //split the inpt to two if possible
            String[] twoInputs = inpt.split(" ", 2);


            if (twoInputs[0].equals("done")) {
                //TODO: should check if there is an int?
                String s = twoInputs[1].trim();
                int val = Integer.parseInt(s) - 1;
                inputs[val].done();
                print(taskComplete + "\n\t" + inputs[val]);
                inpt = sc.nextLine();
                continue;
            } else if (twoInputs[0].equals(TODO)) {
                todo(twoInputs[1]);
                inpt = sc.nextLine();
                continue;
            } else if (twoInputs[0].equals(EVENT)) {
                event(twoInputs[1]);
                inpt = sc.nextLine();
                continue;
            } else if (twoInputs[0].equals(DEADLINE)) {
                deadline(twoInputs[1]);
                inpt = sc.nextLine();
                continue;
            }



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
                this.add(new Task(inpt));
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
