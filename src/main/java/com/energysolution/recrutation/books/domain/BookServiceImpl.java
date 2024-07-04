package com.energysolution.recrutation.books.domain;

import java.util.ArrayList;
import java.util.List;
import com.energysolution.recrutation.common.framework.BookCreateError;
import com.energysolution.recrutation.common.framework.BookCreateRequest;
import com.energysolution.recrutation.common.framework.BookCreateResponse;
import com.energysolution.recrutation.common.framework.BookService;

class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;

	private final List<Validator<BookCreateRequest>> validators;

	BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
		this.validators = new BookAddValidatorProvider().getValidators();
	}

	@Override
	public BookCreateResponse save(BookCreateRequest bcr) {
		List<BookCreateError> errors = validate(bcr);
		if (errors.isEmpty()) {
			Book b = createEntity(bcr);
			return BookCreateResponseImpl.builder().bookId(b.getId()).build();
		} else {
			return BookCreateResponseImpl.builder().errors(errors).build();
		}
	}

	private Book createEntity(BookCreateRequest bcr) {
		Book b = createBookEntity(bcr);
		bookRepository.save(b);
		return b;
	}

	private static Book createBookEntity(BookCreateRequest bcr) {
		Book b = new Book();
		b.setId(BookId.create());
		b.setTitle(bcr.title());
		b.setAuthor(bcr.author());
		b.setIsbn(bcr.isbn());
		b.setPublicationDate(bcr.publicationDate());
		return b;
	}

	private List<BookCreateError> validate(BookCreateRequest bcr) {
		List<BookCreateError> errors = new ArrayList<>();
		validators.forEach(v -> v.validate(bcr, errors));
		return errors;
	}
}
