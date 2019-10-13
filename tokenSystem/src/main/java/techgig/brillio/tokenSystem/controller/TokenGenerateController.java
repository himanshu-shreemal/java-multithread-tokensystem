package techgig.brillio.tokenSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import techgig.brillio.tokenSystem.service.AssignTokenToUser;

@RestController
public class TokenGenerateController {

	@Autowired
	AssignTokenToUser assignToken;

	@GetMapping(path = "/generateToken")
	public int generateToken() {
		return assignToken.assignToken();
	}

	@GetMapping(path = "/getTokenToServer")
	public int getTokenToServer(@RequestParam(defaultValue = "3") int timeTakesToServer) {
		return assignToken.getTokenToServer(timeTakesToServer);
	}
}