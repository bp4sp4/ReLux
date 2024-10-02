package com.luxre.relux;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	@GetMapping("/hello")
	public String inputMemo() {
		return "test/hello2";
	}
}
