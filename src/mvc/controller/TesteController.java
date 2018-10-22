package mvc.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TesteController {
	@RequestMapping("/")
	public String execute() {
		System.out.println("Carrega index.jsp");
		return "index";
	}

	@PutMapping(value = "/teste")
	@ResponseBody
	public void test(@RequestBody String rawJson) {
		System.out.println("PUT");
		JSONObject parsedJson = new JSONObject(rawJson);
		String username = parsedJson.getString("username");
		String password = parsedJson.getString("password");
		System.out.println("Username recebido: " + username);
		System.out.println("Password recebido: " + password);
	}

	@PostMapping(value = "/teste")
	@ResponseBody
	public void testPost(@RequestBody String rawJson) {
		System.out.println("POST");
		JSONObject parsedJson = new JSONObject(rawJson);
		String username = parsedJson.getString("username");
		String password = parsedJson.getString("password");
		System.out.println("Username recebido: " + username);
		System.out.println("Password recebido: " + password);
	}
}