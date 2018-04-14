package Perceptron;

public class Tweet {
	private final long id;
    private final double content;

    public Tweet(long id, double content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public double getContent() {
        return content;
    }
}
