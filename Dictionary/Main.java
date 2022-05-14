package Dictionary;

public class Main {
    public static void main(String[] args) {
        TreeDictionary<Integer, Integer> treeDict = new TreeDictionary<>();
        treeDict.insert(30, 1);
        treeDict.insert(15, 2);
        treeDict.insert(70, 3);
        treeDict.insert(10, 4);
        treeDict.insert(20, 5);
        treeDict.insert(60, 6);
        treeDict.insert(85, 7);
        treeDict.insert(80, 8);
        treeDict.insert(90, 9);
        treeDict.insert(50, 10);
        treeDict.insert(65, 11);
        treeDict.insert(40, 12);
        treeDict.insert(55, 13);
        // treeDict.printAll();

        HashDictionary<Integer> hashDict = new HashDictionary<>(5);
        hashDict.insert("alberto", 10);
        hashDict.insert("alessio", 15);
        hashDict.insert("roberto", 20);
        hashDict.printAll();
    }

}
