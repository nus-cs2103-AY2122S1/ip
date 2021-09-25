package alice;

import java.io.IOException;

import alice.exceptions.AliceException;
import command.Command;
import model.vocab.Vocab;
import model.vocab.VocabList;
import model.vocab.exceptions.UnrecognisedPhraseException;
import parser.Parser;
import storage.Storage;
import storage.VocabularyStorage;
import ui.ChatPage;
import ui.Ui;

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
    /** storage for Alice */
    private final Storage storage;
    /** vocab list that Alice to recognize custom command */
    private VocabList vocabList;

    /** ui of Alice interacting with the user from inputting command and showing the ui back to the user */
    private final Ui ui;

    private String phraseToLearn = "";

    public Alice() throws IOException {
        this("test");
    }

    /**
     * Constructor of Alice.
     * Alice will instantiate a new ui and storage object given the filename and import
     * task list to the ui and also load the vocab list.
     *
     * @param fileName the filename without the suffix .txt, .TXT, etc.
     * @throws IOException if there is issue loading taskList from storage or vocab list from vocabulary storage.
     */
    public Alice(String fileName) throws IOException {
        ui = new Ui();
        storage = new Storage(fileName);
        // import the task from what the storage manage to load
        ui.importTaskList(storage.loadTaskList());
        // instantiate the vocabulary storage
        vocabList = new VocabularyStorage().loadVocabList();

    }

    /**
     * Getter for Ui of Alice.
     *
     * @return ui of this Alice.
     */
    public Ui getUi() {
        return this.ui;
    }


    /**
     * Execute the fullCommand by parsing the command and executing accordingly.
     *
     * @param fullCommand the command as string including taggers and date or other custom phrase that user has taught
     *                    Alice before.
     */
    public void execute(String fullCommand) {
        try {

            if (VocabList.isDefaultPhrase(fullCommand.split(" ")[0])) {
                // check first if it is a default command
                Command c = Parser.parse(fullCommand);
                c.execute(ui, storage);
            } else if (vocabList.containsPhrase(fullCommand)) {
                // check if Alice has learned this phrase before and print the feedback accordingly
                ui.getChatPage().printWithAlice(vocabList.getFeedBack(fullCommand));
            } else {
                // else try executing the command
                Command c = Parser.parse(fullCommand);
                c.execute(ui, storage);
            }

        } catch (AliceException | UnrecognisedPhraseException e) {
            ui.getChatPage().printError(e);
        }
    }

    /**
     * Getter for vocabulary list of this Alice.
     *
     * @return the vocabulary current Alice has.
     */
    public VocabList getVocabList() {
        return vocabList;
    }

    /**
     * Setter for phrase that Alice wants to learn.
     * Alice will keep the phrase with her before executing further command.
     *
     * @param phraseToLearn the phrase that Alice is tasked to learn.
     */
    public void setPhraseToLearn(String phraseToLearn) {
        this.phraseToLearn = phraseToLearn;
    }

    /**
     * Alice will learn the current phrase currently stored within her and learn to give the given feedback
     * in the argument.
     *
     * @param fullFeedback the feedback to be returned when the user use the given phrase as custom command.
     */
    public void learn(String fullFeedback) {
        Vocab vocabToLearn = Vocab.of(this.phraseToLearn, fullFeedback);
        vocabList.add(vocabToLearn);
        ui.getChatPage().setMode(ChatPage.Mode.DEFAULT);
        ui.getChatPage().printWithAlice("Got it! Alice will remember that.");
    }

    /**
     * Alice will save the current/updated vocabulary according to vocabulary storage file location.
     */
    public void saveCurrentVocabulary() {
        try {
            VocabularyStorage vocabularyStorage = new VocabularyStorage();
            vocabularyStorage.save(vocabList);
        } catch (IOException e) {
            ui.getChatPage().printError(e);
        }
    }

}
