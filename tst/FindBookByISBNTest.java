import model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.BalancedBookTree;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.LibraryTestUtils.generateBooks;

public class FindBookByISBNTest {
    private LibraryService libraryService;
    private Random random;
    private List<Book> sampleBooks;

    @BeforeEach
    public void setup() {
        random = new Random();
        sampleBooks = generateBooks(100, random);

        BalancedBookTree bookTree = new BalancedBookTree();
        sampleBooks.forEach(bookTree::put);

        libraryService = new LibraryService(bookTree.getRoot());
    }

    @Test
    public void testIsBookInLibraryByIsbn_nullIsbn_returnsFalse() {
        // GIVEN
        String isbn = null;

        // WHEN & THEN
        assertFalse(libraryService.isBookInLibraryByIsbn(isbn),
            "Expected to not find a book with a null ISBN in our library");
    }

    @Test
    public void testIsBookInLibraryByIsbn_emptyIsbn_returnsFalse() {
        // GIVEN
        String isbn = "";

        // WHEN & THEN
        assertFalse(libraryService.isBookInLibraryByIsbn(isbn),
            "Expected to not find a book with an empty ISBN in our library");
    }

    @Test
    public void testIsBookInLibraryByIsbn_allExistingBooks_returnsTrue() {
        // GIVEN & WHEN
        List<String> booksNotInLibrary =sampleBooks.stream()
            .map(Book::getIsbn)
            .filter(isbn -> !libraryService.isBookInLibraryByIsbn(isbn))
            .collect(Collectors.toList());

        // THEN
        assertTrue(booksNotInLibrary.isEmpty(),
            String.format("Did not find books with ISBNs {%s} in our library.", booksNotInLibrary));
    }

    @Test
    public void testIsBookInLibraryByIsbn_nonExistentIsbn_returnsTrue() {
        // GIVEN
        String isbn = generateISBNNotInSamples();

        assertFalse(libraryService.isBookInLibraryByIsbn(isbn),
            String.format("Expected to not find a book with ISBN {%s} in our library", isbn));
    }

    private String generateISBNNotInSamples() {
        String isbn;
        do {
            isbn = UUID.randomUUID().toString();
        } while (isBookWithIsbnInSamples(isbn));

        return isbn;
    }

    private boolean isBookWithIsbnInSamples(String isbn) {
        return sampleBooks.stream()
            .map(Book::getIsbn)
            .anyMatch(isbn::equals);
    }
}
