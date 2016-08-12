package com.fabulouslab.util;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryTest {

    private static final int INTEGER_LENGHT = 4;

    @Test
    public void should_swap_values_in_backoutarray() throws Exception {

        long firstAddr = Memory.allocate(INTEGER_LENGHT);
        long secondAddr = Memory.allocate(INTEGER_LENGHT);

        Memory.putInt(firstAddr, 1);
        Memory.putInt(secondAddr, 2);
        Memory.swap(firstAddr, secondAddr, INTEGER_LENGHT);

        assertThat(Memory.getInt(firstAddr)).isEqualTo(2);
        assertThat(Memory.getInt(secondAddr)).isEqualTo(1);

        Memory.free(firstAddr);
        Memory.free(secondAddr);
    }

}