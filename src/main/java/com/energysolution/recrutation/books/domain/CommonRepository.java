package com.energysolution.recrutation.books.domain;

import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
interface CommonRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {
}
