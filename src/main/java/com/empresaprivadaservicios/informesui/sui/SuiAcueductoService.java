package com.empresaprivadaservicios.informesui.sui;

import com.empresaprivadaservicios.informesui.BusinessException;
import com.empresaprivadaservicios.informesui.tarifa.Tarifa;
import com.empresaprivadaservicios.informesui.tarifa.TarifaRepository;
import com.empresaprivadaservicios.informesui.informe.Informe;
import com.empresaprivadaservicios.informesui.informe.InformeConstantes;
import com.empresaprivadaservicios.informesui.informe.InformeRepository;
import com.empresaprivadaservicios.informesui.periodo.Periodo;
import com.empresaprivadaservicios.informesui.periodo.PeriodoRepository;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

import static com.empresaprivadaservicios.informesui.sui.SuiHelper.ifnull;

@Service
public class SuiAcueductoService {

  private final InformeRepository informeRepository;
  private final SuiAcueductoRepository acueductoRepository;
  private final PeriodoRepository periodoRepository;
  private final TarifaRepository tarifaRepository;
  private final SuiClaseUsoRepository suiClaseUsoRepository;

  @Autowired
  public SuiAcueductoService(InformeRepository informeRepository, SuiAcueductoRepository acueductoRepository, PeriodoRepository periodoRepository, TarifaRepository tarifaRepository, SuiClaseUsoRepository suiClaseUsoRepository) {
    this.informeRepository = informeRepository;
    this.acueductoRepository = acueductoRepository;
    this.periodoRepository = periodoRepository;
    this.tarifaRepository = tarifaRepository;
    this.suiClaseUsoRepository = suiClaseUsoRepository;
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

  @Transactional
  private SuiAcueducto transform(Informe informe, Periodo periodo) {
    // 1. Nuid
    SuiAcueducto sui = new SuiAcueducto(informe.getInformePk().getInfoperi(), informe.getInformePk().getInfocodi());
    // 2. Numero de cuenta o contrato
    sui.setC02(informe.getInformePk().getInfocodi());
    // 3. Codigo dane depto
    sui.setC03(SuiConstantes.DANE_DEPARTAMENTO);
    // 4. Codigo dane mpio
    sui.setC04(SuiConstantes.DANE_MUNICIPIO);
    // 5. Zona IGAC
    sui.setC05(SuiConstantes.IGAC_ZONA);
    // 6. Sector IGAC
    sui.setC06(SuiConstantes.IGAC_SECTOR);
    // 7. Manzana o vereda IGAC
    sui.setC07(SuiConstantes.IGAC_MANZANA);
    // 8. Numero del predio IGAC
    sui.setC08(SuiConstantes.IGAC_NUMERO);
    // 9. Condicion de propiedad del predio IGAC
    sui.setC09(SuiConstantes.IGAC_CONDICION);
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

    Tarifa tarifaCF = getUsoEstratoFromTarifaCF(informe, periodo);
    SuiClaseUso suiClaseUso = codigoClaseUso(tarifaCF);

    // 15. Codigo clase de uso
    sui.setC15( suiClaseUso.getCluscodi() );
    // 16. Unidades multiusuario residenciales
    sui.setC16(null);
    // 17. Unidades multiusuario no residenciales
    sui.setC17(null);
    // 18. Hogar comunitario o sustituto
    sui.setC18(SuiConstantes.NO_ES_HOGAR_COMUNITARIO_SUSTITUTO);
    // 19. Estado del medidor
    sui.setC19( suiClaseUso.getCluscate() == InformeConstantes.USO_RESIDENCIAL ? SuiEstadoMedidor.BUEN_ESTADO.value() : SuiEstadoMedidor.DANADO.value() );
    // 20. Determinacion del consumo
    sui.setC20( suiClaseUso.getCluscate() == InformeConstantes.USO_RESIDENCIAL ? SuiDeterminacionConsumo.LEIDO.value() : SuiDeterminacionConsumo.PROMEDIO.value() );
    // 21. Lectura Anterior
    sui.setC21(informe.getInfoleac() - informe.getInfocons());
    // 22. Lectura Actual
    sui.setC22(informe.getInfoleac());
    // 23. Consumo del periodo en m3
    Number cons = ifnull(informe.getInfocons(), Integer.valueOf(0));
    sui.setC23((Double) cons);

    return sui;
  }

  private Tarifa getUsoEstratoFromTarifaCF(Informe informe, Periodo periodo) {
    Integer ano = periodo.getPeriano();
    Integer mes = periodo.getPerimes();
    Double valorCF = informe.getInfocafi();
    Tarifa tarifaCF = tarifaRepository.findTarifaCFByValue(ano, mes, valorCF);
    if (tarifaCF == null) {
      throw new BusinessException(MessageFormat.format("No se encuentra tarifa de CF. Codigo {0} Valor Cargo fijo {1}", informe.getInformePk().getInfocodi(), informe.getInfocafi()), HttpStatus.BAD_REQUEST);
    }
    return tarifaCF;
  }

  private SuiClaseUso codigoClaseUso(Tarifa tarifaCF) {
    String uso = tarifaCF.getTarifaPk().getTaricate().toString();
    Integer suca = tarifaCF.getTarifaPk().getTarisuca();
    SuiClaseUso suiClaseUso = suiClaseUsoRepository.findByUsoSuca(uso, suca);
    if (suiClaseUso == null) {
      throw new BusinessException(MessageFormat.format("No existe Uso: {0} Estrato: {1} en tabla de homologación", uso, suca), HttpStatus.BAD_REQUEST);
    }
    return suiClaseUso;
  }

}
