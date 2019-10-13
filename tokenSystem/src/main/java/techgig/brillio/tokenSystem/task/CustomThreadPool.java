package techgig.brillio.tokenSystem.task;

import java.util.concurrent.SynchronousQueue;

public class CustomThreadPool {

	private int poolSize;
	private SynchronousQueue<TaskHolder> synchronousQueue;
	private WorkerThread[] workerThread;
	private int keepAliveTime;

	public CustomThreadPool(int poolSize, SynchronousQueue<TaskHolder> synchronousQueue, int keepAliveTime) {
		super();
		this.poolSize = poolSize;
		this.synchronousQueue = synchronousQueue;
		this.keepAliveTime = keepAliveTime;
		this.workerThread = new WorkerThread[poolSize];
		
		for (int i = 0; i < poolSize; i++) {
			workerThread[i] = new WorkerThread();
			workerThread[i].start();
        }
		
	}

	public void execute(TaskHolder task) {
        synchronized (synchronousQueue) {
        	synchronousQueue.add(task);
        	synchronousQueue.notify();
        }
    }

	private class WorkerThread extends Thread {
		public void run() {
			Runnable task;

			while (true) {
				synchronized (synchronousQueue) {
					while (synchronousQueue.isEmpty()) {
						try {
							synchronousQueue.wait();
						} catch (InterruptedException e) {
							System.out.println("An error occurred while queue is waiting: " + e.getMessage());
						}
					}
					task = (Runnable) synchronousQueue.poll();
				}

				try {
					task.run();
				} catch (RuntimeException e) {
					System.out.println("Thread pool is interrupted due to an issue: " + e.getMessage());
				}
			}
		}
	}

}
