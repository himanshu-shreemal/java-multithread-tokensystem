package techgig.brillio.tokenSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import techgig.brillio.tokenSystem.service.AssignTokenToUser;
import techgig.brillio.tokenSystem.task.TaskHolder;

@RestController
public class TokenGenerateController {

	@Autowired
	AssignTokenToUser assignToken;

	@GetMapping(path = "/generateToken")
	public int generateToken(@RequestParam boolean isPremium) {
		return assignToken.assignToken(isPremium);
	}

	@GetMapping(path = "/getTokenToServer")
	public int getTokenToServer() {
		return assignToken.getTokenToServer();
	}
	
	@PostMapping(path = "/submitTokenToServer")
	public boolean submitTokenToServer(@RequestBody TaskHolder task) {
		return assignToken.submitTokenToServer(task);
	}
}