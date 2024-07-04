package com.energysolution.recrutation.books.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import com.energysolution.recrutation.common.framework.BookHttpService;
import com.energysolution.recrutation.common.framework.BookService;

@Component
public class BookConfiguration {

	@Bean
	public BookService bookService(BookRepository bookRepository) {
		return new BookServiceImpl(bookRepository);
	}

	@Bean
	public BookHttpService bookHttpService(BookRepository bookRepository) {
		return new BookHttpServiceImpl(new BookServiceImpl(bookRepository));
	}

	public BookService bookService() {
		return new BookServiceImpl(new BookInMemoryRepository());
	}
}

