package com.petrosp.assist.util;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class TriFunctionTest
{
    @Test
    void apply()
    {
        PTriFunction<Integer, Integer, Integer, Integer> add = ((i1, i2, i3) -> i1+i2+i3);
        assertEquals(3, add.apply(1,1,1));
        assertEquals(-5, add.apply(-5,0,0));
    }

    @Test
    void andThen()
    {
        Function<Integer, Integer> negate = (i -> -i);
        PTriFunction<Integer, Integer, Integer, Integer> add = ((i1, i2, i3) -> i1+i2+i3);
        PTriFunction<Integer, Integer, Integer, Integer> negateAddition = add.andThen(negate);

        assertEquals(-3, negateAddition.apply(1,1,1));
    }
}
