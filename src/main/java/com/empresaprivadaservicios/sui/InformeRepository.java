package com.empresaprivadaservicios.sui;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;

public interface InformeRepository extends JpaRepository<Informe, InformePk> {

  Long countByInformePk_infoperi(Integer infoperi);

  Informe save(Informe informe);

  boolean exists(InformePk informePk);

  @Query(value = "select infoesta, infocate, infosuca, count(*) usuarios, sum(infocons) consumo from informe where infoperi = ?1 group by infoesta, infocate, infosuca", nativeQuery = true)
  List<Object[]> resumenUsoEstrato(Integer infoperi);

  @Query(value = "select infoesta, count(*) usuarios, sum(infocons) consumo from informe where infoperi = ?1 group by infoesta", nativeQuery = true)
  List<Object[]> resumenEstado(Integer infoperi);

  List<Informe> findByInformePk_infoperi(Integer infoperi);

}
