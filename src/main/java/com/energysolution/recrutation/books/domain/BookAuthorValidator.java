package com.energysolution.recrutation.books.domain;

import java.util.List;
import java.util.regex.Pattern;
import org.springframework.util.StringUtils;
import com.energysolution.recrutation.common.framework.BookCreateError;
import com.energysolution.recrutation.common.framework.BookCreateRequest;

class BookAuthorValidator implements Validator<BookCreateRequest> {

	private final Pattern alphaCharsICasePattern = Pattern.compile("^[a-zA-Z0-9 ]+$");

	@Override
	public void validate(BookCreateRequest bcr, List<BookCreateError> errors) {
		if (!StringUtils.hasLength(bcr.author())) {
			errors.add(new BookCreateError("AUTHOR_IS_REQUIRED", "Author cannot be empty."));
		} else if (bcr.author().length() > 100) {
			errors.add(new BookCreateError("AUTHOR_TOO_LONG", "Author cannot be longer than 100 characters."));
		} else if (!alphaCharsICasePattern.matcher(bcr.author()).find()) {
			errors.add(new BookCreateError("AUTHOR_FORMAT", "Author contains illegal characters."));
		}
	}
}
