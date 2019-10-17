package techgig.brillio.tokenSystem.service;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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
	Queue<Integer> queue = new LinkedList<>();
	
	Queue<Integer> premium_queue = new LinkedList<>();

	final ReentrantLock lock = new ReentrantLock(true);
	private final Condition notFull = lock.newCondition();

	/* We are assuming that there are 5-10 counters who can submit the request */
	ExecutorService executor = Executors.newFixedThreadPool(10);

	/*
	 * This value can be initialized from data base but for this practical we will
	 * store the value in-memory and in case of restart it starts from 0
	 */
	static AtomicInteger tokenNumber = new AtomicInteger();
	static AtomicInteger premium_tokenNumber = new AtomicInteger();

	/*
	 * This method is use to generate the token give it back to user and put in the
	 * blockingQueue
	 */
	public int assignToken(boolean isPremium) {
		lock.lock();
		int token = 0;
		try {
			if(isPremium) {
				premium_tokenNumber.getAndIncrement();
				token = premium_tokenNumber.get();

				// add the token in the queue
				premium_queue.add(premium_tokenNumber.get());
				notFull.signal();
			}else {
				tokenNumber.getAndIncrement();
				token = tokenNumber.get();

				// add the token in the queue
				queue.add(tokenNumber.get());
				notFull.signal();
			}

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
	public int getTokenToServer() {
		try {
			lock.lock();
			if (queue.isEmpty()) {
				notFull.await();
			}
			return queue.poll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} finally {
			lock.unlock();
		}
	}

	public boolean submitTokenToServer(TaskHolder taskHolder) {
		try {
			TaskHolder task = new TaskHolder();
			task.setTimeTakeToServer(taskHolder.getTimeTakeToServer());
			task.setTokenNumber(taskHolder.getTokenNumber());

			Callable<Boolean> callableTaskToSubmit = createThreadInstance(task);
			Future<Boolean> future = executor.submit(callableTaskToSubmit);

			return (boolean) future.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public Callable<Boolean> createThreadInstance(TaskHolder task) {
		return new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				
//				Performt the requried computation
//				we assume that task was performing and taking the time so instead of performing task
//				we ask thread to sleep with hoding the monitor and cpu resources
				
				Thread.sleep(task.getTimeTakeToServer() * 1000);
				return true;
			}
		};

	}
}
