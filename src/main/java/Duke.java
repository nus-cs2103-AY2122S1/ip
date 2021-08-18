import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] list = new String[100];
        int counter = 0;

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String input = scanner.nextLine();
        while(!input.equals("bye")){
            if(input.equals("list")){
                for(int i = 0; i < list.length;i++){
                    if(list[i] == null){
                        break;
                    }else {
                        System.out.println((i + 1) + ". " + list[i]);
                    }
                }
                input = scanner.nextLine();
            }else {
                System.out.println("added: " + input);
                list[counter] = input;
                counter++;
                input = scanner.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
