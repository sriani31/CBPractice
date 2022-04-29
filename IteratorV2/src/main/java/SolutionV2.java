import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SolutionV2 {

    public interface CustomIterator<T>{
        T next();
        boolean hasNext();
    }

    class ListIterator<T> implements CustomIterator<T>{
        private final List<T> iterable;
        private Integer index;

        public ListIterator(List<T> iterable) {
            this.iterable = iterable;
            this.index = 0;
        }

        @Override
        public T next() {
            T element = iterable.get(index);
            index++;
            return element;
        }

        @Override
        public boolean hasNext() {
            return index < iterable.size();
        }
    }

    class RoundRobin<T> implements CustomIterator<T>{
        private final List<CustomIterator<T>> parentIterable;
        private Integer listIndex;

        public RoundRobin(List<List<T>> ListOfLists) {
            parentIterable = ListOfLists.stream().map(ListIterator::new).collect(Collectors.toList());
            this.listIndex = 0;
        }

        @Override
        public T next() {
           T element = parentIterable.get(listIndex).next();
           listIndex = (listIndex+1)%parentIterable.size();
           return element;
        }

        @Override
        public boolean hasNext() {
            return parentIterable.stream().filter(CustomIterator::hasNext).count()>0;
        }
    }

    class ConcatIterator<T> implements CustomIterator<T>{

        private final List<CustomIterator<T>> childIterators;
        private int listIndex;

        public ConcatIterator(CustomIterator<CustomIterator<T>> iterators) {
            this.childIterators = new ArrayList<>();
            while(iterators.hasNext()){
                childIterators.add(iterators.next());
            }
            listIndex = 0;
        }

        @Override
        public T next() {
            if(!childIterators.get(listIndex).hasNext()){
                listIndex++;
            }
            return childIterators.get(listIndex).next();
        }

        @Override
        public boolean hasNext() {
            return childIterators.stream().filter(CustomIterator::hasNext).count()>0;
        }
    }

    public static void main(String[] args) {

    }
}
