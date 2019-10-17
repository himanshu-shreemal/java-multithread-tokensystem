package techgig.brillio.tokenSystem;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import techgig.brillio.tokenSystem.service.AssignTokenToUser;

//This class was create just for the testing purpose from the java end

public class TestMulitiThreadEnvironment {

	public static void main(String[] args) {
		new TestMulitiThreadEnvironment().startTesting();
	}

	int j;
	
	private void startTesting() {

		/*
		 * Let say there are 10 counter to verify the document and raise the request and
		 * we have assigned one thread to each counter there could be many counters but
		 * number of thread depends on number of counter, size of memory, document
		 * verification time and many more things.
		 */
		ExecutorService exe = Executors.newFixedThreadPool(5);

		/*
		 * Let say they have verified multiple documents and generating the token for
		 * them we assume in this process we got 50 requests from the token generator
		 * counter
		 */
		AssignTokenToUser assignTokenToUser = new AssignTokenToUser();
		Future<Integer> future = null;
		for (int i = 0; i < 100; i++) {
			Callable<Integer> task = () -> {
				 return  assignTokenToUser.assignToken(false);
			};
			future = exe.submit(task);
		}
//		Please uncomment the System.out.println in assignToken method to see the generating values 
	}

}
