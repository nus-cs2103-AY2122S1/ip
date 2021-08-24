import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e){
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void OperationForDuke(String taskType, String task, String time, int index) {
        String date = "";
        switch (taskType) {
            case "bye": {
                ui.GoodbyeMessage();
                break;}
            case "list": {
                ui.PrintList(tasks);
                break;
            }
            case "done": {
                try {
                    tasks.MarkDone(index);
                    ui.MarkDone(tasks.get(index).PrintTaskInfo());
                } catch (DukeException e){
                    e.PrintErrorMessage();
                }
                break;
            }
            case "delete":{
                try {
                    tasks.Delete(index);
                    ui.Delete(tasks.get(index).PrintTaskInfo(), tasks.size());
                } catch (DukeException e){
                    e.PrintErrorMessage();
                }
                break;
            }
/*            case "tell": {
                date = (Message.contains(" "))? Message.substring(Message.indexOf(" ") + 1)
                        :"nope";
                SpecificDateEvent(date);
                break;
            }*/
            default:{
                try {
                    tasks.add(taskType, task, time);
                    ui.add(tasks.get(tasks.size() -1).PrintTaskInfo(), tasks.size());
                } catch (DukeException e)
                {
                    e.PrintErrorMessage();
                }
                break;
            }
        }
    }

    public void UpdateSaveData() {
        try {
            storage.SaveListDataToFile(tasks);
        } catch (IOException e) {
            ui.showSavingError();
        }
    }

    public void run() {
        //Say Hello to the User
        ArrayList<String> Messages = new ArrayList<>();
        String taskType = "";
        String task = "";
        String time = "";
        int index = 0;
        ui.HelloMessage();

        while (true) {
            try {
                Messages = ui.ARoundOfInput();
            } catch (DukeException e) {
                ui.PrintAline();
                e.PrintErrorMessage();
                ui.PrintAline();
                continue;
            }

            if (Messages.size() < 4) {
                continue;
            }

            taskType = Messages.get(0);
            task = Messages.get(1);
            time = Messages.get(2);
            index = Integer.parseInt(Messages.get(3));

            ui.PrintAline();
            OperationForDuke(taskType, task, time, index);
            ui.PrintAline();

            UpdateSaveData(); //Update the SaveData every time a round of operation is done.
            if (taskType.equals("bye")) {
                break;
            }
        }


    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
