package MergeFind;

public class Main {
    public static void main(String[] args) {

        QuickUnion MF = new QuickUnion(10);
        MF.merge(1, 2);
        MF.merge(1, 3);
        MF.merge(1, 4);
        System.out.println(MF.find(5));
    }
}
