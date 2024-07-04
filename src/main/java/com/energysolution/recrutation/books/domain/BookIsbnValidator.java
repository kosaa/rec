package com.energysolution.recrutation.books.domain;

import java.util.List;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.energysolution.recrutation.common.framework.BookCreateError;
import com.energysolution.recrutation.common.framework.BookCreateRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class BookIsbnValidator implements Validator<BookCreateRequest> {

	private final Pattern isbnPattern = Pattern.compile("^\\d{13}$");

	@Override
	public void validate(BookCreateRequest bcr, List<BookCreateError> errors) {
		if (!StringUtils.hasLength(bcr.isbn())) {
			errors.add(new BookCreateError("ISBN_IS_REQUIRED", "ISBN cannot be empty."));
		} else if (bcr.isbn().length() != 13) {
			errors.add(new BookCreateError("ISBN_LENGTH", "ISBN number must be 13 digits long."));
		} else if (!isbnPattern.matcher(bcr.isbn()).find()) {
			errors.add(new BookCreateError("ISBN_FORMAT", "ISBN number can only contain numbers."));
		}
	}
}
