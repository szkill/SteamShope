package ru.steamstore.main.utils;

import java.util.Optional;
import java.util.function.Consumer;

public class MyOptional<T> {

    private Optional<T> optional;

    private MyOptional(Optional<T> optional) {
        this.optional = optional;
    }

    public static <T> MyOptional<T> of(Optional<T> optional) {
        return new MyOptional<>(optional);
    }

    public MyOptional<T> ifPresent(Consumer<T> c) {
        optional.ifPresent(c);
        return this;
    }

    public MyOptional<T> orElse(Runnable r) {
        if (!optional.isPresent())
            r.run();
        return this;
    }
}
