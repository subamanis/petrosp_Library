package com.petrosp.assist.util;

import java.util.Objects;
import java.util.function.Function;

public interface PTriFunction<I1,I2,I3,O>
{
    O apply(I1 in1, I2 in2, I3 in3);

    default <V> PTriFunction<I1, I2, I3, V> andThen(Function<? super O, ? extends V> after)
    {
        Objects.requireNonNull(after);
        return (I1 i1, I2 i2, I3 i3) -> after.apply(apply(i1, i2, i3));
    }

}
