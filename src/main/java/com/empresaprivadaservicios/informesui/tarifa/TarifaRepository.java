package com.empresaprivadaservicios.informesui.tarifa;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@CacheConfig(cacheNames = "tarifas")
public interface TarifaRepository extends JpaRepository<Tarifa, TarifaPk> {

  @Cacheable
  @Query(value="select * from sui_tarifa where tariano = ?1 and tarimes = ?2 and taricodi = 'CF' and taricate <> -1 and ?3 between floor(tarivalo) and ceil(tarivalo)", nativeQuery = true)
  Tarifa findTarifaCFByValue(Integer tariano, Integer tarimes, Double infocafi);

  @Cacheable
  @Query(value="select * from sui_tarifa where tariano = ?1 and tarimes = ?2 and tariclus = ?3 and taricodi = ?4", nativeQuery = true)
  Tarifa findOne(Integer tariano, Integer tarimes, Integer tariclus, String taricodi);

  @CacheEvict
  Tarifa save(Tarifa tarifa);
}
