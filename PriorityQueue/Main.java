package PriorityQueue;

public class Main {
    public static void main(String[] args) {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(5);

        priorityQueue.insert("Alberto", 10);
        PriorityItem<String> item = priorityQueue.insert("Alessio", 20);
        priorityQueue.insert("Michael", 30);
        priorityQueue.insert("Andrea", 5);
        priorityQueue.insert("Anthony", 9);

        String item2value = priorityQueue.deleteMin();
        System.out.println(item2value);
        priorityQueue.insert("Emanuele", 5);
        priorityQueue.decrease(item, 1);
        System.out.println(priorityQueue.min());
        priorityQueue.insert("Giovanni", 33);
        priorityQueue.printAll();
    }
}
