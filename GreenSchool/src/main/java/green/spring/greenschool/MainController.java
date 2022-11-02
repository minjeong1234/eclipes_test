package green.spring.greenschool;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/greenSchool") //메인화면
	public String main() {
		return "main";
	}
	
	@RequestMapping("/") //루트
	public String root() {
		return "redirect:/greenSchool";
	}
	
	@RequestMapping("school/info") //학교소개
	public String info() {
		return "school_info";
	}

}
