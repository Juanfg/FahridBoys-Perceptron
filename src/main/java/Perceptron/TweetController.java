package Perceptron;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TweetController {
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/tweets")
    public Tweet index() {
    	Perceptron perceptron = new Perceptron();
    	return new Tweet(counter.incrementAndGet(), perceptron.simpleWekaTrain("3", .1, .1, 500));
    }
}
