package com.energysolution.recrutation.books.domain;

import java.util.UUID;
import jakarta.persistence.Embeddable;

@Embeddable
public class BookId implements Identifier {
	UUID id;

	static BookId create() {
		BookId b = new BookId();
		b.id = UUID.randomUUID();
		return b;
	}
}
