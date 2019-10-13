package techgig.brillio.tokenSystem.service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Service;

import techgig.brillio.tokenSystem.task.TaskHolder;

@Service
public class AssignTokenToUser {

	/*
	 * Size of the queue depends on the requirement like how much time counter take
	 * to serve the token and at what frequency we get the request for a new token
	 */
	static private BlockingQueue<TaskHolder> blockingQueue = new ArrayBlockingQueue<>(3);
	final ReentrantLock lock = new ReentrantLock(true);
	private final Condition notEmpty = lock.newCondition();
	private final Condition notFull = lock.newCondition();
	/*
	 * This value can be initialized from data base but for this practical we will
	 * store the value in-memory and in case of restart it starts from 0
	 */
	static AtomicInteger tokenNumber = new AtomicInteger();

	/*
	 * This method is use to generate the token give it back to user and put in the
	 * blockingQueue
	 */
	public int assignToken() {
		lock.lock();
		int token = 0;
		try {
			tokenNumber.getAndIncrement();
			token = tokenNumber.get();
			TaskHolder task = new TaskHolder(token);

			if(blockingQueue.size() == 3) {
				notEmpty.await();
			}
			// add the token in the queue
			blockingQueue.put(task);
			notFull.signal();

			// uncomment the below line to see the generated values from java multithreads
			// System.out.println(i);

			// Will also store the token with other details in database to in case of
			// failure we will generate the token from previous values
			System.out.println(token);
			return token;
		} catch (InterruptedException e) {
			e.printStackTrace();
			// get the copy of last value from Queue
			TaskHolder lastTask = blockingQueue.peek();
			tokenNumber = new AtomicInteger(lastTask.getTokenNumber());
			assignToken();
			return token;
		} finally {
			while (lock.getHoldCount() >0) {
				lock.unlock();
			}
		}
	}

	/**
	 * This method will take the token from Queue and handover to Other thread to
	 * server
	 */
	public int getTokenToServer(int timeTakesToServer) {
		try {
			lock.lock();
			if(blockingQueue.isEmpty()) {
				notFull.await();
			}
			notEmpty.signal();
			return ((TaskHolder) blockingQueue.take()).getTokenNumber();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}finally {
			lock.unlock();
		}
//		ExecutorService serviceExe = Executors.newFixedThreadPool(5);
//		int tokenNumber = 0;
//		serviceExe.submit(new Callable<Integer>() {
//
//			@Override
//			public Integer call() throws Exception {
//				try {
//					TaskHolder taskFromQueue = blockingQueue.take();
//					tokenNumber = taskFromQueue.getTokenNumber();
//					System.out.println("Currently Serving token number :" + tokenNumber);
//				} catch (InterruptedException e) { // TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				return tokenNumber;
//			}
//		});
//
//		return 0;
	}

}
