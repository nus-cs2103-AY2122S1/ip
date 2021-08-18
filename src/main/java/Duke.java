import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] list = new Task[100];

        int counter = 0;

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String input = scanner.nextLine();
        int test = scanner.nextInt();
        System.out.println(input + test);

        while(!input.equals("bye")){
            if(input.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < list.length;i++){
                    if(list[i] == null){
                        break;
                    }else {
                        String cross = list[i].isComplete()?"X":" ";
                        System.out.println((i + 1) + ".[" + cross + "] " + list[i]);
                    }
                }
                input = scanner.nextLine();
            }else if(input.equals("done")){

            }
            else{
                if(counter == 100){
                    System.out.println("Max limit of list hit");
                    break;

                }else {
                    System.out.println("added: " + input);
                    list[counter] = new Task(input);
                    counter++;
                    input = scanner.nextLine();
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
