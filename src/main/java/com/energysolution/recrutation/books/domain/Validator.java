package com.energysolution.recrutation.books.domain;

import java.util.List;
import com.energysolution.recrutation.common.framework.BookCreateError;

interface Validator<T> {
	void validate(T request, List<BookCreateError> errors);
}
