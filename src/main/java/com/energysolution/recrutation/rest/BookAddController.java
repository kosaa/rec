package com.energysolution.recrutation.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.energysolution.recrutation.common.framework.BookCreateRequest;
import com.energysolution.recrutation.common.framework.BookCreateResponse;
import com.energysolution.recrutation.common.framework.BookHttpService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/public/book")
public class BookAddController {

	private BookHttpService bookService;

	@PostMapping()
	public ResponseEntity<BookCreateResponse> login(@RequestBody BookCreateRequest request) {
		return bookService.save(request);
	}
}
