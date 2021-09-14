package duke.command;

import duke.Ui;
import duke.Storage;
import duke.notes.NotesList;
import duke.tasks.TaskList;
import duke.notes.Note;

public class AddNoteCommand extends Command {
    String command;
    public AddNoteCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "";
    }

    @Override
    public String execute(NotesList notes, Ui ui, Storage storage) {
        String typeOfCommand = command.split(" ")[0];
        assert typeOfCommand != "note";
        String[] parsedCommand = command.split(" ", 2);
        Integer lengthOfCommand = parsedCommand.length;
        if(lengthOfCommand == 1) {
            System.out.println("incomplete note!");
        } else {
            String noteDescription = command.split(" ", 2)[1];
            Note note = new Note(noteDescription);
            notes.addNote(note);
            storage.appendToFile(note);
            return ui.respondToNote(notes.getNotes(), note);
        }
        return "";
    }

    @Override
    public Boolean isTaskRelatedCommand() {
        return false;
    };

    @Override
    public Boolean isExit() {
        return false;
    }

}
