package com.energysolution.recrutation.books.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "BOOKS")
class Book implements IdentifiableAggregateRoot<BookId> {

	@EmbeddedId
	private BookId id;

	private String title;

	private String author;

	private String isbn;

	private String publicationDate;
}
