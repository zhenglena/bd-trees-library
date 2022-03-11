## Leverage the nest structure of trees to your advantage!

One nice quality of trees is that a child node of a tree is the root of a smaller tree, which we generally call a
sub-tree.

So, imagine this: you can split the children of a node into two new sub-trees, one which has the left child as the
root, and another which has the right child as the root. Likewise, you can split each sub-tree using the children of
the root of each sub-tree as roots of two more sub-trees! And you can continue doing this until you hit the "leaf"
nodes of your tree, those which have `null` children nodes.  

How does this help us?

Well, since we can treat each child as the root of a sub-tree, we can use the same algorithm over and over on
each sub-tree. As such, we can simplify our code using recursion. If you need a refresher on recursion, look back at
the lesson materials from Unit 3.

In both of the phases for this activity, you can leverage recursion for more elegant solutions.
