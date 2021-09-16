package model.vocab;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.vocab.exceptions.UnrecognisedPhraseException;

/**
 * Class for dealing with a list of vocab object or its subclasses alternatives.
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.03
 * @since 0.00
 */
public class VocabList {


    /** to be added as more commands are introduced */
    public static final List<String> DEFAULT_PHRASE = List.of("list", "date", "find", "todo", "deadline", "event",
        "done", "delete", "learn", "unlearn", "commands", "?", "help", "bye");

    /** ArrayList containing the vocabs */
    private ArrayList<Vocab> vocabs =
        new ArrayList<>(DEFAULT_PHRASE.stream().map(command
            -> Vocab.of(command, "ALICE_DEFAULT_VOCAB")).collect(Collectors.toList()));

    /**
     * Empty constructor populated with all the default commands.
     */
    public VocabList() {
        // vocabs already have its array list populated with the vocabs.
    }

    /**
     * Add the vocabs on top of the default phrase from the array list of vocab objects.
     *
     * @param vocabs array list of vocabs to be added.
     */
    public VocabList(ArrayList<Vocab> vocabs) {
        this.vocabs.addAll(vocabs);
    }

    /**
     * Check whether the phrase parsed in is a default phrase/ command or not.
     *
     * @param phrase phrase to be recognised as a new command.
     * @return whether the specified phrase is already used as a default phrase or not.
     */
    public static boolean isDefaultPhrase(String phrase) {
        return DEFAULT_PHRASE.contains(phrase);
    }


    /**
     * Add the vocab to the vocab list.
     *
     * @param vocab the vocab to be added to this list.
     */
    public void add(Vocab vocab) {
        vocabs.add(vocab);
    }

    /**
     * Remove the vocab at given index.
     *
     * @param index index of the vocab to be removed from this list.
     */
    public void remove(int index) {
        vocabs.remove(index);
    }

    /**
     * Return the vocab at specific index.
     *
     * @param index index of the vocab.
     * @return the vocab at that index.
     * @throws IndexOutOfBoundsException if the index is invalid.
     */
    public Vocab get(int index) throws IndexOutOfBoundsException {
        return vocabs.get(index);
    }

    /**
     * Getter for feedback from the specified phrase in this current list.
     *
     * @param phrase the phrase to be used to fetch feedback.
     * @return the feedback from the phrase in this vocab list.
     * @throws UnrecognisedPhraseException if the phrase is not contained in this list.
     */
    public String getFeedBack(String phrase) throws UnrecognisedPhraseException {
        if (!containsPhrase(phrase)) {
            throw new UnrecognisedPhraseException(phrase + "");
        }
        return vocabs.stream().filter(vocab
            -> vocab.getPhrase().equals(phrase)).findFirst().orElseThrow().getFeedback();
    }

    /**
     * Remove the given phrase from this vocab list.
     *
     * @param phrase phrase to be removed from this list.
     */
    public void removePhrase(String phrase) {
        if (!containsPhrase(phrase)) {
            return;
        }

        vocabs = new ArrayList<>(vocabs.stream()
            .filter(vocab -> !vocab.getPhrase().equals(phrase)).collect(Collectors.toList()));
    }

    /**
     * Check whether if the given phrase is contained in this vocab list or not.
     *
     * @param phrase phrase to be checked if it exist in this list.
     * @return whether the given phrase exist in this list as a vocab.
     */
    public boolean containsPhrase(String phrase) {
        return vocabs.stream().map(Vocab::getPhrase).anyMatch(p -> p.equals(phrase));
    }


    /**
     * Getter for the array list of vocabs.
     *
     * @return ArrayList of vocab objects or its alternatives.
     */
    public ArrayList<Vocab> getVocabs() {
        return this.vocabs;
    }

    /**
     * A list representation of the vocabs numbered from 1 to the last models.task in the list
     *
     * @return string representation of the list of tasks
     */
    @Override
    public String toString() {
        StringBuilder vocabDialog = new StringBuilder();
        for (int i = 0; i < vocabs.size(); i++) {
            vocabDialog.append("    ").append(i + 1).append(".").append(vocabs.get(i).toString()).append("\n");
        }
        return vocabDialog.toString();
    }
}
