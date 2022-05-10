package Mfset;

public class Main {
    public static void main(String[] args) {

        Mfset mfset = new Mfset(10);
        mfset.merge(1, 2);
        mfset.merge(1, 3);
        mfset.merge(1, 4);
        System.out.println(mfset.find(5));
    }
}
