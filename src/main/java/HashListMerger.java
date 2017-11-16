import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HashListMerger<T> extends AbstractListMerger<T> {

    @Override
    public List<T> mergeNotEmptyLists(List<T> first, List<T> second) {
        List<T> mergedList;
        if (first.size() < second.size()) {
            mergedList = hashMerge(first, second);
        } else {
            mergedList = hashMerge(second, first);
        }
        return mergedList;
    }

    private List<T> hashMerge(List<T> first, List<T> second) {
        Set<T> tempHash = new HashSet<T>();
        List<T> mergedList = new ArrayList<T>();
        tempHash.addAll(first);
        for (T element : second) {
            if (tempHash.contains(element)) {
                mergedList.add(element);
            }
        }
        return mergedList;
    }

}
