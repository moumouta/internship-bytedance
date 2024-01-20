package com.bytedance.press_REST;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PressService implements IPressService{

    private List<Press> presses;

    // Constructor to initialize the in-memory list
/*    public void PressServiceImpl()
    {*/
    // Constructor to initialize the in-memory list
    public void PressServiceImpl() {
        presses = new ArrayList<>();
        presses.add(new Press(100L, "Mobile", "CLK98123"));
        presses.add(new Press(101L, "Smart TV", "LGST09167"));
        presses.add(new Press(102L, "Washing Machine", "38753BK9"));
        presses.add(new Press(103L, "Laptop", "LHP29OCP"));
        presses.add(new Press(104L, "Air Conditioner", "ACLG66721"));
        presses.add(new Press(105L, "Refrigerator ", "12WP9087"));
    }
//        //returns a list of product
//        return presses;
    /*}*/

    @Override
    public List<Press> findAll() {
        PressServiceImpl();
        return presses;
    }

    @Override
    public Optional<Press> getPressById(Long id) {
        PressServiceImpl();
        return presses.stream()
                .filter(press -> press.getId().equals(id))
                .findFirst();
    }

    /*
    private final Map<Long, Press> pressMap = new HashMap<>();

    public PressService() {
        // Initialize with some sample data
        Press press1 = new Press(1L, "I am the title", "I am the content!");
        pressMap.put(press1.getId(), press1);
    }

    @Override
    public Press getPressById(Long id) {
        return pressMap.get(id);
    }
    */
}
