### What is a red-black tree?

A red-black tree is a kind of self-balancing binary search tree. In fact, `TreeMap` is also a red-black tree,
as noted in the [TreeMap javadoc](https://docs.oracle.com/javase/7/docs/api/java/util/TreeMap.html). In this case,
however, we want to directly access the nodes ourselves, so we decided to implement our own red-black tree.

In a red-black tree, we create a new `color` member in each node. The color of each node can either be RED (in our
implementation, `true`) or BLACK (in our implementation, `false`). We can use the color of our nodes to ensure that our
tree is balanced as we add or remove values from it, rotating our nodes around each other or changing their colors
based on a specific set of rules.

If you are curious about red-black trees, you can learn more at
[the Wikipedia page for red-black trees](https://en.wikipedia.org/wiki/Red%E2%80%93black_tree)
We also created a class, `src/test/java/com/amazon/ata/trees/prework/library/utils/BalancedBookTree.java` which creates
a red-black binary tree for us as part of our tests.
