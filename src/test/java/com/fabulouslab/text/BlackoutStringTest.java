package com.fabulouslab.text;


import org.junit.Test;
import static org.assertj.core.api.Assertions.*;


public class BlackoutStringTest {

    BlackoutString toto = new BlackoutString("toto");

    @Test
    public void length() throws Exception {

        assertThat(toto.length()).isEqualTo(4);
    }

    @Test
    public void charAt() throws Exception {

        assertThat(toto.charAt(0)).isEqualTo('t');
        assertThat(toto.charAt(1)).isEqualTo('o');
        assertThat(toto.charAt(2)).isEqualTo('t');
        assertThat(toto.charAt(3)).isEqualTo('o');
    }

    @Test
    public void subSequence() throws Exception {

        assertThat(toto.subSequence(0,1)).isEqualTo("to");
        assertThat(toto.subSequence(1,3)).isEqualTo("oto");
        assertThat(toto.subSequence(0,3)).isEqualTo("toto");
    }

    @Test
    public void souldtoString() throws Exception {
        assertThat(toto.toString()).contains("toto");
    }
}