package com.empresaprivadaservicios.sui;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TarifaRepository extends JpaRepository<Tarifa, TarifaPk> {

  Tarifa findByTarifaPk(TarifaPk tarifaPk);

  @Query(value="select * from sui_tarifa where tariano = ?1 and tarimes = ?2 and taricodi = 'CF' and taricate <> -1 and ?3 between floor(tarivalo) and ceil(tarivalo)", nativeQuery = true)
  Tarifa findTarifaCFByValue(Integer tariano, Integer tarimes, Double infocafi);
}
