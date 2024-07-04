package com.energysolution.recrutation.books.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.energysolution.recrutation.common.framework.BookCreateError;
import com.energysolution.recrutation.common.framework.BookCreateRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class BookPublicationDateValidator implements Validator<BookCreateRequest> {

	@Override
	public void validate(BookCreateRequest bcr, List<BookCreateError> errors) {
		if (!StringUtils.hasLength(bcr.publicationDate())) {
			errors.add(new BookCreateError("PUBLICATION_DATE_IS_REQUIRED", "Publication date cannot be empty."));
		} else {
			validateFormat(bcr.publicationDate(), errors);
		}
	}

	private void validateFormat(String dateString, List<BookCreateError> errors) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		sdf.setLenient(false);

		try {
			Date ignore = sdf.parse(dateString);
		} catch (ParseException e) {
			errors.add(new BookCreateError("PUBLICATION_DATE_WRONG_FORMAT", "Publication date has incorrect format."));
		}
	}
}
