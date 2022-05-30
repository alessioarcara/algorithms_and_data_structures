package Greedy;

import java.util.HashMap;
import java.util.Map.Entry;

import PriorityQueue.PriorityQueue;
import Trees.BinaryTree;

public class Huffman {
    String s;
    HashMap<Character, Integer> freq;
    BinaryTree<TreeNode> huffmanTree;
    HashMap<Character, String> codes = new HashMap<>();
    int dim = 0;

    private class TreeNode {
        Character c;
        Integer f;

        public TreeNode(Integer f, Character c) {
            this.c = c;
            this.f = f;
        }
    }

    public Huffman(String s) {
        this.s = s;
        buildFrequencies();
        buildHuffmanTree();
        setCodes(huffmanTree, "");
    }

    private void buildFrequencies() {
        freq = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Integer val = freq.get(c);
            if (val != null) {
                freq.put(c, val + 1);
            } else {
                freq.put(c, 1);
                dim++;
            }
        }
    }

    private void buildHuffmanTree() {
        PriorityQueue<BinaryTree<TreeNode>> Q = new PriorityQueue<>(freq.size());
        for (Entry<Character, Integer> f : freq.entrySet())
            Q.insert(new BinaryTree<TreeNode>(new TreeNode(f.getValue(), f.getKey())), f.getValue());
        for (int i = 0; i < dim - 1; i++) {
            BinaryTree<TreeNode> z1 = Q.deleteMin();
            BinaryTree<TreeNode> z2 = Q.deleteMin();
            BinaryTree<TreeNode> z = new BinaryTree<>(new TreeNode(z1.getValue().f + z2.getValue().f, null));
            z.insertLeft(z1);
            z.insertRight(z2);
            Q.insert(z, z.getValue().f);
        }
        huffmanTree = Q.deleteMin();
    }

    private void setCodes(BinaryTree<TreeNode> t, String s) {
        if (t.getLeft() == null) {
            codes.put(t.getValue().c, s);
            return;
        }
        setCodes(t.getLeft(), s + '0');
        setCodes(t.getRight(), s + '1');
    }

    public String encode() {
        String encodedString = "";
        for (int i = 0; i < s.length(); i++)
            encodedString += codes.get(s.charAt(i));
        return encodedString;
    }

    public String decode(String b) {
        String decodedString = "";
        BinaryTree<TreeNode> root = huffmanTree;
        for (int i = 0; i < b.length(); i++) {
            boolean bit = b.charAt(i) == '0';
            root = bit ? root.getLeft() : root.getRight();
            if (root.getLeft() == null) {
                decodedString += root.getValue().c;
                root = huffmanTree;
            }
        }
        return decodedString;
    }

    public static void main(String[] args) {
        String s = "nel_cammino_di_nostra_vita";
        Huffman huffman = new Huffman(s);

        String huffmanString = huffman.encode();
        System.out.println(huffmanString);
        System.out.println(huffman.decode(huffmanString));
    }
}
