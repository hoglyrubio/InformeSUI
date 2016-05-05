package com.empresaprivadaservicios.sui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PeriodoService {
  
  @Autowired
  private PeriodoRepository periodoRepository;

  public List<Periodo> findAll() {
    Sort sortByPericodiDesc = new Sort( new Sort.Order(Sort.Direction.DESC, "pericodi") );
    return periodoRepository.findAll(sortByPericodiDesc);
  }
  
}
