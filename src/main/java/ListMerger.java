import java.util.List;

public interface ListMerger<T> {

    List<T> merge(List<T> first, List<T> second);

}
