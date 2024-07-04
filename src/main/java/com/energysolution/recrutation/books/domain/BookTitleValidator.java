package com.energysolution.recrutation.books.domain;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.energysolution.recrutation.common.framework.BookCreateError;
import com.energysolution.recrutation.common.framework.BookCreateRequest;

@Service
class BookTitleValidator implements Validator<BookCreateRequest> {

	@Override
	public void validate(BookCreateRequest bcr, List<BookCreateError> errors) {
		if (!StringUtils.hasLength(bcr.title())) {
			errors.add(new BookCreateError("TITLE_IS_REQUIRED", "Title cannot be empty."));
		} else if (bcr.title().length() > 100) {
			errors.add(new BookCreateError("TITLE_TOO_LONG", "Author cannot be longer than 100 characters."));
		}
	}
}
