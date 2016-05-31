package com.empresaprivadaservicios.informesui.informe;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.MessageFormat;
import java.util.List;

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
      informe.setInfolean( integerValue( record.get(InformeFields.INFOLEAN).replace(".00","")) );
      informe.setInfoleac( integerValue( record.get(InformeFields.INFOLEAC).replace(".00","")) );
      informe.setInfocons( integerValue( record.get(InformeFields.INFOCONS).replace(".00","")) );
      informe.setInfocate( record.get(InformeFields.INFOCATE) );
      informe.setInfoinan( doubleValue( record.get(InformeFields.INFOINAN)) );
      informe.setInfoinag( doubleValue( record.get(InformeFields.INFOINAG)) );
      informe.setInfoinal( doubleValue( record.get(InformeFields.INFOINAL)) );
      informe.setInfoinva( doubleValue( record.get(InformeFields.INFOINVA)) );
      informe.setInforeag( doubleValue( record.get(InformeFields.INFOREAG)) );
      informe.setInforeal( doubleValue( record.get(InformeFields.INFOREAL)) );
      informe.setInforeva( doubleValue( record.get(InformeFields.INFOREVA)) );
      informe.setInfocafi( doubleValue( record.get(InformeFields.INFOCAFI)) );
      informe.setInfocbas( doubleValue( record.get(InformeFields.INFOCBAS)) );
      informe.setInfoccom( doubleValue( record.get(InformeFields.INFOCCOM)) );
      informe.setInfocsun( doubleValue( record.get(InformeFields.INFOCSUN)) );
      informe.setInfoalca( doubleValue( record.get(InformeFields.INFOALCA)) );
      informe.setInfomedi( doubleValue( record.get(InformeFields.INFOMEDI)) );
      informe.setInfosure( doubleValue( record.get(InformeFields.INFOSURE)) );
      informe.setInfotanq( doubleValue( record.get(InformeFields.INFOTANQ)) );
      //informe.setInfoacom( doubleValue( record.get(InformeFields.INFOACOM)) );
      informe.setInfoacom( 0D );
      informe.setInfootca( doubleValue( record.get(InformeFields.INFOOTCA)) );
      informe.setInforeci( integerValue( record.get(InformeFields.INFORECI)) );
      informe.setInfonuat( integerValue( record.get(InformeFields.INFONUAT)) );
      informe.setInfovaim( doubleValue( record.get(InformeFields.INFOVAIM)) );
      informe.setInfoserv( doubleValue( record.get(InformeFields.INFOSERV)) );
      informe.setInfoajus( doubleValue( record.get(InformeFields.INFOAJUS)) );
      informe.setInfovapa( doubleValue( record.get(InformeFields.INFOVAPA)) );
      informe.setInfovano( doubleValue( record.get(InformeFields.INFOVANO)) );

      informeRepository.save(informe);
    }
    LOG.info("loadFile({}) {} registros cargados", infoperi, rn);
    return rn;
  }

  private Double doubleValue(String value) {
    if (StringUtils.isEmpty(value)) {
      return 0D;
    }
    return Double.valueOf(value);
  }

  private Integer integerValue(String value) {
    if (StringUtils.isEmpty(value)) {
      return 0;
    }
    return Integer.valueOf(value);
  }

  public List<Object[]> resumenUsoEstrato(Integer infoperi) {
    List<Object[]> response = informeRepository.resumenUsoEstrato(infoperi);
    return response;
  }

  public List<Object[]> resumenEstado(Integer infoperi) {
    List<Object[]> response = informeRepository.resumenEstado(infoperi);
    return response;
  }

}
