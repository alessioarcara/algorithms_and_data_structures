package Sets;

import java.util.HashSet;
import java.util.Set;

public class Sets {
    public static <T> Set<T> remove(Set<T> S, T element) {
        Set<T> copy = new HashSet<>();
        copy.addAll(S);
        copy.remove(element);
        return copy;
    }
}
