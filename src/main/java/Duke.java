import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasklist;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasklist = new TaskList(storage.loadData());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void run() throws InputNotValidError, IOException{
        System.out.println(this.ui.great());
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("");
            String action = input.nextLine();
            Parser commandpraser = new Parser(action);
            if(commandpraser.getAction() == Action.BYE){
                System.out.println(this.ui.byeSent());
                this.storage.saveData(this.tasklist.returnTaskList());
                break;
            }else{
                tasklist.actionHalder(commandpraser, false, false);
            }
        }
        input.close();
    }    

    public static void main(String[] args) throws InputNotValidError, IOException {
        new Duke("data.txt").run();
    }

}
