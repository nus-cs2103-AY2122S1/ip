package kayu.storage;

import kayu.exception.StorageException;
import kayu.note.Note;

/**
 * Handles the reading and writing of {@link kayu.note.Note} into files.
 */
public class NoteStorage extends Storage<Note> {

    protected static final String ERROR_EMPTY_NOTE_DESC = "Note cannot have an empty description.";

    // Default task file directory.
    private static final String DEFAULT_FILE_PATH = "data/notes.txt";

    private NoteStorage(String directoryPath, String filePath) {
        super(directoryPath, filePath);
    }

    /**
     * Generates a {@link kayu.storage.NoteStorage} instance based on the default path.
     *
     * @return A {@link kayu.storage.NoteStorage} instance.
     */
    public static NoteStorage generate() {
        return NoteStorage.generate(DEFAULT_FILE_PATH);
    }

    /**
     * Generates a {@link kayu.storage.NoteStorage} instance based on a specified path.
     *
     * @param filePath File path string.
     * @return A {@link kayu.storage.NoteStorage} instance.
     */
    public static NoteStorage generate(String filePath) {
        String directoryPath = Storage.extractDirectoryPath(filePath);
        return new NoteStorage(directoryPath, filePath);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Note decode(String encoded) throws StorageException {
        if (encoded.isBlank()) {
            throw new StorageException(ERROR_EMPTY_NOTE_DESC);
        }
        return new Note(encoded);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String encode(Note decoded) {
        return decoded.toString();
    }
}
