package com.empresaprivadaservicios.sui;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.MessageFormat;

@Service
public class InformeService {

  private static final Logger LOG = LoggerFactory.getLogger(InformeService.class);

  private InformeRepository informeRepository;

  @Autowired
  public InformeService(InformeRepository informeRepository) {
    this.informeRepository = informeRepository;
  }

  public Long countByInfoperi(Integer infoperi) {
    return informeRepository.countByInformePk_infoperi(infoperi);
  }

  @Transactional
  public Integer loadFile(Integer infoperi, InputStream inputStream) throws IOException {

    Validate.isTrue(countByInfoperi(infoperi) == 0,
            MessageFormat.format("El periodo {0} ya se encuentra cargado", infoperi.toString()));

    Reader reader = new InputStreamReader(inputStream, "ISO-8859-1");
    CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(reader);

    int rn = 0;
    for (CSVRecord record : parser) {
      rn++;

      Informe informe = new Informe( infoperi, Integer.valueOf(record.get(InformeFields.INFOCODI)) );

      informe.setInfoesta( Integer.valueOf( record.get(InformeFields.INFOESTA)) );
      informe.setInfonomb( record.get(InformeFields.INFONOMB) );
      informe.setInfodire( record.get(InformeFields.INFODIRE) );
      informe.setInfozona( record.get(InformeFields.INFOZONA) );
      informe.setInfosuca( record.get(InformeFields.INFOSUCA) );
      informe.setInfolean( Integer.valueOf( record.get(InformeFields.INFOLEAN).replace(".00","")) );
      informe.setInfoleac( Integer.valueOf( record.get(InformeFields.INFOLEAC).replace(".00","")) );
      informe.setInfocons( Integer.valueOf( record.get(InformeFields.INFOCONS).replace(".00","")) );
      informe.setInfocate( record.get(InformeFields.INFOCATE) );
      informe.setInfoinan( Double.valueOf( record.get(InformeFields.INFOINAN)) );
      informe.setInfoinag( Double.valueOf( record.get(InformeFields.INFOINAG)) );
      informe.setInfoinal( Double.valueOf( record.get(InformeFields.INFOINAL)) );
      informe.setInfoinva( Double.valueOf( record.get(InformeFields.INFOINVA)) );
      informe.setInforeag( Double.valueOf( record.get(InformeFields.INFOREAG)) );
      informe.setInforeal( Double.valueOf( record.get(InformeFields.INFOREAL)) );
      informe.setInforeva( Double.valueOf( record.get(InformeFields.INFOREVA)) );
      informe.setInfocafi( Double.valueOf( record.get(InformeFields.INFOCAFI)) );
      informe.setInfocbas( Double.valueOf( record.get(InformeFields.INFOCBAS)) );
      informe.setInfoccom( Double.valueOf( record.get(InformeFields.INFOCCOM)) );
      informe.setInfocsun( Double.valueOf( record.get(InformeFields.INFOCSUN)) );
      informe.setInfoalca( Double.valueOf( record.get(InformeFields.INFOALCA)) );
      informe.setInfomedi( Double.valueOf( record.get(InformeFields.INFOMEDI)) );
      informe.setInfosure( Double.valueOf( record.get(InformeFields.INFOSURE)) );
      informe.setInfotanq( Double.valueOf( record.get(InformeFields.INFOTANQ)) );
      //informe.setInfoacom( Double.valueOf( record.get(InformeFields.INFOACOM)) );
      informe.setInfoacom( null );
      informe.setInfootca( Double.valueOf( record.get(InformeFields.INFOOTCA)) );
      informe.setInforeci( Integer.valueOf( record.get(InformeFields.INFORECI)) );
      informe.setInfonuat( Integer.valueOf( record.get(InformeFields.INFONUAT)) );
      informe.setInfovaim( Double.valueOf( record.get(InformeFields.INFOVAIM)) );
      informe.setInfoserv( Double.valueOf( record.get(InformeFields.INFOSERV)) );
      informe.setInfoajus( Double.valueOf( record.get(InformeFields.INFOAJUS)) );
      informe.setInfovapa( Double.valueOf( record.get(InformeFields.INFOVAPA)) );
      informe.setInfovano( Double.valueOf( record.get(InformeFields.INFOVANO)) );

      informeRepository.save(informe);
    }
    LOG.info("loadFile({}) {} registros cargados", infoperi, rn);
    return rn;
  }

}
