package dahai;

import org.eclipse.jetty.util.thread.ExecutorThreadPool;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test0507 {
    public static void main(String[] args) throws InterruptedException {
        final HashMap<String, String> map = new HashMap<String, String>();
//        for (int i = 0; i < 1000; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    map.put(UUID.randomUUID().toString(), Thread.currentThread().getName());
//                }
//            }).start();
//        }
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    map.put(UUID.randomUUID().toString(), Thread.currentThread().getName());
                }
            });
        }
        Thread.sleep(1000l);
        executorService.shutdown();
        Set<Map.Entry<String, String>> entries = map.entrySet();
        entries.forEach(System.out::println);
    }
}
