## Writing Tree Searches Yourself! Can you find the books?

Expected time required: 60 min

In this exercise you will add implementation to the `LibraryService` class:

* check if a book is in the library that matches a given ISBN (`isBookInLibraryByIsbn`) 
* check if a book is in the library that matches both a given title and
 author (`isBookInLibraryByTitleAndAuthor`)
 
### Phase 0
First, sync your workspace and your mainline branch and create the `trees-prework-library` branch for this activity.
Then, run `FindBookByISBNTest`. The build should succeed, but the tests should fail.

For this prework, we won't be using `TreeMap`, but, rather, our own implementation of a binary tree.
Let's take a look at the components that make up our tree.

First, take a look at the `Book` class at `model/Book.java`.

These are the values stored in our nodes - each `Book` contains the following information:
* `isbn` - a unique id for each book, represented as a `String`
* `title` - the title for each book, represented as a `String`
* `author` - the title for a given book, represented as a `String`

Next, take a look at the `BookNode` class at `treestructure/BookNode.java`.

These are the nodes of our binary tree. Each `BookNode` contains the following members:
* `book` - the `Book` stored at our node. We can assume that our `book` member will never be null.
* `left` - a `BookNode`, the left child of our node. If a left child exists, its `Book` must have an ISBN of a lower
value than the `Book` at this node. If no left child exists, this value is `null`.
* `right` - a `BookNode`, the right child of our node. The value of the right child is `null` if the node does not have
a right child.  If a right child exists, its `Book` must have an ISBN of a higher value than the `Book` at this node.
If no left child exists, this value is `null`.
* `color` - you don't need to worry about working with this member for this activity. It is used when constructing
our tree as a [red-black tree](./hints/hint01.md) before it is passed into the constructor of `LibraryService`.
 
Now, take a look at the class that you'll actually be modifying in this activity- `LibraryService`

`LibraryService` is constructed with a single member: `books`.

`books` is a `BookNode`, but there are some important things about `books` that you need to know before you get started:

* `books` is the root node of our tree. It is not the child of any other `BookNode`.
* The key of our tree is the `isbn` of the `Book` within each `BookNode`.
* Our implementation guarantees that the `books` tree will always be balanced and sorted by `isbn`.
 * In our implementation, we used `comparison/BookIsbnComparator.java` to
   perform the sorting.
* You will not need to modify any `BookNode` in our tree in any part of this activity.

Once you feel like you have a grip on our `BookNode` class and our tree of `BookNode`s, `book`, move ahead to
implementation.

### Phase 1: Searching a binary tree with a key
The first method you will need to implement is `isBookInLibraryByIsbn` within `LibraryService`.

This method will:
* Accept a `String` parameter for `isbn`
* Perform a search on the tree, using `books` as the root node of the tree
* Return `true` if the book with the desired `isbn` is in the tree, and `false` if it is not.

Here's an edge case to consider as well:
* If the provided `isbn` is `null` or an empty `String`, return `false`.

Before working on the implementation of this method, consider these questions:
1) Our `books` tree is already sorted by `isbn`. How can you use that to your advantage when sorting?
2) Our implementation of the `books` tree guarantees it will be balanced. Knowing that, what is the time
complexity of a binary search algorithm for this tree?
3) If our `books` tree was not balanced, what would be the worst-case time complexity for a binary search?

Then, go ahead and implement a binary search of `books` within `isBookInLibraryByIsbn`.

To test your implementation, run the tests for this phase at `FindBookByISBNTest.java`.

### Phase 2: Searching a binary tree without a key
The first method you will need to implement is `isBookInLibraryByTitleAndAuthor` within `LibraryService`.

This method will:
* Accept `String` parameters for `title` and `author`.
* Perform a search on the tree, using `books` as the root node of the tree
* Return `true` if the book with the desired `title` and `author` is in the tree, and `false` if it is not.

Here's a couple edge cases to consider as well:
* If the provided `author` is `null` or an empty `String`, return `false`
* If the provided `title` is `null` or an empty `String`, return `false`

Unfortunately, we can't rely on the way our tree is sorted to make this search easier, as it is only sorted by 
`isbn`, not `author` or `title`. Instead, like trekking through the Minotaur's maze, we'll need to move through our tree
without any sense of direction. We'll have to rely on a slower but trusty friend: the depth-first search.

Before implementing this method, consider the following questions:
1) What is the time complexity of a depth-first search?
2) Our `books` tree is guaranteed to be balanced. Does that affect the time complexity of this search? Why or why not?

Then, go ahead and implement a depth-first search of `books` within `isBookInLibraryByTitleAndAuthor`.

To test your implementation, run the tests for this phase at `FindBookByTitleAndAuthorTest.java`.

## Hints
[Leverage the nested structure of trees to your advantage!](./hints/hint02.md)
