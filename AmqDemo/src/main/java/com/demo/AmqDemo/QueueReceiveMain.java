package com.demo.AmqDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.demo.AmqDemo.dao.QueueReceive;

public class QueueReceiveMain {

	private static String brokerURL;
	private static String userName;
	private static String passwd;
	private static String userQueue;
	private static long msgCnt = 1000;
	private static int threads = 10;
	private static Thread t;

	public static void main(String[] args) throws Exception {
		if (args.length < 1) {
			// brokerURL =
			// "(tcp://35.247.171.145:61616,tcp://35.247.171.145:61626,tcp://35.247.129.110:61616,tcp://35.247.129.110:61626,tcp://35.240.165.214:61616,tcp://35.240.165.214:61626)?ha=true&reconnectAttempts=-1&retryInterval=5000&retryIntervalMultiplier=1.0";

			// 1a/1b
			brokerURL = "(tcp://35.247.171.145:61616,tcp://35.247.129.110:61626)?ha=true&reconnectAttempts=-1&retryInterval=5000&retryIntervalMultiplier=1.0";

			userName = "amqadm";
			passwd = "amqadm";
			userQueue = "mytest-queue-b1";
			msgCnt = 1000;
			threads = 10;

		} else {

			for (int i = 0; i < args.length; i++) {
				if (args[i].equalsIgnoreCase("--url"))
					brokerURL = args[i + 1];
				if (args[i].equalsIgnoreCase("--user"))
					userName = args[i + 1];
				if (args[i].equalsIgnoreCase("--password"))
					passwd = args[i + 1];
				if (args[i].equalsIgnoreCase("--queue"))
					userQueue = args[i + 1];
				if ("--message-count".equalsIgnoreCase(args[i]))
					msgCnt = Long.valueOf(args[i + 1]);
				if ("--thread-size".equalsIgnoreCase(args[i]))
					threads = Integer.valueOf(args[i + 1]);
				i++;
			}
		}
		try

		{
			if (null == brokerURL) {
				brokerURL = readAndReturn("Please Enter Broker URL");
			}
			if (null == userName) {
				userName = readAndReturn("Please enter the username");
			}
			if (null == passwd)
				passwd = readAndReturn("Please enter the password");
			if (null == userQueue)
				userQueue = readAndReturn("Please enter the queue name");
			if (msgCnt == -1)
				msgCnt = 1000;
			if (threads == -1)
				threads = 10;

		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < threads; i++) {
			System.out.println("Thread " + (i + 1) + " started receiving " + msgCnt + " messages.");
			t = new Thread(new QueueReceive(brokerURL, userName, passwd, userQueue, msgCnt));
			t.start();
		}

	}

	private static String readAndReturn(String quest) throws IOException {
		System.out.println(quest + ": ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String msg = br.readLine();
		br.close();
		return msg;
	}
}
