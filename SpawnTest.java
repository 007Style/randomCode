  // Java 5 - uses a BlockingQueue.
    import java.util.concurrent.*;

    public class SpawnTest extends Thread {
        public static void main(String[] args) {
            int M = Integer.parseInt(args.length > 0 ? args[0] : "1");
            int N = Integer.parseInt(args.length > 1 ? args[1] : "1000000");
            int NpM = N / M;
            BlockingQueue queue = new LinkedBlockingQueue();
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < M; i++) { new Body(queue, NpM).start(); }
            for (int i = 0; i < M; i++) { try { queue.take(); } catch (InterruptedException ie) {} }
            long stopTime = System.currentTimeMillis();
            System.out.println((NpM * M) / ((stopTime - startTime) / 1000.0));
        }

        public static class Body extends Thread {
            BlockingQueue queue;
            int count;
            public Body(BlockingQueue queue, int count) {
                this.queue = queue;
                this.count = count;
            }
            public void run() {
                if (count == 0) {
                    try { queue.put(this); } catch (InterruptedException ie) {}
                } else {
                    new Body(queue, count - 1).start();
                }
            }
        }
    }
