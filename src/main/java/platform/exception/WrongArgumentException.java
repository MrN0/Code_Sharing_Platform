package platform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WrongArgumentException extends IllegalArgumentException {

	private static final long serialVersionUID = 5202691502850124134L;

	public WrongArgumentException(String s) {
        super(s);
    }

}
