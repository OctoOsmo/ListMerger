import java.util.List;

public abstract class AbstractListMerger<T> implements ListMerger<T> {
    @Override
    public List<T> merge(List<T> first, List<T> second) {
        if (first.isEmpty() || second.isEmpty()){
            return null;
        }else {
            return mergeNotEmptyLists(first, second);
        }
    }

    protected abstract List<T> mergeNotEmptyLists(List<T> first, List<T> second);
}
