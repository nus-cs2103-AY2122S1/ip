package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import model.vocab.Vocab;
import model.vocab.VocabList;
import parser.Parser;

/**
 * Class for dealing with the storage system saving and loading save file.
 * Deal with reading and writing to file specifically to be dealt with vocab.
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.03
 * @since 0.00
 */
public class VocabularyStorage extends Storage {

    public static final String VOCAB_FILE_NAME = "/vocab.txt";

    private VocabList vocabListRead;

    /**
     * Create a writer and reader at file according to default path.
     *
     * @throws IOException if the vocabulary storage fails to create writer at that location
     */
    public VocabularyStorage() throws IOException {
        this.filePath = DIRECTORY_PATH + VOCAB_FILE_NAME;
        writer = new BufferedWriter(new FileWriter(filePath, true));
        reader = new BufferedReader(new FileReader(filePath));
    }

    /**
     * load vocab list from the save file.
     *
     * @return vocab list read from the save file.
     */
    public VocabList loadVocabList() {

        vocabListRead = new VocabList();

        reader.lines().forEach((line) -> {
            vocabListRead.add(Vocab.of(line.substring(0, line.indexOf(" |")), line.substring(line.indexOf("| ") + 2)));
        });

        return vocabListRead;
    }

    /**
     * Store the vocab list into the save file the reader and writer are currently at.
     *
     * @param vocabList the vocab list to be stored.
     * @throws IOException if there is any error dealing with the system IO.
     */
    public void save(VocabList vocabList) throws IOException {
        Path path = Paths.get(filePath);
        List<String> fileContent =
                vocabList.getVocabs().stream().filter(vocab -> !VocabList.DEFAULT_PHRASE.contains(vocab.getPhrase()))
                        .map(Parser::vocabToSaveFormat).collect(Collectors.toList());
        Files.write(path, fileContent, StandardCharsets.UTF_8);
        reader.close();
        writer.close();
    }

    /**
     * Force the current vocab storage to close.
     *
     * @throws IOException the reader and writer cannot close.
     */
    public void close() throws IOException {
        reader.close();
        writer.close();
    }

}
