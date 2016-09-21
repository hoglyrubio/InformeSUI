package com.empresaprivadaservicios.informesui.sui;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SuiAcueductoRepository extends CrudRepository<SuiAcueducto, SuiAcueductoPk> {

  SuiAcueducto save(SuiAcueducto suiAcueducto);

  Long deleteBySuiAcueductoPk_periodo(Integer periodo);

  List<SuiAcueducto> findBySuiAcueductoPk_periodo(Integer periodo);

}
