package com.empresaprivadaservicios.informesui.sui;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SuiAlcantarilladoRepository extends CrudRepository<SuiAlcantarillado, SuiAlcantarilladoPk> {

  SuiAlcantarillado save(SuiAlcantarillado suiAlcantarillado);

  Long deleteBySuiAlcantarilladoPk_periodo(Integer periodo);

  List<SuiAlcantarillado> findBySuiAlcantarilladoPk_periodo(Integer periodo);

}
