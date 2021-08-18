import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] list = new Task[100];

        int counter = 0;

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String input = scanner.nextLine();
        Scanner inputScanner = new Scanner(input);
        String checkForKeyword = inputScanner.next();

        while(!input.equals("bye")){
            if(input.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < list.length;i++){
                    if(list[i] == null){
                        break;
                    }else {
                        System.out.println((i + 1) + "." + list[i]);
                    }
                }
            }else if(checkForKeyword.equals("done")){
                try {
                    int taskToComplete = inputScanner.nextInt() - 1;
                    list[taskToComplete].markComplete();
                    System.out.println("Nice! I've marked this task as done:\n " + list[taskToComplete]);

                }catch(Exception e){
                    System.out.println("woops");
                }
            }
            else if(checkForKeyword.equals("todo")){
                String secondWord = inputScanner.nextLine();
                list[counter] = new Todo(secondWord);
                counter++;
                System.out.println("Got it. I've added this task: \n" + list[counter - 1] + "\nNow you have " + counter + " tasks in the list.");
            }
            else if(checkForKeyword.equals("deadline")){
                String[] contentAndDate = inputScanner.nextLine().split("/by",2);
                String content = contentAndDate[0];
                String date = contentAndDate[1];
                list[counter] = new Deadline(content, date);
                counter++;
                System.out.println("Got it. I've added this task: \n" + list[counter - 1] + "\nNow you have " + counter + " tasks in the list.");
            }
            else if(checkForKeyword.equals("event")){
                String[] contentAndDate = inputScanner.nextLine().split("/at",2);
                String content = contentAndDate[0];
                String date = contentAndDate[1];
                list[counter] = new Event(content, date);
                counter++;
                System.out.println("Got it. I've added this task: \n" + list[counter - 1] + "\nNow you have " + counter + " tasks in the list.");
            }
            else{
                if(counter == 100){
                    System.out.println("Max limit of list hit");
                    break;

                }else {
                    System.out.println("added: " + input);
                    list[counter] = new Task(input);
                    counter++;
                }
            }
            input = scanner.nextLine();
            inputScanner = new Scanner(input);
            checkForKeyword = inputScanner.next();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
