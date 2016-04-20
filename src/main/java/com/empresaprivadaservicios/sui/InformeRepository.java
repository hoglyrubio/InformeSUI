package com.empresaprivadaservicios.sui;

import org.springframework.data.repository.CrudRepository;

public interface InformeRepository extends CrudRepository<Informe, InformePk>{

  public Long countByInformePk_infoperi(Integer infoperi);
  
}
