package com.demo.AmqDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.demo.AmqDemo.dao.AmqBroker;
import com.demo.AmqDemo.dao.SenderThread;
import com.demo.AmqDemo.pojo.QueueSend;

/**
 * Hello world!
 *
 */
public class SenderMainV2 {
	public static void main(String[] args) throws Exception {

		QueueSend qs = new QueueSend();

		if (args.length < 1) {
//			qs.setBrokerURL(
//					"(tcp://35.247.171.145:61616,tcp://35.247.171.145:61626,tcp://35.247.129.110:61616,tcp://35.247.129.110:61626,tcp://35.240.165.214:61616,tcp://35.240.165.214:61626)?ha=true&reconnectAttempts=-1&retryInterval=5000&retryIntervalMultiplier=1.0");

			// 1a/2b
			qs.setBrokerURL(
					"(tcp://35.247.129.110:61616,tcp://35.240.165.214:61626)?ha=true&reconnectAttempts=-1&retryInterval=5000&retryIntervalMultiplier=1.0");

			// 2a/2b
//			qs.setBrokerURL(
//					"(tcp://35.247.129.110:61616,tcp://35.240.165.214:61626)?ha=true&reconnectAttempts=-1&retryInterval=5000&retryIntervalMultiplier=1.0");

			qs.setUsername("amqadm");
			qs.setPassword("amqadm");
			qs.setUserQueue("mytest-queue-b1");
			qs.setMsgSize(10);
			qs.setMsgCount(20000);
			qs.setThreadSize(10);
			qs.setConnections(1);

		} else {
			for (int i = 0; i < args.length; i++) {
				if (args[i].equalsIgnoreCase("--url"))
					qs.setBrokerURL(args[i + 1]);
				if (args[i].equalsIgnoreCase("--user"))
					qs.setUsername(args[i + 1]);
				if (args[i].equalsIgnoreCase("--password"))
					qs.setPassword(args[i + 1]);
				if (args[i].equalsIgnoreCase("--queue"))
					qs.setUserQueue(args[i + 1]);
				if ("--message-size".equalsIgnoreCase(args[i]))
					qs.setMsgSize(Integer.valueOf(args[i + 1]));
				if ("--message-count".equalsIgnoreCase(args[i]))
					qs.setMsgCount(Long.valueOf(args[i + 1]));
				if ("--thread-size".equalsIgnoreCase(args[i]))
					qs.setThreadSize(Integer.valueOf(args[i + 1]));
				if ("--connections".equalsIgnoreCase(args[i]))
					qs.setConnections(Integer.valueOf(args[i + 1]));
				i++;
			}

			System.out.println(Thread.currentThread().getName() + " QS Object: " + qs);
		}

		AmqBroker[] amqBrokers = new AmqBroker[qs.getConnections()];

		//
		System.out.println(
				Thread.currentThread().getName() + " Creating these number of connections: " + qs.getConnections());
		for (int i = 0; i < qs.getConnections(); i++) {
			amqBrokers[i] = new AmqBroker(qs);
		}

		// start threads
		for (int i = 0; i < qs.getConnections(); i++) {
			ExecutorService executor = Executors.newFixedThreadPool(qs.getConnections());
			executor.execute(new SenderThread(amqBrokers[i], qs));
			awaitTerminationAfterShutdown(executor);

		}

		// cleanup
		for (int i = 0; i < qs.getConnections(); i++) {
			System.out.println(Thread.currentThread().getName() + " closing all connections");
			amqBrokers[i].close();
		}

		//
		System.out.println(Thread.currentThread().getName() + " [COMPLETE] gracefully shutdown");
	}

	private static void awaitTerminationAfterShutdown(ExecutorService executor) {
		executor.shutdown();
		System.out.println(Thread.currentThread().getName() + " waiting for all multi-threaded tasks to complete");
		try {
			if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
				executor.shutdownNow();
			}
		} catch (InterruptedException ex) {
			executor.shutdownNow();
			Thread.currentThread().interrupt();
		}

		System.out.println(Thread.currentThread().getName() + " All tasks completed");
	}
}
