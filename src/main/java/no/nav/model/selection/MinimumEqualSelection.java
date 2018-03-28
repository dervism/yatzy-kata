package no.nav.model.selection;

import no.nav.model.Category;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MinimumEqualSelection extends AbstractSelection {

    private int minimum;

    public MinimumEqualSelection() {
        this(2);
    }

    public MinimumEqualSelection(int n) {
        super();
        this.minimum = n;
    }

    public MinimumEqualSelection(Collection<Integer> diceList, Collection<Category> categoryList) {
        super(diceList, categoryList);
        this.minimum = 2;
    }

    public MinimumEqualSelection(Collection<Integer> diceList, Collection<Category> categoryList, int minimum) {
        super(diceList, categoryList);
        this.minimum = minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getMinimum() {
        return minimum;
    }

    @Override
    public Optional<Category> select() {
        Optional<Map.Entry<Integer, Long>> max = getDiceList().stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() >= minimum)
                .filter(entry -> !getCategoryList().contains(Category.fromIndex(entry.getKey())))
                .max(Comparator.comparing(Map.Entry<Integer, Long>::getValue)
                        .thenComparing(Map.Entry::getKey));

        return max.map(entry -> Category.fromIndex(entry.getKey()));
    }

    @Override
    public Selection build(Collection<Integer> list, Collection<Category> categories) {
        return new MinimumEqualSelection(list, categories, 2);
    }
}