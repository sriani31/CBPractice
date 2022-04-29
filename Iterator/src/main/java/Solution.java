import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import static java.util.List.*;


public class Solution {

    interface CustomIterator<T>{

        T next();
        boolean hasNext();
    }

    class ListIterator<T> implements CustomIterator<T>{

        private final List<T> iterable;
        private Integer index;

        ListIterator(List<T> iterable){
            this.iterable = iterable;
            this.index = 0;
        }
        @Override
        public T next() {
            T element = iterable.get(index);
            index +=1;
            return element;
        }

        @Override
        public boolean hasNext() {
            return index < iterable.size();
        }
    }

    class RoundRobinIterator<T> implements CustomIterator<T>{
        private final List<CustomIterator<T>> parentIterator;
        private  Integer listIndex;

        RoundRobinIterator(List<List<T>> listOfLists ){
            parentIterator =listOfLists.stream().map(ListIterator::new).collect(Collectors.toList());

            this.listIndex = 0;
        }

        @Override
        public T next() {
           T element = parentIterator.get(listIndex).next();
           listIndex = (listIndex +1)% parentIterator.size();
           return element;
        }

        @Override
        public boolean hasNext() {
            return parentIterator.stream().filter(CustomIterator::hasNext).count()>0;
        }
    }

    class ConcatIterator<T> implements CustomIterator<T>{
        private final List<CustomIterator<T>> childIterators;
        private Integer listIndex;

        public ConcatIterator(CustomIterator<CustomIterator<T>> iterators) {
            this.childIterators = new ArrayList<>();
            while (iterators.hasNext()){
                childIterators.add(iterators.next());
            }
            listIndex = 0;
        }

        @Override
        public T next() {
           if(!childIterators.get(listIndex).hasNext()){
               listIndex +=1;
           }
           return childIterators.get(listIndex).next();
        }

        @Override
        public boolean hasNext() {
            return childIterators.stream().filter(CustomIterator::hasNext).count()>0;
        }
    }

    class StepIterator<T> implements CustomIterator<T>{
        private final List<T> inputList;
        private final Integer start,end,step;
        private Integer currentIndex;

        public StepIterator(List<T> inputList, Integer start, Integer end, Integer step) {
            this.inputList = inputList;
            this.start = start;
            this.end = end;
            this.step = step;
            this.currentIndex = start;
        }

        @Override
        public T next() {
            T element = inputList.get(currentIndex);
            currentIndex = currentIndex+step;
            return element;
        }

        @Override
        public boolean hasNext() {
            if(step > 0 ){
                return currentIndex < end;
            }
            else {
                return currentIndex > end;
            }
        }
    }

    class InfiniteIterator<T> implements CustomIterator<T>{
        private  final List<T> values;
        private Integer currentIndex;

        public InfiniteIterator(List<T> values) {
            this.values = values;
            currentIndex = 0;
        }

        @Override
        public T next() {
           T element = values.get(currentIndex);
           currentIndex = (currentIndex + 1)%values.size();
           return element;
        }

        @Override
        public boolean hasNext() {
            return true;
        }
    }

    class InfiniteIteratorEven<T> implements  CustomIterator<Long>{

       private Long curr;

        public InfiniteIteratorEven(Long start) {
            this.curr = start;

        }

        @Override
        public Long next() {
           Long val = curr;
           curr += 2;
           return val;
        }

        @Override
        public boolean hasNext() {
            return true;
        }
    }

    class IteratorIterator<T> implements CustomIterator<T>{
        @Override
        public T next() {
            return null;
        }

        @Override
        public boolean hasNext() {
            return false;
        }
    }

    public static void main(String[] args) {

        Solution solve = new Solution();
        solve.run();


    }

    public void run(){
        List<String> myList = new ArrayList<String>(of("A","B","C"));
        CustomIterator<String> iter = new ListIterator<String>(myList);

        while (iter.hasNext()){
            System.out.println(iter.next());
        }


        List<String> iterable1 = List.of("A", "B", "C");
        List<String> iterable2 = List.of("D", "E", "F");
        List<String> iterable3 = List.of("G", "H", "I");
        List<String> iterable4 = List.of("J", "K", "L");

        CustomIterator<String> iter1 = new RoundRobinIterator<String>(List.of(iterable1,iterable2,iterable3,iterable4));

        while (iter1.hasNext()){
            System.out.println(iter1.next());
        }

        System.out.println("---------");
        CustomIterator<String> iterabl1 = new ListIterator<>(List.of("A", "B", "C"));
        CustomIterator<String> iterabl2 = new ListIterator<>(List.of("D", "E", "F"));
        CustomIterator<String> iterabl3 = new ListIterator<>(List.of("G", "H", "I"));
        CustomIterator<String> iterabl4 = new ListIterator<>(List.of("J", "K", "L"));

        CustomIterator<String> iterator = new ConcatIterator<>(new ListIterator<>(List.of(iterabl1, iterabl2, iterabl3, iterabl4)));

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("--------------");

        List<String> iterable = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L");
        CustomIterator<String> iter2 = new StepIterator<>(iterable, 0,iterable.size(),2);
        while (iter2.hasNext()){
            System.out.println(iter2.next());
        }

        List<String> values = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L");
        CustomIterator<String> iterInfinite = new InfiniteIterator<>(values);
        int i = 0;
       while (iterInfinite.hasNext() && i < 100){
           System.out.println(iterInfinite.next());
           i++;
       }

        CustomIterator<Long> iteratorEven = new InfiniteIteratorEven<Long>(2L);
         i = 0;

        while (iteratorEven.hasNext() && i < 100) {
            System.out.println(iteratorEven.next());
            i += 1;
        }

        List<String> stringList = new ArrayList<>(List.of("A","B"));

        Iterator<String> ij = stringList.iterator();
        while (ij.hasNext()){
            System.out.println(ij.next());
        }


    }
}
