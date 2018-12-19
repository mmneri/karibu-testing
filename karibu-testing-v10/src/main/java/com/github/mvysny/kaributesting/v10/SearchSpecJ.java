package com.github.mvysny.kaributesting.v10;

import com.vaadin.flow.component.Component;

import com.vaadin.flow.component.HasValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

import kotlin.ranges.IntRange;

/**
 * A criterion for matching components. The component must match all of non-null fields.
 * @author mavi
 */
public class SearchSpecJ<T extends Component> {
    @NotNull
    private final SearchSpec<T> spec;

    public SearchSpecJ(@NotNull SearchSpec<T> spec) {
        this.spec = spec;
    }

    /**
     * The required {@link Component#getId()}; if {@code null}, no particular id is matched.
     * @param id the id
     * @return this
     */
    @NotNull
    public SearchSpecJ<T> withId(@Nullable String id) {
        spec.setId(id);
        return this;
    }

    /**
     * The required {@link BasicUtilsKt#getCaption(Component)}; if {@code null}, no particular caption is matched.
     * @param caption
     * @return this
     */
    @NotNull
    public SearchSpecJ<T> withCaption(@Nullable String caption) {
        spec.setCaption(caption);
        return this;
    }

    /**
     * The required {@link HasValue#getValue()}. If {@code null}, no particular value is matched.
     * @param value the expected value
     * @return this
     */
    @NotNull
    public SearchSpecJ<T> withValue(@Nullable Object value) {
        spec.setValue(value);
        return this;
    }

    /**
     * The required {@link BasicUtilsKt#getPlaceholder(Component)}; if {@code null}, no particular placeholder is matched.
     * @param placeholder the placeholder
     * @return this
     */
    @NotNull
    public SearchSpecJ<T> withPlaceholder(@Nullable String placeholder) {
        spec.setPlaceholder(placeholder);
        return this;
    }

    /**
     * if not null, the component's {@link com.vaadin.flow.dom.Element#getText} must match given text.
     * @param text the expected text
     * @return this
     */
    @NotNull
    public SearchSpecJ<T> withText(@Nullable String text) {
        spec.setText(text);
        return this;
    }

    /**
     * expected count of matching components, defaults to {@code 0..Int.MAX_VALUE}
     * @param count expected count of matching components. Any count of component within this range is accepted.
     * @return this
     */
    @NotNull
    public SearchSpecJ<T> withCount(@NotNull IntRange count) {
        spec.setCount(count);
        return this;
    }

    /**
     * Expected count of matching components, defaults to {@code 0..Int.MAX_VALUE}
     * @param count expected count
     * @return this
     */
    @NotNull
    public SearchSpecJ<T> withCount(int count) {
        return withCount(count, count);
    }

    /**
     * Expected count of matching components, defaults to {@code 0..Int.MAX_VALUE}
     * @param min minimum count, inclusive
     * @param max maximum count, inclusive
     * @return this
     */
    @NotNull
    public SearchSpecJ<T> withCount(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Parameter min: invalid value " + min + ": must be less than or equal to max: " + max);
        }
        return withCount(new IntRange(min, max));
    }

    /**
     * Adds an additional predicate which the component needs to match. Not null.
     * <p/>
     * Please remember to provide a proper {@link Object#toString()} for the predicate,
     * so that you'll get an informative error message on lookup failure.
     * @param predicate the matcher
     * @return this
     */
    @NotNull
    public SearchSpecJ<T> withPredicate(@NotNull Predicate<T> predicate) {
        spec.getPredicates().add(predicate);
        return this;
    }

    @Override
    public String toString() {
        return spec.toString();
    }
}
