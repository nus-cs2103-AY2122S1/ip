package duke.task;

import duke.Ui;

/**
 * Class that encapsulates searches for keywords among current Tasks.
 */
public class Find {
    private boolean isFound;
    private String word;
    private TaskList ls;
    private String result;
    private Ui ui = new Ui();

    /**
     * Constructor for Find.
     *
     * @param word Keyword.
     * @param ls Current TaskList.
     */
    public Find(String word, TaskList ls) {
        this.isFound = false;
        this.word = word;
        this.ls = ls;
    }

    /**
     * Prints out the list of Tasks that include the keyword.
     */
    public String findWord() {
        if (!this.isFound) {
            return this.result = ui.printListWithKeyword(ls, word, this);
        }

        if (this.result.isEmpty()) {
            return ui.noResultsFound(word);
        } else {
            return this.result;
        }
    }

    /**
     * Signals that there are Tasks with the keyword by setting the isFound boolean to true.
     */
    public void setFound() {
        this.isFound = true;
    }

}
