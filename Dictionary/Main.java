package Dictionary;

public class Main {
    public static void main(String[] args) {
        TreeDictionary<Integer, Integer> dict = new TreeDictionary<>();
        dict.insert(30, 1);
        dict.insert(15, 2);
        dict.insert(70, 3);
        dict.insert(10, 4);
        dict.insert(20, 5);
        dict.insert(60, 6);
        dict.insert(85, 7);
        dict.insert(80, 8);
        dict.insert(90, 9);
        dict.insert(50, 10);
        dict.insert(65, 11);
        dict.insert(40, 12);
        dict.insert(55, 13);
        // dict.insert(11, 14);

        dict.printAll();
    }

}
