package green.spring.greenschool.notice;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import green.spring.greenschool.user.SiteUser;
import green.spring.greenschool.user.UserService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/notice")
@RequiredArgsConstructor
@Controller
public class NoticeController {
	
	private final NoticeService noticeService;
	private final UserService userService;
	
	@RequestMapping("/list")
	public String notice(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		Page<Notice> paging = this.noticeService.getList(page);
		model.addAttribute("paging", paging);
		return "school_notice";
	}
	
	@RequestMapping(value = "/detail/{id}")
	public String noticeDetail(Model model, @PathVariable("id") Integer id) {
		Notice notice = this.noticeService.getNotice(id);
		model.addAttribute("notice", notice);
		return "notice_detail";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String noticeCreate(NoticeForm noticeForm) {
		return "notice_form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String noticeCreate(@Valid NoticeForm noticeForm, BindingResult bindingResult, Principal principal) {
		if(bindingResult.hasErrors()) {
			return "notice_form";
		}
		SiteUser siteUser = this.userService.getUser(principal.getName());
		this.noticeService.create(noticeForm.getSubject(), noticeForm.getContent(), siteUser);
		return "redirect:/notice/list";
	}

}
