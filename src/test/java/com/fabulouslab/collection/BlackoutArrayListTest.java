package com.fabulouslab.collection;

import org.junit.Test;

import java.util.Arrays;

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

    @Test
    public void should_get_a_array_in_heap_of_off_heap_array() throws Exception {

        BlackoutIntegerArray blackoutArrayList = new BlackoutIntegerArray(new int[]{10,-1,0,3});
        Object[] onHeapArray = blackoutArrayList.toArray();

        assertThat(onHeapArray.length).isEqualTo(4);
        assertThat(onHeapArray[0]).isEqualTo(blackoutArrayList.get(0));
        assertThat(onHeapArray[1]).isEqualTo(blackoutArrayList.get(1));
        assertThat(onHeapArray[2]).isEqualTo(blackoutArrayList.get(2));
        assertThat(onHeapArray[3]).isEqualTo(blackoutArrayList.get(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void should_get_a_empty_array_in_heap_of_emplty_off_heap_array() throws Exception {

        BlackoutIntegerArray blackoutArrayList = new BlackoutIntegerArray(new int[]{});
        Object[] onHeapArray = blackoutArrayList.toArray();

        assertThat(onHeapArray.length).isEqualTo(0);
        assertThat(onHeapArray[0]).isEqualTo(blackoutArrayList.get(0));
    }

    @Test
    public void should_remove_object() throws Exception {

        BlackoutIntegerArray blackoutArrayList = new BlackoutIntegerArray(new int[]{10,-1,0,3});
        boolean remove = blackoutArrayList.remove(new Integer(10));

        assertThat(remove).isTrue();
        assertThat(blackoutArrayList.size()).isEqualTo(3);
        assertThat(blackoutArrayList.get(0)).isEqualTo(-1);
        assertThat(blackoutArrayList.get(1)).isEqualTo(0);
        assertThat(blackoutArrayList.get(2)).isEqualTo(3);
    }

    @Test
    public void should_not_remove_a_not_found_object() throws Exception {

        BlackoutIntegerArray blackoutArrayList = new BlackoutIntegerArray(new int[]{10, -1, 0, 3});
        boolean remove = blackoutArrayList.remove(new Integer(2));

        assertThat(remove).isFalse();
        assertThat(blackoutArrayList.size()).isEqualTo(4);
    }

    @Test(expected = NullPointerException.class)
    public void should_throw_exception_if_object_is_null() throws Exception {

        BlackoutIntegerArray blackoutArrayList = new BlackoutIntegerArray(new int[]{10,-1,0,3});
        boolean remove = blackoutArrayList.remove(null);
    }

    @Test
    public void should_set_new_value() throws Exception {

        BlackoutIntegerArray blackoutArrayList = new BlackoutIntegerArray(new int[]{10,-1,0,3});
        int  oldValue = blackoutArrayList.set(2,2);

        assertThat(oldValue).isEqualTo(0);
        assertThat(blackoutArrayList.get(2)).isEqualTo(2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void should_clear_list() throws Exception {

        BlackoutIntegerArray blackoutArrayList = new BlackoutIntegerArray(new int[]{10,-1,0,3});
        blackoutArrayList.clear();

        assertThat(blackoutArrayList.size()).isEqualTo(0);
        assertThat(blackoutArrayList.get(0)).isEqualTo(2);
    }

    @Test
    public void should_return_the_last_index_of_the_given_input() throws Exception {
        BlackoutIntegerArray blackoutIntegerArray = new BlackoutIntegerArray(new int[]{10, 2, 3, 10});

        int lastIndex = blackoutIntegerArray.lastIndexOf(10);

        assertThat(lastIndex).isEqualTo(3);
    }

    @Test
    public void should_return_the_last_index_of_the_given_input_when_it_is_in_the_middle_of_the_list() throws Exception {
        BlackoutIntegerArray blackoutIntegerArray = new BlackoutIntegerArray(new int[]{10, 2, 3, 10});

        int lastIndex = blackoutIntegerArray.lastIndexOf(3);

        assertThat(lastIndex).isEqualTo(2);
    }

    @Test
    public void should_return_minus_one_if_the_given_input_is_not_in_the_list() throws Exception {
        BlackoutIntegerArray blackoutIntegerArray = new BlackoutIntegerArray(new int[]{10, 2, 3, 10});

        int lastIndex = blackoutIntegerArray.lastIndexOf(1);

        assertThat(lastIndex).isEqualTo(-1);
    }

    @Test
    public void should_remove_all_given_element_from_the_list() throws Exception {
        BlackoutIntegerArray blackoutIntegerArray = new BlackoutIntegerArray(new int[]{1, 2, 3, 11});

        boolean removed = blackoutIntegerArray.removeAll(Arrays.asList(2, 3));

        assertThat(removed).isTrue();
        assertThat(blackoutIntegerArray).hasSize(2);
        assertThat(blackoutIntegerArray).containsExactly(1, 11);
    }

    @Test
    public void should_remove_only_existing_elements_from_the_list() throws Exception {
        BlackoutIntegerArray blackoutIntegerArray = new BlackoutIntegerArray(new int[]{1, 2, 3, 11});

        boolean removed = blackoutIntegerArray.removeAll(Arrays.asList(2, 4, 11, 22));

        assertThat(removed).isTrue();
        assertThat(blackoutIntegerArray).hasSize(2);
        assertThat(blackoutIntegerArray).containsExactly(1, 3);
    }

    @Test
    public void should_return_false_when_given_element_are_not_in_list() throws Exception {
        BlackoutIntegerArray blackoutIntegerArray = new BlackoutIntegerArray(new int[]{1, 2, 3, 11});

        boolean removed = blackoutIntegerArray.removeAll(Arrays.asList(22, 20));

        assertThat(removed).isFalse();
    }
}