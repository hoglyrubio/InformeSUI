package com.empresaprivadaservicios.sui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InformeService {

  @Autowired
  private InformeRepository repository;
  
  public Long countByInfoperi(Integer infoperi) {
    return repository.countByInformePk_infoperi(infoperi);
  }
  
}
