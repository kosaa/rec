package com.energysolution.recrutation.books.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import com.energysolution.recrutation.common.framework.BookCreateError;
import com.energysolution.recrutation.common.framework.BookCreateResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
class BookCreateResponseImpl implements BookCreateResponse {

	private final BookId bookId;
	private final List<BookCreateError> errors;

	@Override
	public Optional<BookId> bookId() {
		return Optional.ofNullable(bookId);
	}

	@Override
	public List<BookCreateError> bookCreateErrors() {
		if (errors == null) {
			return Collections.emptyList();
		}
		return new ArrayList<>(errors);
	}

	@Override
	public boolean hasError(String code) {
		if (errors == null) {
			return false;
		}
		return errors.stream().anyMatch(v -> v.errorCode().equals(code));
	}
}
