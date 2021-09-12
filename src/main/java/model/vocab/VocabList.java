package model.vocab;

import java.util.ArrayList;

/**
 * Class for dealing with a list of tasks object or its subclasses alternatives.
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.03
 * @since 0.00
 */
public class VocabList {
    /** ArrayList containing the tasks */
    private final ArrayList<Vocab> vocabs;

    public VocabList() {
        this.vocabs = new ArrayList<>();
    }


    public VocabList(ArrayList<Vocab> tasks) {
        this.vocabs = tasks;
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

    /**
     * Getter for the array list of tasks
     *
     * @return ArrayList of Task objects or its alternatives
     */
    public ArrayList<Vocab> getTasks() {
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
        StringBuilder tasksDialog = new StringBuilder();
        for (int i = 0; i < vocabs.size(); i++) {
            tasksDialog.append("    ").append(i + 1).append(".").append(vocabs.get(i).toString()).append("\n");
        }
        return tasksDialog.toString();
    }
}
