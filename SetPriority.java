public class SetPriority extends Object {
  private static Runnable makeRunnable() {
    Runnable r = new Runnable() {
      public void run() {
        for (int i = 0; i < 10; i++) {
          Thread t = Thread.currentThread();
          System.out.println("in run() - priority=" + t.getPriority()
              + ", name=" + t.getName());
          try {
            Thread.yield();
            //Thread.sleep(2000);
          } catch (Exception x) {
          }
        }
      }
    };

    return r;
  }

  public static void main(String[] args) {
    Thread threadA = new Thread(makeRunnable(), "threadA");
    threadA.setPriority(8);
    threadA.start();
    
    Thread threadA1 = new Thread(makeRunnable(), "threadA1");
    threadA1.setPriority(8);
    threadA1.start();
    
    Thread threadA2 = new Thread(makeRunnable(), "threadA2");
    threadA2.setPriority(8);
    threadA2.start();
    
    Thread threadA3 = new Thread(makeRunnable(), "threadA3");
    threadA3.setPriority(8);
    threadA3.start();
    
    Thread threadA4 = new Thread(makeRunnable(), "threadA4");
    threadA4.setPriority(8);
    threadA4.start();

    Thread threadB = new Thread(makeRunnable(), "threadB");
    threadB.setPriority(2);
    threadB.start();

    Runnable r = new Runnable() {
      public void run() {
        Thread threadC = new Thread(makeRunnable(), "threadC");
        threadC.start();
      }
    };
    Thread threadD = new Thread(r, "threadD");
    threadD.setPriority(10);
    threadD.start();

		/*
    try {
      Thread.sleep(3000);
    } catch (InterruptedException x) {
  	}
  	*/

    threadA.setPriority(1);
    System.out.println("in main() - threadA.getPriority()="
        + threadA.getPriority());
    threadA1.setPriority(1);
    System.out.println("in main() - threadA.getPriority()="
        + threadA.getPriority());
    threadA2.setPriority(1);
    System.out.println("in main() - threadA.getPriority()="
        + threadA.getPriority());
    threadA3.setPriority(1);
    System.out.println("in main() - threadA.getPriority()="
        + threadA.getPriority());
    threadA4.setPriority(1);
    System.out.println("in main() - threadA.getPriority()="
        + threadA.getPriority());
        
    try {
      Thread.sleep(3000);
    } catch (InterruptedException x) {
    }
  }
}