package green.spring.greenschool.question;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {
	@NotEmpty(message="제목을 입력하세요.")
	@Size(max=200)
	private String subject;
	
	@NotEmpty(message="내용을 입력하세요.")
	private String content;

}
