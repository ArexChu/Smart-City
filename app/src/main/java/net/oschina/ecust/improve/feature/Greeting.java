package net.oschina.ecust.improve.feature;


public class Greeting {

    public int id;
    public String content;
    public int size;

    @Override
    public String toString() {
        return "Greeting{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", size=" + size +
                '}';
    }
}
