package techgig.brillio.tokenSystem.task;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TaskHolder {

	@NotNull
	@Min(1)
	private int tokenNumber;

	@NotNull
	@Min(1)
	private int timeTakeToServer;

	public int getTimeTakeToServer() {
		return timeTakeToServer;
	}

	public void setTimeTakeToServer(int timeTakeToServer) {
		this.timeTakeToServer = timeTakeToServer;
	}

	public int getTokenNumber() {
		return tokenNumber;
	}

	public void setTokenNumber(int tokenNumber) {
		this.tokenNumber = tokenNumber;
	}

}
