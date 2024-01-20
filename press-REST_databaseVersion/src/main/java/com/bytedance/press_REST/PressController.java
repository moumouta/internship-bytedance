package com.bytedance.press_REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/press")
public class PressController {
    @Autowired
    private IPressService pressService;

/*    // meaningless, please ses the latest version
    @GetMapping("/{id}")
    public Press getPressById(@PathVariable Long id) {
        return pressService.getPressById(id);
    }*/

    @GetMapping(value = "/Press")
    public List<Press> getAllPress()
    {
        //查找所有产品
        List<Press> presses = pressService.findAll();

        //返回产品列表
        return presses;
    }

    @GetMapping("/{id}")
    public Press getPressById(@PathVariable Long id) {
        Optional<Press> press = pressService.getPressById(id);
        return press.orElse(null);
    }
}
