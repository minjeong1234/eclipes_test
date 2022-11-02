package green.spring.greenschool;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import green.spring.greenschool.notice.NoticeService;

@SpringBootTest
class GreenSchoolApplicationTests {
	
	@Autowired
	private NoticeService noticeService;

	@Test
	void testJpa() {
		for(int i = 1; i<=300; i++) {
			String subject = String.format("테스트 데이터입니다: [%03d]", i);
			String content = "내용없음";
			this.noticeService.create(subject, content);
		}
	}

}
