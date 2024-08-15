import model.Book;
import model.comparison.BookIsbnComparator;
import org.apache.commons.lang3.NotImplementedException;
import treestructure.BookNode;

import java.util.Stack;

/**
 * Application to test traversing Binary Trees and Binary Search Trees.
 *
 * Represents a Library with a collection of books.
 */
public class LibraryService {

    /**
     * The root node of our tree of Books.
     * Assume this tree is sorted by ISBN.
     * The tree and its nodes should not be modified by our application.
    */
    private final BookNode books;
    private final BookIsbnComparator comparator;

    /**
     * Constructs our library with a default tree of books.
     * Assume this tree is sorted by ISBN.
     *
     * @param books The root node of a tree of Books
     */
    public LibraryService(final BookNode books) {
        this.books = books;
        comparator = new BookIsbnComparator();
    }

    /**
     * Determines whether or not a book is in the library
     * by searching our tree for a book with the given ISBN.
     *
     * @param isbn A given ISBN to search our library for
     * @return True if a book with the given ISBN is in our library and
     *         false otherwise
     */
    public boolean isBookInLibraryByIsbn(String isbn) {
        // PARTICIPANTS: IMPLEMENT YOUR BINARY SEARCH HERE
        Book book = new Book(isbn, null, null); //book to be searched
        BookNode bookNode = books;
        if (isbn == null || isbn.isEmpty()) {
            return false;
        }
        while (comparator.compare(bookNode.getBook(), book) != 0) {
            if (bookNode.getLeft() == null && bookNode.getRight() == null) {
                return false;
            } else if (comparator.compare(bookNode.getBook(), book) < 0) { //bookNode's isbn is smaller
                // than book's isbn
                bookNode = bookNode.getRight();
            } else if (comparator.compare(bookNode.getBook(), book) > 0) {
                bookNode = bookNode.getLeft();
            }
        }
        return true;

    }


    /**
     * Determines whether or not a book is in the library
     * by searching our tree for a book with the given Title and Author.
     *
     * @param title A given title to search our library for, alongside an author's name
     * @param author The name of a given author to search our library for, alongside a title
     * @return True if a book with the given title and author is in our library, and
     *         false otherwise
     */
    public boolean isBookInLibraryByTitleAndAuthor(String title, String author) {
        // PARTICIPANTS: IMPLEMENT YOUR DEPTH FIRST SEARCH HERE
        if (title == null || title.isEmpty() || author == null || author.isEmpty()) {
            return false;
        }

        Stack<BookNode> bookStack = new Stack<>();
        bookStack.push(books); //root
        while (!bookStack.isEmpty()) {
            BookNode bookNode = bookStack.pop();
            if (bookNode.getBook().getTitle().equals(title) && bookNode.getBook().getAuthor().equals(author)) {
                return true;
            } else {
                if (bookNode.getLeft() != null) {
                    bookStack.push(bookNode.getLeft());
                }
                if (bookNode.getRight() != null) {
                    bookStack.push(bookNode.getRight());
                }
            }
        }
        return false;
    }
}