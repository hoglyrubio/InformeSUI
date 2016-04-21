package com.empresaprivadaservicios.sui;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
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
  private InformeService service;
  
  @RequestMapping(path = "/informe/upload", method = RequestMethod.POST)
  public void runImport(@RequestParam("peri") Integer infoperi, @RequestParam("file") MultipartFile multipartFile) {

    if (multipartFile.isEmpty()) {
      throw new BusinessException("Archivo vacío", HttpStatus.BAD_REQUEST);
    }
    
    try {
      File file = File.createTempFile(multipartFile.getName(), ".tmp");
      LOG.info(MessageFormat.format("Uploading {0} into {1}", multipartFile.getOriginalFilename(), file.getName()));
      BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
      FileCopyUtils.copy(multipartFile.getInputStream(), stream);
      stream.close();
      
      service.loadFile(infoperi, file);
      
    } catch (IOException ex) {
      throw new TechnicalException("Error cargando archivo", ex);
    }
  }

  @RequestMapping(path = "/informe/process", method = RequestMethod.POST)
  public String runProcess() {
    throw new TechnicalException("This is a technical exception");
  }

  @RequestMapping(path = "/informe/source", method = RequestMethod.GET)
  public String viewInforme() {
    throw new TechnicalException("This is a technical exception");
  }

  @RequestMapping(path = "/informe/export", method = RequestMethod.GET)
  public String runExport() {
    throw new BusinessException("This is a BusinessException", HttpStatus.BAD_REQUEST);
  }

  @RequestMapping(path = "/informe/{infoperi}/count", method = RequestMethod.GET)
  public Long countByInfoperi(@PathVariable Integer infoperi) {
    return service.countByInfoperi(infoperi);
  }

}
