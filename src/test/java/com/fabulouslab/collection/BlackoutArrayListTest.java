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

    @Test
    public void should_remove_a_value_by_index() throws Exception {

        BlackoutIntegerArray blackoutArrayList = new BlackoutIntegerArray(new int[]{1,7,10});
        Integer removedValue = blackoutArrayList.remove(1);

        assertThat(blackoutArrayList.size()).isEqualTo(2);
        assertThat(removedValue).isEqualTo(7);
        assertThat(blackoutArrayList.get(0)).isEqualTo(1);
        assertThat(blackoutArrayList.get(1)).isEqualTo(10);
    }

    @Test
    public void should_last_element_by_index() throws Exception {

        BlackoutIntegerArray blackoutArrayList = new BlackoutIntegerArray(new int[]{1,7,10});
        Integer removedValue = blackoutArrayList.remove(2);

        assertThat(blackoutArrayList.size()).isEqualTo(2);
        assertThat(removedValue).isEqualTo(10);
        assertThat(blackoutArrayList.get(0)).isEqualTo(1);
        assertThat(blackoutArrayList.get(1)).isEqualTo(7);
    }

    @Test(expected = NullPointerException.class)
    public void should_get_first_index_of_a_number() throws Exception {

        BlackoutIntegerArray blackoutArrayList = new BlackoutIntegerArray(new int[]{1,7,10});

        assertThat(blackoutArrayList.indexOf(1)).isEqualTo(0);
        assertThat(blackoutArrayList.indexOf(7)).isEqualTo(1);
        assertThat(blackoutArrayList.indexOf(10)).isEqualTo(2);
        assertThat(blackoutArrayList.indexOf(-1)).isEqualTo(-1);
        assertThat(blackoutArrayList.indexOf(null)).isEqualTo(null);
    }

    @Test
    public void should_get_a_iterrator() throws Exception {

        BlackoutIntegerArray blackoutArrayList = new BlackoutIntegerArray(new int[]{0,1,2,3});

        for (Integer i : blackoutArrayList){
           assertThat(i).isEqualTo(blackoutArrayList.get(i));
        }
    }

}