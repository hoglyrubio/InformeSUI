package com.empresaprivadaservicios.sui;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class InformeController {

  private static final Logger LOG = LoggerFactory.getLogger(InformeController.class);
  
  @Autowired
  private InformeService informeService;
  
  @Autowired
  private PeriodoService periodoService;
  
  @RequestMapping(path = "/informe/periodos", method = RequestMethod.GET)
  public List<Periodo> findAllPeriodos() {
    LOG.debug("/informe/periodos -> findAllPeriodos()");
    return periodoService.findAll();
  }

  @RequestMapping(path = "/informe/upload", method = RequestMethod.POST)
  public void runImport(@RequestParam("infoperi") Integer infoperi, @RequestParam("infofile") MultipartFile infofile) {
    LOG.debug("/informe/upload -> runImport({})", infoperi);
    
    try {
      informeService.loadFile(infoperi, infofile.getInputStream());
    } catch (IOException ex) {
      throw new TechnicalException("Error cargando archivo", ex);
    }
  }

  @RequestMapping(path = "/informe/{infoperi}/count", method = RequestMethod.GET)
  public Long countByInfoperi(@PathVariable Integer infoperi) {
    return informeService.countByInfoperi(infoperi);
  }

}
