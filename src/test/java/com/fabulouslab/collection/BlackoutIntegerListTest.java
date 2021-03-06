package com.fabulouslab.collection;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.*;

import org.junit.Test;

public class BlackoutIntegerListTest {

    @Test
    public void should_get_3_as_size() throws Exception {

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{1,2,3});

        assertThat(blackoutArrayList.size()).isEqualTo(3);
        assertThat(blackoutArrayList.isEmpty()).isFalse();
        assertThat(blackoutArrayList.get(0)).isEqualTo(1);
        assertThat(blackoutArrayList.get(1)).isEqualTo(2);
        assertThat(blackoutArrayList.get(2)).isEqualTo(3);
    }

    @Test
    public void should_get_zero_for_empty_array() throws Exception {

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{});
        assertThat(blackoutArrayList.size()).isEqualTo(0);
        assertThat(blackoutArrayList.isEmpty()).isTrue();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void should_check_out_of_bound() throws Exception {

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{1});
        blackoutArrayList.get(1);
    }

    @Test
    public void should_remove_a_value_by_index() throws Exception {

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{1,7,10});
        Integer removedValue = blackoutArrayList.remove(1);

        assertThat(blackoutArrayList.size()).isEqualTo(2);
        assertThat(removedValue).isEqualTo(7);
        assertThat(blackoutArrayList.get(0)).isEqualTo(1);
        assertThat(blackoutArrayList.get(1)).isEqualTo(10);
    }

    @Test
    public void should_last_element_by_index() throws Exception {

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{1,7,10});
        Integer removedValue = blackoutArrayList.remove(2);

        assertThat(blackoutArrayList.size()).isEqualTo(2);
        assertThat(removedValue).isEqualTo(10);
        assertThat(blackoutArrayList.get(0)).isEqualTo(1);
        assertThat(blackoutArrayList.get(1)).isEqualTo(7);
    }

    @Test(expected = NullPointerException.class)
    public void should_get_first_index_of_a_number() throws Exception {

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{1,7,10});

        assertThat(blackoutArrayList.indexOf(1)).isEqualTo(0);
        assertThat(blackoutArrayList.indexOf(7)).isEqualTo(1);
        assertThat(blackoutArrayList.indexOf(10)).isEqualTo(2);
        assertThat(blackoutArrayList.indexOf(-1)).isEqualTo(-1);
        assertThat(blackoutArrayList.indexOf(null)).isEqualTo(null);
    }

    @Test
    public void should_get_a_iterrator() throws Exception {

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{0,1,2,3});

        for (Integer i : blackoutArrayList){
           assertThat(i).isEqualTo(blackoutArrayList.get(i));
        }
    }

    @Test
    public void should_get_a_array_in_heap_of_off_heap_array() throws Exception {

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{10,-1,0,3});
        Object[] onHeapArray = blackoutArrayList.toArray();

        assertThat(onHeapArray.length).isEqualTo(4);
        assertThat(onHeapArray[0]).isEqualTo(blackoutArrayList.get(0));
        assertThat(onHeapArray[1]).isEqualTo(blackoutArrayList.get(1));
        assertThat(onHeapArray[2]).isEqualTo(blackoutArrayList.get(2));
        assertThat(onHeapArray[3]).isEqualTo(blackoutArrayList.get(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void should_get_a_empty_array_in_heap_of_emplty_off_heap_array() throws Exception {

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{});
        Object[] onHeapArray = blackoutArrayList.toArray();

        assertThat(onHeapArray.length).isEqualTo(0);
        assertThat(onHeapArray[0]).isEqualTo(blackoutArrayList.get(0));
    }

    @Test
    public void should_remove_object() throws Exception {

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{10,-1,0,3});
        boolean remove = blackoutArrayList.remove(new Integer(10));

        assertThat(remove).isTrue();
        assertThat(blackoutArrayList.size()).isEqualTo(3);
        assertThat(blackoutArrayList.get(0)).isEqualTo(-1);
        assertThat(blackoutArrayList.get(1)).isEqualTo(0);
        assertThat(blackoutArrayList.get(2)).isEqualTo(3);
    }

    @Test
    public void should_not_remove_a_not_found_object() throws Exception {

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{10, -1, 0, 3});
        boolean remove = blackoutArrayList.remove(new Integer(2));

        assertThat(remove).isFalse();
        assertThat(blackoutArrayList.size()).isEqualTo(4);
    }

    @Test(expected = NullPointerException.class)
    public void should_throw_exception_if_object_is_null() throws Exception {

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{10,-1,0,3});
        boolean remove = blackoutArrayList.remove(null);
    }

    @Test
    public void should_set_new_value() throws Exception {

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{10,-1,0,3});
        int  oldValue = blackoutArrayList.set(2,2);

        assertThat(oldValue).isEqualTo(0);
        assertThat(blackoutArrayList.get(2)).isEqualTo(2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void should_clear_list() throws Exception {

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{10,-1,0,3});
        blackoutArrayList.clear();

        assertThat(blackoutArrayList.size()).isEqualTo(0);
        assertThat(blackoutArrayList.get(0)).isEqualTo(2);
    }

    @Test
    public void should_return_the_last_index_of_the_given_input() throws Exception {
        BlackoutIntegerList blackoutIntegerArray = new BlackoutIntegerList(new int[]{10, 2, 3, 10});

        int lastIndex = blackoutIntegerArray.lastIndexOf(10);

        assertThat(lastIndex).isEqualTo(3);
    }

    @Test
    public void should_return_the_last_index_of_the_given_input_when_it_is_in_the_middle_of_the_list() throws Exception {
        BlackoutIntegerList blackoutIntegerArray = new BlackoutIntegerList(new int[]{10, 2, 3, 10});

        int lastIndex = blackoutIntegerArray.lastIndexOf(3);

        assertThat(lastIndex).isEqualTo(2);
    }

    @Test
    public void should_return_minus_one_if_the_given_input_is_not_in_the_list() throws Exception {
        BlackoutIntegerList blackoutIntegerArray = new BlackoutIntegerList(new int[]{10, 2, 3, 10});

        int lastIndex = blackoutIntegerArray.lastIndexOf(1);

        assertThat(lastIndex).isEqualTo(-1);
    }

    @Test
    public void should_remove_all_given_element_from_the_list() throws Exception {
        BlackoutIntegerList blackoutIntegerArray = new BlackoutIntegerList(new int[]{1, 2, 3, 11});

        boolean removed = blackoutIntegerArray.removeAll(Arrays.asList(2, 3));

        assertThat(removed).isTrue();
        assertThat(blackoutIntegerArray).hasSize(2);
        assertThat(blackoutIntegerArray).containsExactly(1, 11);
    }

    @Test
    public void should_remove_only_existing_elements_from_the_list() throws Exception {
        BlackoutIntegerList blackoutIntegerArray = new BlackoutIntegerList(new int[]{1, 2, 3, 11});

        boolean removed = blackoutIntegerArray.removeAll(Arrays.asList(2, 4, 11, 22));

        assertThat(removed).isTrue();
        assertThat(blackoutIntegerArray).hasSize(2);
        assertThat(blackoutIntegerArray).containsExactly(1, 3);
    }

    @Test
    public void should_return_false_when_given_element_are_not_in_list() throws Exception {
        BlackoutIntegerList blackoutIntegerArray = new BlackoutIntegerList(new int[]{1, 2, 3, 11});

        boolean removed = blackoutIntegerArray.removeAll(Arrays.asList(22, 20));

        assertThat(removed).isFalse();
    }

    @Test
    public void should_add_collection_with_index() throws Exception {

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{10,-1,1,3,-2});
        List<Integer> listToAdd = new ArrayList(){{
            add(1);
            add(2);
        }};
        blackoutArrayList.addAll(2, listToAdd);

        assertThat(blackoutArrayList.size()).isEqualTo(7);
        assertThat(blackoutArrayList.get(0)).isEqualTo(10);
        assertThat(blackoutArrayList.get(1)).isEqualTo(-1);
        assertThat(blackoutArrayList.get(2)).isEqualTo(1);
        assertThat(blackoutArrayList.get(3)).isEqualTo(2);
        assertThat(blackoutArrayList.get(4)).isEqualTo(1);
        assertThat(blackoutArrayList.get(5)).isEqualTo(3);
        assertThat(blackoutArrayList.get(6)).isEqualTo(-2);
    }

    @Test
    public void should_add_collection_in_the_end() throws Exception {

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{10,-1});
        List<Integer> listToAdd = new ArrayList(){{
            add(1);
            add(2);
        }};
        blackoutArrayList.addAll(listToAdd);

        assertThat(blackoutArrayList.size()).isEqualTo(4);
        assertThat(blackoutArrayList.get(0)).isEqualTo(10);
        assertThat(blackoutArrayList.get(1)).isEqualTo(-1);
        assertThat(blackoutArrayList.get(2)).isEqualTo(1);
        assertThat(blackoutArrayList.get(3)).isEqualTo(2);
    }

    @Test
    public void should_add_collection_in_middle() throws Exception {

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{10,-1});
        List<Integer> listToAdd = new ArrayList(){{
            add(1);
            add(2);
        }};
        blackoutArrayList.addAll(1, listToAdd);

        assertThat(blackoutArrayList.size()).isEqualTo(4);
        assertThat(blackoutArrayList.get(0)).isEqualTo(10);
        assertThat(blackoutArrayList.get(1)).isEqualTo(1);
        assertThat(blackoutArrayList.get(2)).isEqualTo(2);
        assertThat(blackoutArrayList.get(3)).isEqualTo(-1);
    }

    @Test
    public void should_add_collection_in_the_end_with_index() throws Exception {

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{10,-1});
        List<Integer> listToAdd = new ArrayList(){{
            add(1);
            add(2);
        }};
        blackoutArrayList.addAll(2, listToAdd);

        assertThat(blackoutArrayList.size()).isEqualTo(4);
        assertThat(blackoutArrayList.get(0)).isEqualTo(10);
        assertThat(blackoutArrayList.get(1)).isEqualTo(-1);
        assertThat(blackoutArrayList.get(2)).isEqualTo(1);
        assertThat(blackoutArrayList.get(3)).isEqualTo(2);
    }

    @Test
    public void should_add_element_in_the_end_with_index() throws Exception {

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{10,-1});
        blackoutArrayList.add(2, 3);

        assertThat(blackoutArrayList.size()).isEqualTo(3);
        assertThat(blackoutArrayList.get(0)).isEqualTo(10);
        assertThat(blackoutArrayList.get(1)).isEqualTo(-1);
        assertThat(blackoutArrayList.get(2)).isEqualTo(3);
    }

    @Test
    public void should_add_element_in_the_middle_with_index() throws Exception {

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{10,-1});
        blackoutArrayList.add(1, 3);

        assertThat(blackoutArrayList.size()).isEqualTo(3);
        assertThat(blackoutArrayList.get(0)).isEqualTo(10);
        assertThat(blackoutArrayList.get(1)).isEqualTo(3);
        assertThat(blackoutArrayList.get(2)).isEqualTo(-1);
    }

    @Test
    public void should_add_element_in_the_begin_with_index() throws Exception {

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{10, -1});
        blackoutArrayList.add(0, 3);

        assertThat(blackoutArrayList.size()).isEqualTo(3);
        assertThat(blackoutArrayList.get(0)).isEqualTo(3);
        assertThat(blackoutArrayList.get(1)).isEqualTo(10);
        assertThat(blackoutArrayList.get(2)).isEqualTo(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void should_get_IndexOutOfBoundsException_when_add_out_size() throws Exception {

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{10,-1});
        blackoutArrayList.add(3, 3);
    }

    @Test
    public void should_add_element() throws Exception {

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{10,-1});
        blackoutArrayList.add(3);
        blackoutArrayList.add(4);
        blackoutArrayList.add(5);

        assertThat(blackoutArrayList.size()).isEqualTo(5);
        assertThat(blackoutArrayList.get(0)).isEqualTo(10);
        assertThat(blackoutArrayList.get(1)).isEqualTo(-1);
        assertThat(blackoutArrayList.get(2)).isEqualTo(3);
        assertThat(blackoutArrayList.get(3)).isEqualTo(4);
        assertThat(blackoutArrayList.get(4)).isEqualTo(5);
    }

    @Test
    public void should_replace_all_element() throws Exception {

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{0,1,2});

        blackoutArrayList.replaceAll(x -> x*2);
        assertThat(blackoutArrayList.size()).isEqualTo(3);
        assertThat(blackoutArrayList.get(0)).isEqualTo(0);
        assertThat(blackoutArrayList.get(1)).isEqualTo(2);
        assertThat(blackoutArrayList.get(2)).isEqualTo(4);
    }

    @Test
    public void should_get_sublist() throws Exception {

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{0,1,2,3,4,5});
        List<Integer> subList = blackoutArrayList.subList(1, 4);
        assertThat(subList.size()).isEqualTo(3);
        assertThat(subList.get(0)).isEqualTo(1);
        assertThat(subList.get(1)).isEqualTo(2);
        assertThat(subList.get(2)).isEqualTo(3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void should_get_sublist_check_rang_toIndex() throws Exception {

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{0,1,2,3,4,5});
        List<Integer> subList = blackoutArrayList.subList(1, 7);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void should_get_sublist_check_rang_fromIndex_greater_than_toInddex() throws Exception {

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{0,1,2,3,4,5});
        List<Integer> subList = blackoutArrayList.subList(3, 2);
    }

    @Test
    public void should_remove_paire_number() throws Exception{

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{0,1,2,8,6,3,4,5});

        blackoutArrayList.removeIf(x -> x%2 == 0 );
        assertThat(blackoutArrayList.size()).isEqualTo(3);
        assertThat(blackoutArrayList.get(0)).isEqualTo(1);
        assertThat(blackoutArrayList.get(1)).isEqualTo(3);
        assertThat(blackoutArrayList.get(2)).isEqualTo(5);
    }

    @Test
    public void should_retain_a_collection() throws Exception{

        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(new int[]{0,1,2,3,4,5});

        blackoutArrayList.retainAll(Arrays.asList(1,2,5));
        assertThat(blackoutArrayList.size()).isEqualTo(3);
        assertThat(blackoutArrayList.get(0)).isEqualTo(1);
        assertThat(blackoutArrayList.get(1)).isEqualTo(2);
        assertThat(blackoutArrayList.get(2)).isEqualTo(5);
    }

    @Test
    public void sould_sort_elements() throws Exception {
        int[] init = new int[]{0,1,2,2,8,6,3,4,5};
        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(init);


        blackoutArrayList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });

        Arrays.sort(init);
        for (int i = 0; i < init.length; i++) {
            assertThat(blackoutArrayList.get(i)).isEqualTo(init[i]);
        }
    }

    @Test
    public void should_iterate_on_blackout() throws Exception {
        int[] init = new int[]{0,1,2};
        BlackoutIntegerList blackoutArrayList = new BlackoutIntegerList(init);


        ListIterator<Integer> blackoutListIterator = blackoutArrayList.listIterator();


        //hasNext and next
        assertThat(blackoutListIterator.hasNext()).isEqualTo(true);
        assertThat(blackoutListIterator.next()).isEqualTo(0);
        assertThat(blackoutListIterator.hasNext()).isEqualTo(true);
        assertThat(blackoutListIterator.next()).isEqualTo(1);
        assertThat(blackoutListIterator.hasNext()).isEqualTo(true);
        assertThat(blackoutListIterator.next()).isEqualTo(2);
        assertThat(blackoutListIterator.hasNext()).isEqualTo(false);

        // hasPrevious and previous
        assertThat(blackoutListIterator.hasPrevious()).isEqualTo(true);
        assertThat(blackoutListIterator.previous()).isEqualTo(1);

        //remove
        blackoutListIterator.remove();
        assertThat(blackoutListIterator.hasPrevious()).isEqualTo(false);
        assertThat(blackoutListIterator.next()).isEqualTo(2);

        blackoutListIterator.add(3);
        assertThat(blackoutListIterator.hasNext()).isEqualTo(true);
        assertThat(blackoutListIterator.next()).isEqualTo(3);



    }


}