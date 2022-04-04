package LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<String>();
        ArrayList<Integer> arrayList = new ArrayList<Integer>(5);

        linkedList.append("Hello");
        linkedList.append("World");
        linkedList.append("!");

        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.remove(3);
        arrayList.remove(0);

        System.out.println(String.format("TESTA: %s", linkedList.head().value));
        System.out.println(String.format("CODA: %s", linkedList.tail().value));
        System.out.print("ELEMENTI NELLA LISTA: ");
        linkedList.printAll(linkedList.head());
        System.out.println();
        System.out.print("ELEMENTI NELL'ARRAY: ");
        arrayList.printAll();
    }
}
