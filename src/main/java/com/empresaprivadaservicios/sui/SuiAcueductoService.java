package com.empresaprivadaservicios.sui;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.empresaprivadaservicios.sui.SuiHelper.ifnull;

@Service
public class SuiAcueductoService {

  private final InformeRepository informeRepository;
  private final SuiAcueductoRepository acueductoRepository;
  private final PeriodoRepository periodoRepository;

  @Autowired
  public SuiAcueductoService(InformeRepository informeRepository, SuiAcueductoRepository acueductoRepository, PeriodoRepository periodoRepository) {
    this.informeRepository = informeRepository;
    this.acueductoRepository = acueductoRepository;
    this.periodoRepository = periodoRepository;
  }

  public Integer process(Integer infoperi) {
    Periodo periodo = periodoRepository.findOne(infoperi);

    acueductoRepository.deleteBySuiAcueductoPk_periodo(infoperi);


    List<Informe> informes = informeRepository.findByInformePk_infoperi(infoperi);
    for (Informe informe : informes) {
      SuiAcueducto sui = transform(informe, periodo);
      acueductoRepository.save(sui);
    }
    return informes.size();
  }

  private SuiAcueducto transform(Informe informe, Periodo periodo) {
    // 1. Nuid
    SuiAcueducto sui = new SuiAcueducto(informe.getInformePk().getInfoperi(), informe.getInformePk().getInfocodi());
    // 2. Numero de cuenta o contrato
    sui.setC02(informe.getInformePk().getInfocodi());
    // 3. Codigo dane depto
    sui.setC03("54");
    // 4. Codigo dane mpio
    sui.setC04("405");
    // 5. Zona IGAC
    sui.setC05("00");
    // 6. Sector IGAC
    sui.setC06("00");
    // 7. Manzana o vereda IGAC
    sui.setC07("0000");
    // 8. Numero del predio IGAC
    sui.setC08("0000");
    // 9. Condicion de propiedad del predio IGAC
    sui.setC09("000");
    // 10. Direccion
    sui.setC10( informe.getInfodire() );
    // 11. Numero de factura
    sui.setC11( informe.getInforeci() );
    // 12. Fecha expedicion factura
    sui.setC12( periodo.getPerifege() );
    // 13. fecha inicio periodo
    sui.setC13( periodo.getPerifein() );
    // 14. Dias facturados
    Days peridias = Days.daysBetween(new LocalDate(periodo.getPerifefi()), new LocalDate(periodo.getPerifein()));
    sui.setC14(peridias.getDays());

    return sui;
  }

}
