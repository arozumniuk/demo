package io.result.randomizer;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class RandomBeanGenerator {
    private static final EnhancedRandom RANDOMIZER = setUpGenerator(1, 1);

    private static EnhancedRandom setUpGenerator(int collectionSizeMin, int collectionSizeMax) {
        return EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .collectionSizeRange(collectionSizeMin, collectionSizeMax)
                .randomize(String.class, new StringsRandomizer())
                .randomize(Long.class, new LongRandomizer())
                .build();
    }

    /**
     * Generate a random instance of the given type.
     *
     * @param type           the target class type
     * @param excludedFields the name of fields to exclude
     * @param <T>            the target type
     * @return a random instance of the given type
     */
    public static <T> T random(Class<T> type, String... excludedFields) {
        return RANDOMIZER.nextObject(type, excludedFields);
    }


    /**
     * Generate a random instance of the given type with defined collection size
     *
     * @param type              the target class type
     * @param excludedFields    the name of fields to exclude
     * @param <T>               the target type
     * @param collectionSizeMax max generated collection size
     * @param collectionSizeMin min generated collection size
     * @return a random instance of the given type
     */
    protected static <T> T random(Class<T> type, int collectionSizeMin, int collectionSizeMax, String... excludedFields) {
        return setUpGenerator(collectionSizeMin, collectionSizeMax).nextObject(type, excludedFields);
    }

    /**
     * Generate a {@link Stream} of random instances of the given type.
     *
     * @param amount         the number of instances to generate
     * @param type           the type for which instances will be generated
     * @param excludedFields the name of fields to exclude
     * @param <T>            the actual type of the target objects
     * @return a stream of random instances of the given type
     */
    public static <T> Stream<T> randomStreamOf(int amount, Class<T> type, String... excludedFields) {
        return RANDOMIZER.objects(type, amount, excludedFields);
    }

    /**
     * Generate a {@link List} of random instances of the given type.
     *
     * @param amount         the number of instances to generate
     * @param type           the type for which instances will be generated
     * @param excludedFields the name of fields to exclude
     * @param <T>            the actual type of the target objects
     * @return a list of random instances of the given type
     */
    public static <T> List<T> randomListOf(int amount, Class<? extends T> type, String... excludedFields) {
        return randomStreamOf(amount, type, excludedFields).collect(toList());
    }

    /**
     * Generate a {@link Set} of random instances of the given type.
     *
     * @param amount         the number of instances to generate
     * @param type           the type for which instances will be generated
     * @param excludedFields the name of fields to exclude
     * @param <T>            the actual type of the target objects
     * @return a set of random instances of the given type
     */
    public static <T> Set<T> randomSetOf(int amount, Class<T> type, String... excludedFields) {
        return randomStreamOf(amount, type, excludedFields).collect(toSet());
    }
}
