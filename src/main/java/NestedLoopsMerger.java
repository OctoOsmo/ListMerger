import java.util.ArrayList;
import java.util.List;

public class NestedLoopsMerger<T> extends AbstractListMerger<T> {

    @Override
    public List<T> mergeNotEmptyLists(List<T> first, List<T> second) {
        List<T> merged = new ArrayList<>();
        for (T firstElement : first) {
            for (T secondElement : second) {
                if (firstElement.equals(secondElement)){
                    merged.add(firstElement);
                }
            }
        }
        return merged;
    }

}
