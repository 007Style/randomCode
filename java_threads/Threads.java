// package cx.rewind.util;

public class Threads {

	static final double PERFORMANCE_INCREASE = 0.75;

	static final int MAX_THREADS = 256;

	static final long PREFERRED_RUN_TIME = 150;

	static final int INITIAL_COUNT = 0x100;

	static final int COUNT = calcCount();

	public static final int PERFORMANCE_INDEX =
			(int) ((Math.log(COUNT) - Math.log(INITIAL_COUNT)) / Math.log(2));

	static int calcCount() {
		int count = INITIAL_COUNT;
		while (new Timed(count).getRunTime() < PREFERRED_RUN_TIME)
			count *= 2;
		return count;
	}

	public static int getOptimalCount(boolean printTests) {
		double lastAverage = Double.MAX_VALUE;
		double newAverage;
		int i = 1;
		do {
			newAverage = getAverageRunTime(i);
			if (printTests)
				System.out.println((float) newAverage + " ms per thread at " + i + " thread(s)");
			if (!isPerformanceIncrease(lastAverage, i / 2, newAverage, i))
				break;
			lastAverage = newAverage;
			i *= 2;
		} while (i <= MAX_THREADS);
		return i / 2;
	}

	static double getAverageRunTime(int threads) {
		System.gc();

		Timed[] timed = new Timed[threads];
		for (int i = 0; i < threads; i++)
			timed[i] = new Timed(COUNT);

		long totalRunTime = 0;
		for (int i = 0; i < threads; i++)
			totalRunTime += timed[i].getRunTime();

		return (double) totalRunTime / threads;
	}

	static boolean isPerformanceIncrease(double lastAverage, int lastThreads,
			double newAverage, int newThreads) {
		if (lastThreads == 0)
			return true;
		double timeRatio = lastAverage / newAverage;
		double threadRatio = (double) newThreads / lastThreads;
		return (timeRatio >= threadRatio * PERFORMANCE_INCREASE);
	}

	public static void main(String[] args) {
		System.out.println("Performace Index: " + PERFORMANCE_INDEX);
		System.out.println(getOptimalCount(true) + " thread(s) for optimal performace");
	}

	static class Timed extends Thread {

		int count;
		long startTime = 0;
		long endTime = 0;

		Timed(int count) {
			super(Threads.class.getName());
			setPriority(MAX_PRIORITY - 1);
			this.count = count;

			Thread.yield();
			start();
		}

		public synchronized void run() {
			startTime = System.currentTimeMillis();

			int[] a = new int[count];
			for (int i = 0; i < a.length; i++)
				a[i] = (int) (Math.random() * count);
			java.util.Arrays.sort(a);

			endTime = System.currentTimeMillis();
			notifyAll();
		}

		synchronized long getRunTime() {
			if (endTime == 0) try {
				wait();
			} catch (InterruptedException e) {}
			return endTime - startTime;
		}

	}

}
