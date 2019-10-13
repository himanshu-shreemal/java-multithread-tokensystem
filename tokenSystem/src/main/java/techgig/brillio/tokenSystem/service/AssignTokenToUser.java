package techgig.brillio.tokenSystem.service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Service;

import techgig.brillio.tokenSystem.task.TaskHolder;

@Service
public class AssignTokenToUser {

	static private BlockingQueue<TaskHolder> blockingQueue = new ArrayBlockingQueue<>(30000);

	/*
	 * This value will be initialized from data base but for this practical we will
	 * store the value in-memory and in case of restart start from 0
	 */
	static AtomicInteger tokenNumber = new AtomicInteger();
	ReentrantLock lock = new ReentrantLock();

	/*
	 * When we start using this class we start a thread to serve the tokens
	 */
	public AssignTokenToUser() {
		new Thread(() -> {
			getTheTokenAndServer();
		}).start();
	}

	/*
	 * This method is use to generate the token give it back to user and put in the
	 * queue
	 */
	public static synchronized int assignToken() throws Exception {
		try {
//			lock.lock();
			int token = tokenNumber.getAndIncrement();
			TaskHolder task = new TaskHolder(tokenNumber.get(), token * 100);
			// add the token in the queue
			blockingQueue.put(task);
			// uncomment the below line to see the generated values from java multithreads
			// System.out.println(i);
			// Will also store the token with other details in database to in case of
			// failure we will generate the token from previous values
		} catch (InterruptedException e) {
			e.printStackTrace();
			// get the copy of last value from Queue
			TaskHolder lastTask = blockingQueue.peek();
			tokenNumber = new AtomicInteger(lastTask.getI());
			assignToken();
			// Try to assign at max 10 times
//			if (lock.getHoldCount() <= 10) {
//				throw new Exception();
//			}
//		} finally {
//			while (lock.getHoldCount() >= 0) {
//				lock.unlock();
//			}
		}
		System.out.println(tokenNumber.get());
		return tokenNumber.get();
	}

	/**
	 * This method will take the token from Queue and handover to Other thread to
	 * server
	 */
	private void getTheTokenAndServer() {
		ExecutorService serviceExe = Executors.newFixedThreadPool(5);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (!blockingQueue.isEmpty()) {
			serviceExe.execute(new Runnable() {
				@Override
				public void run() {
					try {
						TaskHolder taskFromQueue = blockingQueue.take();
						System.out.println(taskFromQueue.getI());
						System.out.println("Task Taken by Thread " + Thread.currentThread().getName() + " ->"
								+ taskFromQueue.getI() + " " + new java.sql.Time(System.currentTimeMillis()));
//						Thread.sleep(taskFromQueue.getCompletesInSeconds());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
	}

}