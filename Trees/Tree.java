package Trees;

interface Callable<T> {
    void call(T value);
}

public abstract class Tree<T> {
    protected Tree<T> parent;
    protected T value;

    Tree(T value) {
        this.value = value;
        this.parent = null;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Tree<T> getParent() {
        return parent;
    }
}
