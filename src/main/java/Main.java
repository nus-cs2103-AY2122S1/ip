import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        levelTwo();
    }

    public static void levelOne() {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        duke.greet();
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            duke.echo(command);
            command = sc.nextLine();
        }
        sc.close();
        duke.bye();
    }

    public static void levelTwo() {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        duke.greet();
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            if (!command.equals("list")) {
                duke.addTask(command);
            } else {
                duke.displayTasks();
            }
            command = sc.nextLine();
        }
        sc.close();
        duke.bye();
    }

}