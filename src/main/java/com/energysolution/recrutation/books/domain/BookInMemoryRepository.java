package com.energysolution.recrutation.books.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class BookInMemoryRepository implements BookRepository {

	private final List<Book> db = new ArrayList<>();

	@Override
	public <S extends Book> S save(S entity) {
		db.add(entity);
		return entity;
	}

	@Override
	public <S extends Book> Iterable<S> saveAll(Iterable<S> entities) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public Optional<Book> findById(BookId bookId) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public boolean existsById(BookId bookId) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public Iterable<Book> findAll() {
		throw new RuntimeException("not implemented");
	}

	@Override
	public Iterable<Book> findAllById(Iterable<BookId> bookIds) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public long count() {
		throw new RuntimeException("not implemented");
	}

	@Override
	public void deleteById(BookId bookId) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public void delete(Book entity) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public void deleteAllById(Iterable<? extends BookId> bookIds) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public void deleteAll(Iterable<? extends Book> entities) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public void deleteAll() {
		throw new RuntimeException("not implemented");
	}
}
