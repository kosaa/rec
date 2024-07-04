package com.energysolution.recrutation.common.framework;

import java.util.List;
import java.util.Optional;
import com.energysolution.recrutation.books.domain.BookId;

public interface BookCreateResponse {

	Optional<BookId> bookId();

	List<BookCreateError> bookCreateErrors();

	boolean hasError(String code);
}
