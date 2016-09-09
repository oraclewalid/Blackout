package com.fabulouslab.collection;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackoutLongListTest {

    @Test
    public void should_get_3_as_size() throws Exception {

        BlackoutLongList blackoutList = new BlackoutLongList(0,1,2_000_000_000);

        assertThat(blackoutList.size()).isEqualTo(3);
        assertThat(blackoutList.isEmpty()).isFalse();
        assertThat(blackoutList.get(0)).isEqualTo(0);
        assertThat(blackoutList.get(1)).isEqualTo(1);
        assertThat(blackoutList.get(2)).isEqualTo(2_000_000_000);
    }

    @Test
    public void should_set_new_value() throws Exception {

        BlackoutLongList blackoutList = new BlackoutLongList(0,1,2_000_000_000);
        long oldValue = blackoutList.set(2, 3_000_000_000l);

        assertThat(oldValue).isEqualTo(2_000_000_000);
        assertThat(blackoutList.get(2)).isEqualTo(3_000_000_000l);
    }

}