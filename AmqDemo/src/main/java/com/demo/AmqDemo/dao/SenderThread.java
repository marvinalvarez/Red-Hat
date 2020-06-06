package com.demo.AmqDemo.dao;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.demo.AmqDemo.pojo.QueueSend;

public class SenderThread implements Runnable {

	private AmqBroker amqBroker;
	private QueueSend queueSend;

	public SenderThread(AmqBroker amqBroker, QueueSend queueSend) {
		this.amqBroker = amqBroker;
		this.queueSend = queueSend;
	}

	private void sendByteMessage(int msgSize) throws JMSException {
		BytesMessage byteMsg = this.amqBroker.getQsession().createBytesMessage();
		byteMsg.writeBytes(new byte[msgSize]);
		this.amqBroker.getqProducer().send(byteMsg);

		System.out.println(Thread.currentThread().getName() + " message delivered.");
	}

	@Override
	public void run() {
		for (int i = 1; i <= queueSend.getMsgCount(); i++) {

			try {
				this.sendByteMessage(queueSend.getMsgSize());

				if (i % 1000 == 0) {
					System.out.println(i + " Messages Produced");
				}

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		System.out.println(Thread.currentThread().getName() + " Thread Completed");
	}

}
