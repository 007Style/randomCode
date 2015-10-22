package com.ibm.demo;

import com.ibm.lang.management.*;
import java.lang.management.*;
import java.util.*;
import javax.management.*;
import javax.management.openmbean.*;

public class VirtualizationDemo {

	com.ibm.lang.management.OperatingSystemMXBean myOSMXB = (com.ibm.lang.management.OperatingSystemMXBean) ManagementFactory
			.getOperatingSystemMXBean();

	com.ibm.lang.management.MemoryMXBean myMMXB = (com.ibm.lang.management.MemoryMXBean) ManagementFactory
			.getMemoryMXBean();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		(new VirtualizationDemo()).showStatus();
	}

	void showStatus() {
		System.out.println("---------------------------------------------");
		System.out.println("Available Processors: "
				+ myOSMXB.getAvailableProcessors());
		System.out.println("Processing Capacity: "
				+ myOSMXB.getProcessingCapacity());
		System.out.println("Total Physical Memory: "
				+ printable(myOSMXB.getTotalPhysicalMemory()));

		System.out.println("Java Heap: " + printable(myMMXB.getMinHeapSize())
				+ " (Minimum), " + printable(myMMXB.getMaxHeapSize())
				+ "(Soft Limit), " + printable(myMMXB.getMaxHeapSizeLimit())
				+ "(Maximum)");
		System.out.println("---------------------------------------------");

		OSBeanListener DLPARListener = new OSBeanListener();
		((NotificationEmitter) myOSMXB).addNotificationListener(DLPARListener,
				null, null);
		((NotificationEmitter) myMMXB).addNotificationListener(DLPARListener,
				null, null);
		System.out.println("Hit Enter anytime to terminate the demo.");
		System.out
				.println(">>> On standby for dynamic reconfiguration event ...");
		try {
			System.in.read(); // blocking call
			((NotificationEmitter) myOSMXB)
					.removeNotificationListener(DLPARListener);
			((NotificationEmitter) myMMXB)
					.removeNotificationListener(DLPARListener);
			System.out.println("This concludes the demo. Goodbye!");
		} catch (Exception ie) {
			// Ignore
		}
	}

	enum Suffixes {
		Bytes, KB, MB, GB
	};

	static String printable(long v) {
		double value = v;
		for (Suffixes s : Suffixes.values()) {
			if (value < 1024)
				return String.format("%.2f %s", value, s);

			else
				value /= 1024;
		}
		return String.format("%.2f GB", value);
	}
}

class OSBeanListener implements javax.management.NotificationListener {

	public void handleNotification(Notification notif, Object handback) {
		String s = notif.getType();
		CompositeData cd = (CompositeData) notif.getUserData();
		System.out
				.println("    =====================================================");
		System.out
				.println("    === Dynamic Reconfiguration Notification received ===");
		System.out
				.println("    =====================================================");
		System.out.println("    Notification Type: " + s);
		System.out.println("    Time             : " + (new Date()).toString());
		System.out.println("    Sequence Number  : "
				+ notif.getSequenceNumber());
		if (s
				.equals(AvailableProcessorsNotificationInfo.AVAILABLE_PROCESSORS_CHANGE)) {
			AvailableProcessorsNotificationInfo info = AvailableProcessorsNotificationInfo
					.from(cd);
			System.out.println("New Available Processors: "
					+ info.getNewAvailableProcessors());
		} else if (s
				.equals(ProcessingCapacityNotificationInfo.PROCESSING_CAPACITY_CHANGE)) {
			ProcessingCapacityNotificationInfo info = ProcessingCapacityNotificationInfo
					.from(cd);
			System.out.println("New Processing Capacity: "
					+ info.getNewProcessingCapacity());
		} else {
			TotalPhysicalMemoryNotificationInfo info = TotalPhysicalMemoryNotificationInfo
					.from(cd);
			long newPhysicalMem = info.getNewTotalPhysicalMemory();
			System.out.println("New Physical Memory: "
					+ VirtualizationDemo.printable(newPhysicalMem));
			// Adjust soft memory size if needed
			com.ibm.lang.management.MemoryMXBean myMMXB = (com.ibm.lang.management.MemoryMXBean) ManagementFactory
					.getMemoryMXBean();
			// We would like the new limit to become a quarter of total
			// available physical memory
			long newHeapSize = newPhysicalMem / 4;
			if (newHeapSize < myMMXB.getMinHeapSize())
				newHeapSize = myMMXB.getMinHeapSize();
			else if (newHeapSize > myMMXB.getMaxHeapSizeLimit())
				newHeapSize = myMMXB.getMaxHeapSizeLimit();

			if (newHeapSize != myMMXB.getMaxHeapSize()) {
				System.out.println("Changing maximum heap size to "
						+ VirtualizationDemo.printable(newHeapSize));
				myMMXB.setMaxHeapSize(newHeapSize);
				// Verify.
				System.out.println("Java Heap: "
						+ VirtualizationDemo.printable(myMMXB.getMinHeapSize())
						+ " (Minimum), "
						+ VirtualizationDemo.printable(myMMXB.getMaxHeapSize())
						+ "(Soft Limit), "
						+ VirtualizationDemo.printable(myMMXB
								.getMaxHeapSizeLimit()) + "(Maximum)");

			}
		}
		System.out
				.println(">>> On standby for dynamic reconfiguration event ...");

	}
}
