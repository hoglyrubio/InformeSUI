package com.empresaprivadaservicios.informesui.sui;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SuiCateSucaRepository extends JpaRepository<SuiCateSuca, SuiCateSucaPk> {

  SuiCateSuca findBySuiCateSucaPk(SuiCateSucaPk suiCateSucaPk);

}
