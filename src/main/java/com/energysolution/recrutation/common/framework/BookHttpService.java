package com.energysolution.recrutation.common.framework;

import org.springframework.http.ResponseEntity;

public interface BookHttpService {

	ResponseEntity<BookCreateResponse> save(BookCreateRequest bookCreateRequest);
}
