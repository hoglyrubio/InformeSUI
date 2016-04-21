package com.empresaprivadaservicios.sui;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InformeService {

  private static final Logger LOG = LoggerFactory.getLogger(InformeService.class);

  @Autowired
  private InformeRepository repository;

  public Long countByInfoperi(Integer infoperi) {
    return repository.countByInformePk_infoperi(infoperi);
  }

  public void loadFile(Integer infoperi, File file) throws IOException {
    Reader reader = new FileReader(file);
    CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(reader);

    LOG.info("Records: {}", parser.getRecordNumber());
    LOG.info("Headers: {}", parser.getHeaderMap());
    
    for (CSVRecord record : parser) {
      
      Informe informe = new Informe( infoperi, Integer.valueOf(record.get("INFOCODI")) );

      LOG.info("infoesta: {}", record.get("﻿INFOESTA"));
      
      informe.setInfoesta( Integer.valueOf( record.get("﻿INFOESTA")) );
      informe.setInfonomb( record.get("INFONOMB") );
      informe.setInfodire( record.get("INFODIRE") );
      informe.setInfozona( record.get("INFOZONA") );
      informe.setInfosuca( record.get("INFOSUCA") );
      informe.setInfolean( Integer.valueOf( record.get("INFOLEAN")) );
      informe.setInfoleac( Integer.valueOf( record.get("INFOLEAC")) );
      informe.setInfocons( Integer.valueOf( record.get("INFOCONS")) );
      informe.setInfocate( record.get("INFOCATE") );
      informe.setInfoinan( Double.valueOf( record.get("INFOINAN")) );
      informe.setInfoinag( Double.valueOf( record.get("INFOINAG")) );
      informe.setInfoinal( Double.valueOf( record.get("INFOINAL")) );
      informe.setInfoinva( Double.valueOf( record.get("INFOINVA")) );
      informe.setInforeag( Double.valueOf( record.get("INFOREAG")) );
      informe.setInforeal( Double.valueOf( record.get("INFOREAL")) );
      informe.setInforeva( Double.valueOf( record.get("INFOREVA")) );
      informe.setInfocafi( Double.valueOf( record.get("INFOCAFI")) );
      informe.setInfocbas( Double.valueOf( record.get("INFOCBAS")) );
      informe.setInfoccom( Double.valueOf( record.get("INFOCCOM")) );
      informe.setInfocsun( Double.valueOf( record.get("INFOCSUN")) );
      informe.setInfoalca( Double.valueOf( record.get("INFOALCA")) );
      informe.setInfomedi( Double.valueOf( record.get("INFOMEDI")) );
      informe.setInfosure( Double.valueOf( record.get("INFOSURE")) );
      informe.setInfotanq( Double.valueOf( record.get("INFOTANQ")) );
      informe.setInfoacom( Double.valueOf( record.get("INFOACOM")) );
      informe.setInfootca( Double.valueOf( record.get("INFOOTCA")) );
      informe.setInforeci( Integer.valueOf( record.get("INFORECI")) );
      informe.setInfonuat( Integer.valueOf( record.get("INFONUAT")) );
      informe.setInfovaim( Double.valueOf( record.get("INFOVAIM")) );
      informe.setInfoserv( Double.valueOf( record.get("INFOSERV")) );
      informe.setInfoajus( Double.valueOf( record.get("INFOAJUS")) );
      informe.setInfovapa( Double.valueOf( record.get("INFOVAPA")) );
      informe.setInfovano( Double.valueOf( record.get("INFOVANO")) );

      LOG.info("{}", informe);
    }
  }

}
