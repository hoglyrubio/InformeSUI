package com.empresaprivadaservicios.informesui.sui;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SuiClaseUsoRepository extends JpaRepository<SuiClaseUso, Integer> {

  @Query(value = "select * from sui_claseuso where cluscate = ?1 and (clussuca = ?2 or clussuca = -1)", nativeQuery = true)
  SuiClaseUso findByUsoSuca(String cluscate, Integer clussuca);

}
