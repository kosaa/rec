package com.energysolution.recrutation;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import com.energysolution.recrutation.books.domain.BookConfiguration;
import com.energysolution.recrutation.common.framework.BookCreateRequest;
import com.energysolution.recrutation.common.framework.BookCreateResponse;
import com.energysolution.recrutation.common.framework.BookService;

class ApplicationTests {

	private static final BookConfiguration configuration = new BookConfiguration();
	private static BookService service;

	@BeforeEach
	void BeforeEach() {
		service = configuration.bookService();
	}

	@Test
	void shouldAddBook() {
		// given
		BookCreateRequest request = new BookCreateRequest(
				"title",
				"author",
				"1".repeat(13),
				"01-01-2000");

		// when
		BookCreateResponse response = service.save(request);

		// then
		assertEquals(0, response.bookCreateErrors().size());
		assertTrue(response.bookId().isPresent());
	}

    @Test
    void shouldReturnErrorWhenTitleIsNotPresent() {
		// given
		BookCreateRequest request = new BookCreateRequest(
				null,
				"author",
				"1".repeat(13),
				"01-01-2000");

		// when
		BookCreateResponse response = service.save(request);

		// then
		assertEquals(1, response.bookCreateErrors().size());
		assertTrue(response.hasError("TITLE_IS_REQUIRED"));
		assertFalse(response.bookId().isPresent());
    }

	@Test
	void shouldReturnErrorWhenTitleIsLongerThan100Characters() {
		// given
		BookCreateRequest request = new BookCreateRequest(
				"a".repeat(101),
				"author",
				"1".repeat(13),
				"01-01-2000");

		// when
		BookCreateResponse response = service.save(request);

		// then
		assertEquals(1, response.bookCreateErrors().size());
		assertTrue(response.hasError("TITLE_TOO_LONG"));
		assertFalse(response.bookId().isPresent());
	}

    @Test
    void shouldReturnErrorWhenAuthorIsNotPresent() {
		// given
		BookCreateRequest request = new BookCreateRequest(
				"title",
				null,
				"1".repeat(13),
				"01-01-2000");

		// when
		BookCreateResponse response = service.save(request);

		// then
		assertEquals(1, response.bookCreateErrors().size());
		assertTrue(response.hasError("AUTHOR_IS_REQUIRED"));
		assertFalse(response.bookId().isPresent());
    }

	@ParameterizedTest
	@CsvSource({
			"Nyankómàgó",
			"Bashar al-Assad",
			"Yo-Yo Ma",
			"Tu'uakitau Cokanauto",
			"Jürgen",
			"Dagur Bergþóruson Eggertsson",
			"Seán Mac Mathúna",
			"Karbala'i",
			"Zygmunt Solorz-Żak",
	})
    void shouldReturnErrorWhenAuthorContainsIllegalCharacters(String author) {
		// given
		BookCreateRequest request = new BookCreateRequest(
				"title",
				author,
				"1".repeat(13),
				"01-01-2000");

		// when
		BookCreateResponse response = service.save(request);

		// then
		assertEquals(1, response.bookCreateErrors().size());
		assertTrue(response.hasError("AUTHOR_FORMAT"));
		assertFalse(response.bookId().isPresent());
    }

    @Test
    void shouldReturnErrorWhenIsbnIsNotPresent() {
		// given
		BookCreateRequest request = new BookCreateRequest(
				"title",
				"author",
				null,
				"01-01-2000");

		// when
		BookCreateResponse response = service.save(request);

		// then
		assertEquals(1, response.bookCreateErrors().size());
		assertTrue(response.hasError("ISBN_IS_REQUIRED"));
		assertFalse(response.bookId().isPresent());
    }

    @Test
    void shouldReturnErrorWhenIsbnIsTooLong() {
		// given
		BookCreateRequest request = new BookCreateRequest(
				"title",
				"author",
				"1".repeat(14),
				"01-01-2000");

		// when
		BookCreateResponse response = service.save(request);

		// then
		assertEquals(1, response.bookCreateErrors().size());
		assertTrue(response.hasError("ISBN_LENGTH"));
		assertFalse(response.bookId().isPresent());
    }

    @Test
    void shouldReturnErrorWhenIsbnContainNonNumericCharacters() {
		// given
		BookCreateRequest request = new BookCreateRequest(
				"title",
				"author",
				"12345ABC12345",
				"01-01-2000");

		// when
		BookCreateResponse response = service.save(request);

		// then
		assertEquals(1, response.bookCreateErrors().size());
		assertTrue(response.hasError("ISBN_FORMAT"));
		assertFalse(response.bookId().isPresent());
    }

    @Test
    void shouldReturnErrorWhenPublicationDateIsNotPresent() {
		// given
		BookCreateRequest request = new BookCreateRequest(
				"title",
				"author",
				"1".repeat(13),
				null);

		// when
		BookCreateResponse response = service.save(request);

		// then
		assertEquals(1, response.bookCreateErrors().size());
		assertTrue(response.hasError("PUBLICATION_DATE_IS_REQUIRED"));
		assertFalse(response.bookId().isPresent());
    }

	@ParameterizedTest
	@CsvSource({
			"foobar",
			"00-10-1993",
			"32-10-1993",
			"13 89 2100",
			"99-10-2000",
			"99-13-2000",
			"01/01/1900",
	})
	public void shouldReturnErrorWhenPublicationDateHasWrongFormat(String pubDate) {
		// given
		BookCreateRequest request = new BookCreateRequest("title", "author", "1234512345123", pubDate);

		// when
		BookCreateResponse response = service.save(request);

		// then
		assertEquals(1, response.bookCreateErrors().size());
		assertTrue(response.hasError("PUBLICATION_DATE_WRONG_FORMAT"));
		assertFalse(response.bookId().isPresent());
	}
}
