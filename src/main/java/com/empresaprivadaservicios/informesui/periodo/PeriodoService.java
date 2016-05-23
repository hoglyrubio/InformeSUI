package com.empresaprivadaservicios.informesui.periodo;

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

  public List<Periodo> findAllPeriodosByAno(Integer periano) {
    Sort sortByPericodiDesc = new Sort( new Sort.Order(Sort.Direction.DESC, "pericodi") );
    return periodoRepository.findAllByPeriano(periano, sortByPericodiDesc);
  }

  public List<Integer> findLastPeriano(Integer size) {
    return periodoRepository.obtainLastPeriano(size);
  }

  public List<Integer> findByPerianoGreaterThan(Integer gt, Integer size) {
    return periodoRepository.obtainPerianoGreaterThan(gt, size);
  }

  public List<Integer> findByPerianoLessThan(Integer lt, Integer size) {
    return periodoRepository.obtainPerianoLessThan(lt, size);
  }

}
