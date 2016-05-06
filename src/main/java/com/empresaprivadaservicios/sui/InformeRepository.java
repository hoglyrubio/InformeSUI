package com.empresaprivadaservicios.sui;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InformeRepository extends CrudRepository<Informe, InformePk>{

  Long countByInformePk_infoperi(Integer infoperi);

  List<Informe> findByInformePk_infoperi(Integer infoperi);

  Informe save(Informe informe);

  boolean exists(InformePk informePk);

}
