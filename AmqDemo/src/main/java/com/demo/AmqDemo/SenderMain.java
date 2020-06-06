//package com.demo.AmqDemo;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//import com.demo.AmqDemo.dao.SenderThread;
//import com.demo.AmqDemo.pojo.QueueSend;
//
///**
// * Hello world!
// *
// */
//public class SenderMain {
//	public static void main(String[] args) throws Exception {
//
//		QueueSend qs = new QueueSend();
//
//		if (args.length < 1) {
//			qs.setBrokerURL(
//					"(tcp://35.247.171.145:61616,tcp://35.247.171.145:61626,tcp://35.247.129.110:61616,tcp://35.247.129.110:61626,tcp://35.240.165.214:61616,tcp://35.240.165.214:61626)?ha=true&reconnectAttempts=-1&retryInterval=5000&retryIntervalMultiplier=1.0");
//
////			qs.setBrokerURL(
////					"(tcp://35.247.129.110:61626)?ha=true&reconnectAttempts=-1&retryInterval=5000&retryIntervalMultiplier=1.0");
//
//			qs.setUsername("amqadm");
//			qs.setPassword("amqadm");
//			qs.setUserQueue("mytest-queue-b1");
//			qs.setMsgSize(1000);
//			qs.setMsgCount(10000);
//			qs.setThreadSize(10);
//			qs.setConnections(3);
//
//		} else {
//			for (int i = 0; i < args.length; i++) {
//				if (args[i].equalsIgnoreCase("--url"))
//					qs.setBrokerURL(args[i + 1]);
//				if (args[i].equalsIgnoreCase("--user"))
//					qs.setUsername(args[i + 1]);
//				if (args[i].equalsIgnoreCase("--password"))
//					qs.setPassword(args[i + 1]);
//				if (args[i].equalsIgnoreCase("--queue"))
//					qs.setUserQueue(args[i + 1]);
//				if ("--message-size".equalsIgnoreCase(args[i]))
//					qs.setMsgSize(Integer.valueOf(args[i + 1]));
//				if ("--message-count".equalsIgnoreCase(args[i]))
//					qs.setMsgCount(Long.valueOf(args[i + 1]));
//				if ("--thread-size".equalsIgnoreCase(args[i]))
//					qs.setThreadSize(Integer.valueOf(args[i + 1]));
//				if ("--connections".equalsIgnoreCase(args[i]))
//					qs.setConnections(Integer.valueOf(args[i + 1]));
//				i++;
//			}
//
//			System.out.println("QS Object: " + qs);
//
//			try {
//				if (null == qs.getBrokerURL()) {
//					qs.setBrokerURL(SenderMain.readAndReturn("Please Enter Broker URL"));
//				}
//				if (null == qs.getUsername()) {
//					qs.setUsername(SenderMain.readAndReturn("Please enter the username"));
//				}
//				if (null == qs.getPassword())
//					qs.setPassword(SenderMain.readAndReturn("Please enter the password"));
//				if (null == qs.getUserQueue())
//					qs.setUserQueue(SenderMain.readAndReturn("Please enter the queue name"));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
//		// start threads
//		for (int i = 0; i < qs.getThreadSize(); i++) {
//			System.out.println("Thread " + (i + 1) + " started sending " + qs.getMsgCount() + " messages.");
//			Thread t = new Thread(new SenderThread(qs));
//			t.start();
//		}
//	}
//
//	private static String readAndReturn(String quest) throws IOException {
//		System.out.println(quest + ": ");
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String msg = br.readLine();
//		br.close();
//		return msg;
//	}
//}
