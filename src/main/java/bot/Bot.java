package main.java.bot;

import main.java.command.Command;

public class Bot {

     private TaskList tasks;
     private UserInterface ui;
     private static boolean isRunning;

     public Bot() {

         ui = new UserInterface();

         try {
            tasks = Storage.load();
         } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
         }
     }

     public void start() {

         ui.showWelcomeMessage();
         isRunning = false;

         while (!isRunning) {
             try {
                 String fullCommand = ui.readCommand();
                 Command c = Parser.parse(fullCommand);
                 c.execute(tasks, ui);
             } catch (DukeException e) {
                 ui.showError(e.getMessage());
             }
         }
     }

     public static void stop() {
         isRunning = true;
     }
}