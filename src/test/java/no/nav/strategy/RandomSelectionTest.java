package no.nav.strategy;

import no.nav.model.Category;
import no.nav.model.selection.RandomSelection;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static no.nav.model.Category.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomSelectionTest {

    @Test
    void select() {
        RandomSelection r = new RandomSelection();
        Optional<Category> category = r.select(Arrays.asList(1, 2, 3, 4, 5));

        assertTrue(category.isPresent());
    }

    @Test
    void select_only_not_taken() {
        RandomSelection r = new RandomSelection();
        Optional<Category> category = r.select(Arrays.asList(1, 2, 3, 4, 5), Arrays.asList(ONE, TWO, THREE, FOUR, SIX));

        assertTrue(category.isPresent());
        assertTrue(category.get() == FIVE);
    }
}