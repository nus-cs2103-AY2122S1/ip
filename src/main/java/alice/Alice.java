package alice;

import alice.exceptions.AliceException;
import command.Command;
import model.vocab.Vocab;
import model.vocab.VocabList;
import parser.Parser;
import storage.Storage;
import storage.VocabularyStorage;
import ui.ChatPage;
import ui.Ui;

import java.io.IOException;

/**
 * Main class of the chatbot.
 * By running the main method the bot will first ask for the save file and asking if
 * the user want to create a new save file location before proceeding.
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.03
 * @since 0.02
 */
public class Alice {
    /** storage for alice.Alice */
    private final Storage storage;
    private VocabList vocabList;

    /** ui of alice.Alice interacting with the user from inputting command and showing the ui back to the user */
    private final Ui ui;

    private String phraseToLearn = "";

    public Alice() throws IOException {
        this("test");
    }

    /**
     * Constructor of alice.Alice.
     *
     * @param fileName the filename without the suffix .txt, .TXT, etc.
     * @throws IOException if there is issue loading taskList from storage
     */
    public Alice(String fileName) throws IOException {
        ui = new Ui();
        storage = new Storage(fileName);
        // import the models.task from what the storage manage to load
        ui.setTaskList(storage.loadTaskList());
        // set the current taskDialog of alice.Alice to the one ui fetch from the storage
        vocabList = new VocabularyStorage().loadVocabList();

    }

    /**
     * Getter for Ui of Alice
     *
     * @return Alice
     */
    public Ui getUi() {
        return this.ui;
    }


    /**
     * Execute the fullCommand.
     * Execute the fullCommand by parsing the command
     * and executing it without caring if it is exit or not.
     *
     * @param fullCommand the command as string including taggers and date
     */
    public void execute(String fullCommand) {
        try {

            if (VocabList.isDefaultPhrase(fullCommand.split(" ")[0])) {
                // check first if it is a default command
                Command c = Parser.parse(fullCommand);
                c.execute(ui, storage);
            } else if (vocabList.containsPhrase(fullCommand)) {
                // check if Alice has learned this phrase before
                ui.getChatPage().printWithAlice(vocabList.getFeedBack(fullCommand));
            } else {
                // else try executing the command
                Command c = Parser.parse(fullCommand);
                c.execute(ui, storage);
            }

        } catch (AliceException e) {
            ui.getChatPage().printError(e);
        }
    }

    public VocabList getVocabList() {
        return vocabList;
    }

    public void setPhraseToLearn(String phraseToLearn) {
        this.phraseToLearn = phraseToLearn;
    }

    public void learn(String fullFeedback) {
        Vocab vocabToLearn = Vocab.of(this.phraseToLearn, fullFeedback);
        vocabList.add(vocabToLearn);
        ui.getChatPage().setMode(ChatPage.Mode.DEFAULT);
        ui.getChatPage().printWithAlice("Got it! Alice will remember that.");
    }

    public void saveCurrentVocabulary() {
        try {
            VocabularyStorage vocabularyStorage = new VocabularyStorage();
            vocabularyStorage.save(vocabList);
        } catch (IOException e) {
            ui.getChatPage().printError(e);
        }
    }

}
