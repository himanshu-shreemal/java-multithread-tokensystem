package techgig.brillio.tokenSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import techgig.brillio.tokenSystem.service.AssignTokenToUser;

@RestController
public class RequestHandler {

	@Autowired
	AssignTokenToUser assignToken;

	@GetMapping(path = "/test1")
	public long test1() {
		try {
			return assignToken.assignToken();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 999;
		}
	}

}