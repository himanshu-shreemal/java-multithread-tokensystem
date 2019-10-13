package techgig.brillio.tokenSystem.task;

public class TaskHolder{

	private int i, completesInSeconds;

	public TaskHolder(int i, int completesInSeconds) {
		this.i = i;
		this.completesInSeconds = completesInSeconds;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getCompletesInSeconds() {
		return completesInSeconds;
	}

	public void setCompletesInSeconds(int completesInSeconds) {
		this.completesInSeconds = completesInSeconds;
	}

//	@Override
//	public void run() {
//		try {
//			System.out.println("Executing task : " + i);
//			Thread.sleep(completesInSeconds);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
