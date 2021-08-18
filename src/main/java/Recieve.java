import java.util.Scanner;

public class Recieve {
    private static int index = 1;
    private String input;
    private static String[] inputs = new String[100];


    public Recieve() {
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            input = sc.nextLine();
            if (this.input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (this.input.equals("list")) {
                for(int i = 1; i < index; i++) {
                    System.out.println(i + ". " + inputs[i - 1]);
                }
            } else {
                inputs[index - 1] = this.input;
                index++;
                System.out.println("added: " + this.input);
            }
        }
        sc.close();
    }



}
