package com.empresaprivadaservicios.sui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeriodoService {
  
  @Autowired
  private PeriodoRepository periodoRepository;

  public List<Periodo> findAll() {
    return periodoRepository.findAll();
  }
  
}
