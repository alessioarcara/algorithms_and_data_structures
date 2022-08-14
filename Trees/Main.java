package Trees;

public class Main {
    public static void main(String[] args) {
        /**
         * Binary Tree
         */
        // BinaryTree<Integer> t1 = new BinaryTree<>(1);

        // t1.insertLeft(new BinaryTree<>(2));
        // t1.insertRight(new BinaryTree<>(3));

        // t1.getLeft().insertLeft(new BinaryTree<>(4));
        // t1.getLeft().insertRight(new BinaryTree<>(5));

        // t1.getRight().insertLeft(new BinaryTree<>(6));
        // t1.getRight().insertRight(new BinaryTree<>(7));

        // t1.getLeft().getLeft().insertLeft(new BinaryTree<>(8));
        // t1.getLeft().getLeft().insertRight(new BinaryTree<>(9));

        // t1.getLeft().getRight().insertLeft(new BinaryTree<>(10));
        // t1.getLeft().getRight().insertRight(new BinaryTree<>(11));

        // t1.getRight().getLeft().insertLeft(new BinaryTree<>(12));
        // t1.getRight().getLeft().insertRight(new BinaryTree<>(13));

        // t1.getRight().getRight().insertLeft(new BinaryTree<>(14));
        // t1.getRight().getRight().insertRight(new BinaryTree<>(15));

        // BinaryTree.search(t1, 7).deleteLeft();
        // t1.printAll();
        // System.out.println("Il numero di nodi dell'albero binaro è " +
        // BinaryTree.count(t1));

        /**
         * N-ary Tree
         */
        // GenericTree<Integer> tg1 = new GenericTree<>(3);

        // tg1.insertChild(new GenericTree<>(112));
        // tg1.leftMostChild().insertChild(new GenericTree<>(7));
        // tg1.leftMostChild().insertChild(new GenericTree<>(8));
        // tg1.leftMostChild().insertChild(new GenericTree<>(9));
        // tg1.leftMostChild().insertChild(new GenericTree<>(10));

        // tg1.leftMostChild().insertSibling(new GenericTree<Integer>(67));
        // tg1.leftMostChild().rightSibling().insertChild(new GenericTree<>(11));

        // tg1.leftMostChild().rightSibling().insertSibling(new GenericTree<>(53));
        // tg1.leftMostChild().rightSibling().rightSibling().insertChild(new
        // GenericTree<>(16));
        // tg1.leftMostChild().rightSibling().rightSibling().insertChild(new
        // GenericTree<>(17));

        // tg1.leftMostChild().setValue(33);
        // tg1.leftMostChild().deleteSibling();

        // // Stampa nodi dell'albero
        // tg1.printAll();
        // System.out.println("Il numero di nodi dell'albero generico è " +
        // GenericTree.count(tg1));

        /*
         * Red-black tree
         */

        RedBlackTree<Integer, Character> t3 = new RedBlackTree<>();
        t3.insertNode(5, 'a');
        t3.insertNode(10, 'b');
        t3.insertNode(15, 'c');
        t3.insertNode(20, 'd');
        t3.insertNode(25, 'e');
        t3.insertNode(30, 'f');
        t3.insertNode(35, 'g');
        t3.insertNode(40, 'h');
        t3.insertNode(45, 'i');
        t3.insertNode(50, 'l');
        t3.printAll(t3.getRoot());
    }

}
