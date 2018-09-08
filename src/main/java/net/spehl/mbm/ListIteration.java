package net.spehl.mbm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;


@State(Scope.Benchmark)
public class ListIteration {

    final int LIST_SIZE = 100_000;
    List<Integer> elements = new ArrayList<>();

    @Setup
    public void setup() {
        for (int i = 0; i < LIST_SIZE; i++) {
            elements.add(i);
        }
        Collections.shuffle(elements);
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    public void lambda() {
        int sum = elements.stream().reduce((result, elej) -> result += elej).get();
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    public void forEach() {
        int sum = 0;
        for (Integer element : elements) {
            sum += element;
        }
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    public void indexed() {
        int sum = 0;
        for (int i = 0; i < elements.size(); i++) {
            Integer element = elements.get(i);
            sum += element;
        }
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

}
