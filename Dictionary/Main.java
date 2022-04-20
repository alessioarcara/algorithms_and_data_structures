package Dictionary;

public class Main {
    public static void main(String[] args) {
        TreeDictionary<String, Integer> dict = new TreeDictionary<>();
        dict.insert("a2", 2);
        dict.insert("a1", 1);
        dict.insert("a3", 3);

        dict.remove("a2");
        dict.printAll();
    }
}
