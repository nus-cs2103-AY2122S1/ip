package model.vocab;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for dealing with a list of tasks object or its subclasses alternatives.
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

    /** ArrayList containing the tasks */
    private ArrayList<Vocab> vocabs =
            new ArrayList<>(DEFAULT_PHRASE.stream().map(command -> Vocab.of(command, "ALICE_DEFAULT_VOCAB")).collect(Collectors.toList()));
    ;

    public VocabList() {

    }

    public static boolean isDefaultPhrase(String phrase) {
        return DEFAULT_PHRASE.contains(phrase);
    }


    public VocabList(ArrayList<Vocab> vocabs) {
        this.vocabs.addAll(vocabs);
    }

    /**
     * Add the models.task to the taskList
     *
     * @param vocab the models.task to be added.
     */
    public void add(Vocab vocab) {
        vocabs.add(vocab);
    }

    /**
     * Remove the models.task at given index
     *
     * @param index index of the models.task to be removed
     */
    public void remove(int index) {
        vocabs.remove(index);
    }

    /**
     * Return the models.task at specific index.
     *
     * @param index index of the models.task
     * @return the models.task at that index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public Vocab get(int index) throws IndexOutOfBoundsException {
        return vocabs.get(index);
    }

    public String getFeedBack(String phrase) {
        return vocabs.stream().filter(vocab -> vocab.getPhrase().equals(phrase)).findFirst().get().getFeedback();
    }

    public void removePhrase(String phrase) {
        if (!containsPhrase(phrase)) {
            return;
        }

        vocabs = new ArrayList<>(vocabs.stream()
                .filter(vocab -> !vocab.getPhrase().equals(phrase)).collect(Collectors.toList()));
    }

    public boolean containsPhrase(String phrase) {
        return vocabs.stream().map(Vocab::getPhrase).anyMatch(p -> p.equals(phrase));
    }


    /**
     * Getter for the array list of tasks
     *
     * @return ArrayList of Task objects or its alternatives
     */
    public ArrayList<Vocab> getVocabs() {
        return this.vocabs;
    }

    /**
     * Getter for length of the list of tasks.
     *
     * @return the length of the tasks
     */
    public int length() {
        return vocabs.size();
    }

    /**
     * A list representation of the tasks numbered from 1 to the last models.task in the list
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
