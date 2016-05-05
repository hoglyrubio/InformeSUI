package com.empresaprivadaservicios.sui;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PeriodoRepository extends PagingAndSortingRepository<Periodo, Integer>{

  public List<Periodo> findAll(Sort sort);
}
