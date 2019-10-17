package com.ies.spring.demo;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingRestController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting-rest")
    public GreetingRest greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new GreetingRest(counter.incrementAndGet(),
                            String.format(template, name));
    }
}