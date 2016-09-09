package com.fabulouslab.collection;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackoutFloatListTest {

    @Test
    public void should_get_3_as_size() throws Exception {

        BlackoutFloatList blackoutList = new BlackoutFloatList(0.0f,1.0f,2.0f);

        assertThat(blackoutList.size()).isEqualTo(3);
        assertThat(blackoutList.isEmpty()).isFalse();
        assertThat(blackoutList.get(0)).isEqualTo(0.0f);
        assertThat(blackoutList.get(1)).isEqualTo(1.0f);
        assertThat(blackoutList.get(2)).isEqualTo(2.0f);
    }

    @Test
    public void should_set_new_value() throws Exception {

        BlackoutFloatList blackoutList = new BlackoutFloatList(0.0f,1.0f,2.0f);
        float oldValue = blackoutList.set(2,3.0f);

        assertThat(oldValue).isEqualTo(2.0f);
        assertThat(blackoutList.get(2)).isEqualTo(3.0f);
    }

}