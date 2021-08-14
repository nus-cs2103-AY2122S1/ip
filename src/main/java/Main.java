import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        levelThree();

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

//    public static void levelTwo() {
//        Duke duke = new Duke();
//        Scanner sc = new Scanner(System.in);
//        duke.greet();
//        String command = sc.nextLine();
//        while (!command.equals("bye")) {
//            if (!command.equals("list")) {
//                duke.addTask(command);
//            } else {
//                duke.displayTasks();
//            }
//            command = sc.nextLine();
//        }
//        sc.close();
//        duke.bye();
//    }

    public static void levelThree() {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        duke.greet();
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            if (!command.equals("list")) {
                String[] commandKeywords = command.split(" ");
                if (commandKeywords.length == 2) {
                    if (commandKeywords[0].equals("done")) {
                        try {
                            int taskIdx = Integer.parseInt(commandKeywords[1]);
                            if (taskIdx <= duke.getNumberOfTasks() && taskIdx > 0) {
                                duke.markTaskAsDone(taskIdx - 1);
                            } else {
                                duke.noTaskExist();
                            }
                        } catch (NumberFormatException ignored) {
                            Task task = new Task(command);
                            duke.addTask(task);
                        }
                    } else {
                        Task task = new Task(command);
                        duke.addTask(task);
                    }
                } else {
                    Task task = new Task(command);
                    duke.addTask(task);
                }
            } else {
                duke.displayTasks();
            }
            command = sc.nextLine();
        }
        sc.close();
        duke.bye();
    }

}