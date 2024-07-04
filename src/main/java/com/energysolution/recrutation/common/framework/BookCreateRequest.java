package com.energysolution.recrutation.common.framework;

public record BookCreateRequest(
		String title,
		String author,
		String isbn,
		String publicationDate) {
}
