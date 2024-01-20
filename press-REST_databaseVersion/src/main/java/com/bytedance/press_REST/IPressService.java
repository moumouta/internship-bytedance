package com.bytedance.press_REST;
import java.util.List;
import java.util.Optional;

public interface IPressService {
    List<Press> findAll();
    Optional<Press> getPressById(Long id);

//    Press getPressById(Long id);
}
