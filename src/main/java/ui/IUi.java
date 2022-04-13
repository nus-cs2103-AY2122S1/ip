package ui;

import java.util.List;

/**
 * Interface for UI.
 * Sole responsibility is to determine the UI to user.
 */
public interface IUi {
    /**
     * Displays the sentence for the CLI.
     *
     * @param sentence String to be printed.
     */
    void printSentence(String sentence);
    
    /**
     * Displays a list for the CLI.
     *
     * @param list List.
     * @param <T> Parameter, implicitly inferred.
     */
    <T> void printIndexedList(List<T> list);
}
