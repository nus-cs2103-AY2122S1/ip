package duke;
import java.util.Scanner;

class Parser {
//     Scanner input = new Scanner(System.in);
   Ui ui;
   Storage storage;
   TaskList tasklist;
   Scanner myScanner;
   boolean breakLoop = false;


     Parser(Scanner myScanner) {
          this.storage = new Storage("Data/DukeData.txt");
          this.myScanner = myScanner;
          this.tasklist = Storage.tasklist;
          try {
               Storage.readFile("Data/DukeData.txt");
          } catch (Exception e) {
               System.out.println(e);
          }
          this.ui = new Ui(tasklist);
     }

     public boolean getBreak(){
          return breakLoop;
     }

     public void breakOutLoop(){
          breakLoop = true;
     }
    /**
     * Calls appropriate UI functions based on input
     * @param input String passed by user
     *
     */
     public void parse(String input) {
          if (input.equals("bye")) {
               ui.byeResponse();
               storage.saveTasks(tasklist);
               breakOutLoop();
          }
          else if (input.contains("list")) {
               ui.listResponse();
          }
          else if (input.contains("done")) {
               ui.doneResponse(input);
          }
          else if (input.contains("delete")) {
               ui.deleteResponse(input);
          }
          else if (input.contains("find")) {
              ui.findResponse(input);
          }
          else if (input.contains("todo")) {
               ui.todoResponse(input);
          }
          else if (input.contains("deadline")) {
               ui.deadlineResponse(input);
          }
          else if (input.contains("event")) {
               ui.eventResponse(input);
          }
          else {
               ui.invalidInput();
          }

     }
}
