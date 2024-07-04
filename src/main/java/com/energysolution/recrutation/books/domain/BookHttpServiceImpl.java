package com.energysolution.recrutation.books.domain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.energysolution.recrutation.common.framework.BookCreateRequest;
import com.energysolution.recrutation.common.framework.BookCreateResponse;
import com.energysolution.recrutation.common.framework.BookHttpService;
import com.energysolution.recrutation.common.framework.BookService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class BookHttpServiceImpl implements BookHttpService {

	private final BookService bookService;

	@Override
	public ResponseEntity<BookCreateResponse> save(BookCreateRequest request) {
		BookCreateResponse response = bookService.save(request);
		return ResponseEntity
				.status(of(response))
				.body(response);
	}

	private HttpStatus of(BookCreateResponse response) {
		return response.bookId().isPresent() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
	}
}
