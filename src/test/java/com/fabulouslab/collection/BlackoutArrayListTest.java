package com.fabulouslab.collection;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class BlackoutArrayListTest {

    @Test
    public void should_get_3_as_size() throws Exception {

        BlackoutIntegerArray blackoutArrayList = new BlackoutIntegerArray(new int[]{1,2,3});

        assertThat(blackoutArrayList.size()).isEqualTo(3);
        assertThat(blackoutArrayList.isEmpty()).isFalse();
        assertThat(blackoutArrayList.get(0)).isEqualTo(1);
        assertThat(blackoutArrayList.get(1)).isEqualTo(2);
        assertThat(blackoutArrayList.get(2)).isEqualTo(3);
    }

    @Test
    public void should_get_zero_for_empty_array() throws Exception {

        BlackoutIntegerArray blackoutArrayList = new BlackoutIntegerArray(new int[]{});
        assertThat(blackoutArrayList.size()).isEqualTo(0);
        assertThat(blackoutArrayList.isEmpty()).isTrue();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void should_check_out_of_bound() throws Exception {

        BlackoutIntegerArray blackoutArrayList = new BlackoutIntegerArray(new int[]{1});
        blackoutArrayList.get(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_for_size_0() throws Exception {

        BlackoutIntegerArray blackoutArrayList = new BlackoutIntegerArray(-1);
    }

    @Test
    public void should_remove_a_value_by_index() throws Exception {

        BlackoutIntegerArray blackoutArrayList = new BlackoutIntegerArray(new int[]{1,7,10});
        Integer removedValue = blackoutArrayList.remove(1);

        assertThat(blackoutArrayList.size()).isEqualTo(2);
        assertThat(removedValue).isEqualTo(7);
        assertThat(blackoutArrayList.get(0)).isEqualTo(1);
        assertThat(blackoutArrayList.get(1)).isEqualTo(10);
    }

}