package com.energysolution.recrutation.books.domain;

import java.util.ArrayList;
import java.util.List;
import com.energysolution.recrutation.common.framework.BookCreateRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class BookAddValidatorProvider {

	List<Validator<BookCreateRequest>> getValidators() {
		List<Validator<BookCreateRequest>> validators = new ArrayList<>();
		validators.add(new BookAuthorValidator());
		validators.add(new BookIsbnValidator());
		validators.add(new BookTitleValidator());
		validators.add(new BookPublicationDateValidator());
		return validators;
	}
}
