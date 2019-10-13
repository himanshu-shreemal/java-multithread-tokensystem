package techgig.brillio.tokenSystem.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import techgig.brillio.tokenSystem.task.TaskHolder;

public class ServerToUserToken {

	static SynchronousQueue<TaskHolder> synchronousQueue = new SynchronousQueue<>(true);

	public static void main(String[] args) {
		new ServerToUserToken();
	}
	
	/*
	 * When we start using this class we start a thread to serve the tokens
	 */
	public ServerToUserToken() {
//			serverAsNormalCounter();
	}	

//	public void serverAsNormalCounter() {
//		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 5, 30, TimeUnit.SECONDS, synchronousQueue)
//		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
//		scheduler.scheduleAtFixedRate();
//	}

	public void serveAsPremiumCounter() {

	}
}
