package com.empresaprivadaservicios.sui;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PeriodoRepository extends JpaRepository<Periodo, Integer> {

  List<Periodo> findAll(Sort sort);

  List<Periodo> findAllByPeriano(Integer periano, Sort sortByPericodiDesc);

  @Query(value = "select distinct periano from sui_periodo order by periano desc limit ?1", nativeQuery = true)
  List<Integer> obtainLastPeriano(Integer size);

  @Query(value = "select * from (select distinct periano from sui_periodo where periano > ?1 order by periano asc limit ?2) tabla order by periano desc", nativeQuery = true)
  List<Integer> obtainPerianoGreaterThan(Integer gt, Integer size);

  @Query(value = "select * from (select distinct periano from sui_periodo where periano < ?1 order by periano desc limit ?2) tablas order by periano desc", nativeQuery = true)
  List<Integer> obtainPerianoLessThan(Integer lt, Integer size);
}
