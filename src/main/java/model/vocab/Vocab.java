package model.vocab;

import java.util.Objects;

/**
 * Vocab is a structure in which it has a phrase and a feedback.
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.03
 * @since 0.00
 */
public class Vocab {

    private final String phrase;
    private final String feedback;

    private Vocab(String phrase, String feedback) {
        this.phrase = phrase;
        this.feedback = feedback;
    }

    /**
     * Factory method for producing a Vocab object.
     *
     * @param phrase   a word, sentence, or other string to be recognised
     * @param feedback a word, sentence, or string to be feedback.
     * @return vocab object with specified phrase and feedback
     */
    public static Vocab of(String phrase, String feedback) {
        return new Vocab(phrase, feedback);
    }

    /**
     * Getter for phrase.
     *
     * @return phrase in which this vocab is going to bbe recognised as.
     */
    public String getPhrase() {
        return phrase;
    }

    /**
     * Getter for feedback.
     *
     * @return feedback for the phrase associated to this object.
     */
    public String getFeedback() {
        return feedback;
    }

    /**
     * Both phrase and feedback need to be the same to count as the same vocab.
     * Object of other type which is not subtype of vocab will instantly return false.
     *
     * @param other other object of any type.
     * @return whether the object used as argument equal to this vocab.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Vocab)) {
            return false;
        }
        Vocab otherVocab = (Vocab) other;
        return otherVocab.phrase.equals(phrase) && otherVocab.feedback.equals(feedback);
    }

    /**
     * Hashcode calculated by hashing the phrase and feedback.
     *
     * @return the hashcode for this vocab calculated form the phrase and feedback.
     */
    @Override
    public int hashCode() {
        return Objects.hash(phrase, feedback);
    }

    /**
     * String representation of vocab object.
     *
     * @return the string of value of both phrase and feedback for this object.
     */
    @Override
    public String toString() {
        return "phrase='" + phrase + ", feedback='" + feedback;
    }
}
