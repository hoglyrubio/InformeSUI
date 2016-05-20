package com.empresaprivadaservicios.sui;

import org.springframework.data.repository.CrudRepository;

public interface SuiAcueductoRepository extends CrudRepository<SuiAcueducto, SuiAcueductoPk> {

  SuiAcueducto save(SuiAcueducto suiAcueducto);

  Long deleteBySuiAcueductoPk_periodo(Integer periodo);
}
