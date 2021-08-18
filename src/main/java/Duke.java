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
//                System.out.println(input);
                //String secondWord = input.substring(input.indexOf(" "));
                //System.out.println(secondWord);
            }
//            else if(checkForKeyword.next().equals("deadline")){
//
//            }
//            else if(checkForKeyword.next().equals("event")){
//
//            }
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
