package Greedy;

import java.util.HashMap;
import java.util.Map.Entry;

import PriorityQueue.PriorityQueue;

public class Huffman {
    String s;
    HashMap<Character, Integer> freq;
    TreeNode huffmanTree;
    HashMap<Character, String> codes = new HashMap<>();
    int dim = 0;

    private class TreeNode {
        Character c;
        Integer f;
        TreeNode l, r;

        TreeNode(Integer f, Character c) {
            this.c = c;
            this.f = f;
            this.l = null;
            this.r = null;
        }

        boolean isLeaf() {
            return this.l == null;
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
        PriorityQueue<TreeNode> Q = new PriorityQueue<>(freq.size());
        for (Entry<Character, Integer> f : freq.entrySet())
            Q.insert(new TreeNode(f.getValue(), f.getKey()), f.getValue());
        for (int i = 0; i < dim - 1; i++) {
            TreeNode z1 = Q.deleteMin();
            TreeNode z2 = Q.deleteMin();
            TreeNode z = new TreeNode(z1.f + z2.f, null);
            z.l = z1;
            z.r = z2;
            Q.insert(z, z.f);
        }
        huffmanTree = Q.deleteMin();
    }

    private void setCodes(TreeNode t, String s) {
        if (t.isLeaf()) {
            codes.put(t.c, s);
        } else {
            setCodes(t.l, s + '0');
            setCodes(t.r, s + '1');
        }
    }

    public String encode() {
        String encodedString = "";
        for (int i = 0; i < s.length(); i++)
            encodedString += codes.get(s.charAt(i));
        return encodedString;
    }

    public String decode(String b) {
        String decodedString = "";
        TreeNode root = huffmanTree;
        for (int i = 0; i < b.length(); i++) {
            boolean bit = b.charAt(i) == '0';
            root = bit ? root.l : root.r;
            if (root.isLeaf()) {
                decodedString += root.c;
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
