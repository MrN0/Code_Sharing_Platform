package platform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CodeSnippetNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5263101410520175288L;

	public CodeSnippetNotFoundException(String message) {
        super(message);
    }

}
