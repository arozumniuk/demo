package io.result.randomizer;

import com.github.javafaker.Faker;
import io.github.benas.randombeans.api.Randomizer;

public class StringsRandomizer implements Randomizer<String> {
    @Override
    public String getRandomValue() {
        return new Faker().lorem().characters(1, 15, true, true);
    }
}
