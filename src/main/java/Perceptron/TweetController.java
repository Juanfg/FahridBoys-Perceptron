package Perceptron;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TweetController {
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(method = RequestMethod.POST, value = "/tweets")
    public String index(@RequestParam(value = "text", required = false) String tweets) {
    	Perceptron perceptron = new Perceptron();
//    	return new Tweet(counter.incrementAndGet(), perceptron.simpleWekaTrain("3", .1, .1, 500));
    	return tweets;
    }
}
