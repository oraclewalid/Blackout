package com.fabulouslab.collection;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackoutByteListTest {

    @Test
    public void should_get_3_as_size() throws Exception {

        BlackoutByteList blackoutList = new BlackoutByteList((byte)0,(byte)1,(byte)2);

        assertThat(blackoutList.size()).isEqualTo(3);
        assertThat(blackoutList.get(0)).isEqualTo((byte)0);
        assertThat(blackoutList.get(1)).isEqualTo((byte)1);
        assertThat(blackoutList.get(2)).isEqualTo((byte)2);
    }

    @Test
    public void should_set_new_value() throws Exception {

        BlackoutByteList blackoutList = new BlackoutByteList((byte)0,(byte)1,(byte)2);
        byte oldValue = blackoutList.set(2,(byte)3);

        assertThat(oldValue).isEqualTo((byte) 2);
        assertThat(blackoutList.get(2)).isEqualTo((byte)3);
    }

}