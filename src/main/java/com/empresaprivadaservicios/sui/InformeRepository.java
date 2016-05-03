package com.empresaprivadaservicios.sui;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface InformeRepository extends CrudRepository<Informe, InformePk>{

  public Long countByInformePk_infoperi(Integer infoperi);
  
  public List<Informe> findByInformePk_infoperi(Integer infoperi);
  
}
