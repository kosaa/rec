package com.energysolution.recrutation.books.domain;

interface IdentifiableAggregateRoot<ID extends Identifier> {
	ID getId();
}
