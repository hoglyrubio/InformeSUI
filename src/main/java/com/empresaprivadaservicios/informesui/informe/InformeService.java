package com.empresaprivadaservicios.informesui.informe;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
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

    //Validate.isTrue(countByInfoperi(infoperi) == 0,
    //        MessageFormat.format("El periodo {0} ya se encuentra cargado", infoperi.toString()));

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
      informe.setInfolean( asInteger( record.get(InformeFields.INFOLEAN).replace(".00","")) );
      informe.setInfoleac( asInteger( record.get(InformeFields.INFOLEAC).replace(".00","")) );
      informe.setInfocons( asInteger( record.get(InformeFields.INFOCONS).replace(".00","")) );
      informe.setInfocate( record.get(InformeFields.INFOCATE) );
      informe.setInfoinan( asBigDecimal( record.get(InformeFields.INFOINAN)) );
      informe.setInfoinag( asBigDecimal( record.get(InformeFields.INFOINAG)) );
      informe.setInfoinal( asBigDecimal( record.get(InformeFields.INFOINAL)) );
      informe.setInfoinva( asBigDecimal( record.get(InformeFields.INFOINVA)) );
      informe.setInforeag( asBigDecimal( record.get(InformeFields.INFOREAG)) );
      informe.setInforeal( asBigDecimal( record.get(InformeFields.INFOREAL)) );
      informe.setInforeva( asBigDecimal( record.get(InformeFields.INFOREVA)) );
      informe.setInfocafi( asBigDecimal( record.get(InformeFields.INFOCAFI)) );
      informe.setInfocbas( asBigDecimal( record.get(InformeFields.INFOCBAS)) );
      informe.setInfoccom( asBigDecimal( record.get(InformeFields.INFOCCOM)) );
      informe.setInfocsun( asBigDecimal( record.get(InformeFields.INFOCSUN)) );
      informe.setInfoalca( asBigDecimal( record.get(InformeFields.INFOALCA)) );
      informe.setInfomedi( asBigDecimal( record.get(InformeFields.INFOMEDI)) );
      informe.setInfosure( asBigDecimal( record.get(InformeFields.INFOSURE)) );
      informe.setInfotanq( asBigDecimal( record.get(InformeFields.INFOTANQ)) );
      informe.setInfoacom( asBigDecimal( record.get(InformeFields.INFOACOM)) );
      informe.setInfoacom( BigDecimal.ZERO );
      informe.setInfootca( asBigDecimal( record.get(InformeFields.INFOOTCA)) );
      informe.setInforeci( asInteger( record.get(InformeFields.INFORECI)) );
      informe.setInfonuat( asInteger( record.get(InformeFields.INFONUAT)) );
      informe.setInfovaim( asBigDecimal( record.get(InformeFields.INFOVAIM)) );
      informe.setInfoserv( asBigDecimal( record.get(InformeFields.INFOSERV)) );
      informe.setInfoajus( asBigDecimal( record.get(InformeFields.INFOAJUS)) );
      informe.setInfovapa( asBigDecimal( record.get(InformeFields.INFOVAPA)) );
      informe.setInfovano( asBigDecimal( record.get(InformeFields.INFOVANO)) );

      informeRepository.save(informe);
    }
    LOG.info("loadFile({}) {} registros cargados", infoperi, rn);
    return rn;
  }

  private BigDecimal asBigDecimal(String value) {
    if (StringUtils.isEmpty(value)) {
      return BigDecimal.ZERO;
    }
    return new BigDecimal(value);
  }

  private Integer asInteger(String value) {
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
