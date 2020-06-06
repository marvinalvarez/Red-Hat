package com.demo.AmqDemo.pojo;

public class QueueSend {

	private String brokerURL = null;
	private String username = null;
	private String password = null;
	private String userQueue = null;
	private long msgCount = 1000;
	private int msgSize = 3000;
	private int threadSize = 1;
	private int connections = 3;
	
	public String getBrokerURL() {
		return brokerURL;
	}

	public void setBrokerURL(String brokerURL) {
		this.brokerURL = brokerURL;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserQueue() {
		return userQueue;
	}

	public void setUserQueue(String userQueue) {
		this.userQueue = "" + userQueue;
	}

	public long getMsgCount() {
		return msgCount;
	}

	public void setMsgCount(long msgCount) {
		this.msgCount = msgCount;
	}

	public int getMsgSize() {
		return msgSize;
	}

	public void setMsgSize(int msgSize) {
		this.msgSize = msgSize;
	}

	public int getThreadSize() {
		return threadSize;
	}

	public void setThreadSize(int threadSize) {
		this.threadSize = threadSize;
	}

	public int getConnections() {
		return connections;
	}

	public void setConnections(int connections) {
		this.connections = connections;
	}

	@Override
	public String toString() {
		return "QueueSend [brokerURL=" + brokerURL + ", username=" + username + ", password=" + password
				+ ", userQueue=" + userQueue + ", msgCount=" + msgCount + ", msgSize=" + msgSize + ", threadSize="
				+ threadSize + ", connections=" + connections + "]";
	}

}
