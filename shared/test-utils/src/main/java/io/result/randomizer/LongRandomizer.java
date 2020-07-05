package io.result.randomizer;

import com.github.javafaker.Faker;
import io.github.benas.randombeans.api.Randomizer;

public class LongRandomizer implements Randomizer<Long> {

    @Override
    public Long getRandomValue() {
        return Math.abs(new Faker().random().nextLong());
    }
}
