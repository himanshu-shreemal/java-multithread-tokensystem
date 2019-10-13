package techgig.brillio.tokenSystem.service;

import java.util.LinkedList;
import java.util.Queue;
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
	Queue<TaskHolder> queue = new LinkedList<TaskHolder>();

	final ReentrantLock lock = new ReentrantLock(true);
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

			// add the token in the queue
			queue.add(task);
			notFull.signal();

			// uncomment the below line to see the generated values from java multithreads
			// System.out.println(i);

			// Will also store the token with other details in database to in case of
			// failure we will generate the token from previous values
			return token;
		} finally {
			lock.unlock();
		}
	}

	/**
	 * This method will take the token from Queue and handover to Other thread to
	 * server
	 */
	public int getTokenToServer(int timeTakesToServer) {
		try {
			lock.lock();
			if (queue.isEmpty()) {
				notFull.await();
			}
			return ((TaskHolder) queue.poll()).getTokenNumber();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} finally {
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
