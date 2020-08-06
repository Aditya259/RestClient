

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class FastHTTP {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(25);

        for (int i = 0; i < 25; i++) {
            LinkedBlockingQueue queue = new LinkedBlockingQueue();
            queue.add("http://localhost:8090/test/");//for example
            executor.execute(new HTTPworker(queue));
        }
    }

}
