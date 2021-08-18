import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Dino dino = new Dino();
        dino.greeting();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                dino.printTaskList();
            } else {
                String type = Tool.getFirstWord(input);
                if (type.equals("done")) {
                    int index = Tool.getDoneIndex(input);
                    if (index > 0) dino.markTaskDone(index);
                    else dino.addTask(input);
                } else {
                    dino.addTask(input);
                }
            }
        }
        sc.close();
        dino.farewell();
    }

}
