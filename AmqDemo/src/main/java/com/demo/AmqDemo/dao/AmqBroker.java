package com.demo.AmqDemo.dao;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSession;
import javax.jms.Session;

import org.apache.activemq.artemis.jms.client.ActiveMQConnection;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;

import com.demo.AmqDemo.pojo.QueueSend;

public class AmqBroker {

	private QueueSend qs;

	private QueueConnection qcon;
	private QueueSession qsession;

	private MessageProducer qProducer;

	public AmqBroker(QueueSend queueSend) throws JMSException {
		qs = queueSend;

		ActiveMQConnectionFactory amqConFactory = new ActiveMQConnectionFactory(qs.getBrokerURL(), qs.getUsername(),
				qs.getPassword());
		qcon = amqConFactory.createQueueConnection();
		qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

		Queue queue = new ActiveMQQueue(queueSend.getUserQueue());
		qProducer = qsession.createProducer(queue);

		((ActiveMQConnection) qcon).setFailoverListener(new FailOverEventListenerImpl());
		qcon.start();

		System.out.println("qcon.getMetaData()=" + qcon.getMetaData());
	}

	public MessageProducer getqProducer() {
		return qProducer;
	}

	public QueueSession getQsession() {
		return qsession;
	}

	public void sendByteMessage() throws JMSException {
		BytesMessage byteMsg = qsession.createBytesMessage();
		byteMsg.writeBytes(new byte[qs.getMsgSize()]);
		qProducer.send(byteMsg);

		System.out.println(Thread.currentThread().getName() + " message delivered.");
	}

	public void close() throws JMSException {
		qProducer.close();
		qsession.close();
		qcon.close();
	}
}
