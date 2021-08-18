import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] list = new Task[100];

        int counter = 0;

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String input = scanner.nextLine();
        Scanner checkForKeyword = new Scanner(input);

        while(!input.equals("bye")){
            if(input.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < list.length;i++){
                    if(list[i] == null){
                        break;
                    }else {
                        String cross = list[i].isComplete()?"X":" ";
                        System.out.println((i + 1) + "." + list[i]);
                    }
                }
            }else if(checkForKeyword.next().equals("done")){
                try {
                    int taskToComplete = checkForKeyword.nextInt() - 1;
                    list[taskToComplete].markComplete();
                    System.out.println("Nice! I've marked this task as done:\n " + list[taskToComplete]);

                }catch(Exception e){
                    System.out.println(e);
                }
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
            checkForKeyword = new Scanner(input);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
