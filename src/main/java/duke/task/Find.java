package duke.task;

import duke.Ui;

public class Find {
    boolean isFound;
    String word;
    TaskList ls;
    String result;
    Ui ui = new Ui();

    public Find(String word, TaskList ls) {
        this.isFound = false;
        this.word = word;
        this.ls = ls;
    }

    public void findWord() {
        if (!this.isFound) {
            this.result = ui.printListWithKeyword(ls, word, this);
        }

        if (this.result.isEmpty()) {
            ui.noResultsFound(word);
        } else {
            System.out.println(this.result);
        }
    }

    public void setFound() {
        this.isFound = true;
    }

}
