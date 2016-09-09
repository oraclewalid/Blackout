package com.fabulouslab.collection;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackoutShortListTest {

    @Test
    public void should_get_3_as_size() throws Exception {

        BlackoutShortList blackoutList = new BlackoutShortList((short) 0,(short) 1,(short) 2);

        assertThat(blackoutList.size()).isEqualTo(3);
        assertThat(blackoutList.isEmpty()).isFalse();
        assertThat(blackoutList.get(0)).isEqualTo((short) 0);
        assertThat(blackoutList.get(1)).isEqualTo((short) 1);
        assertThat(blackoutList.get(2)).isEqualTo((short) 2);
    }

    @Test
    public void should_set_new_value() throws Exception {

        BlackoutShortList blackoutList = new BlackoutShortList((short) 0,(short) 1,(short) 2);
        short oldValue = blackoutList.set(2,(short) 3);

        assertThat(oldValue).isEqualTo((short) 2);
        assertThat(blackoutList.get(2)).isEqualTo((short) 3);
    }

}