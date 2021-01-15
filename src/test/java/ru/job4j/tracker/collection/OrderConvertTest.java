package ru.job4j.tracker.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class OrderConvertTest {

    @Test
    public void process() {
        List<Order> order = new ArrayList<>();
        order.add(new Order("0001", "Zenbook"));
        HashMap<String, Order> map = OrderConvert.process(order);
        assertThat(map.get("0001"), is(new Order("0001", "Zenbook")));
    }
}