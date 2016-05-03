package com.empresaprivadaservicios.sui;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PeriodoRepository extends CrudRepository<Periodo, Integer>{

  public List<Periodo> findAll();
}
