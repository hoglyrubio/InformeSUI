package com.empresaprivadaservicios.sui;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class InformeController {

  private static final Logger LOG = LoggerFactory.getLogger(InformeController.class);

  private InformeService informeService;
  private PeriodoService periodoService;

  @Autowired
  public InformeController(InformeService informeService, PeriodoService periodoService) {
    this.informeService = informeService;
    this.periodoService = periodoService;
  }

  /*
   *
   *  Carga de archivos
   *
   */

  @RequestMapping(path = "/informe/upload", method = RequestMethod.POST)
  public Map<String, Object> runImport(@RequestParam("infoperi") Integer infoperi, @RequestParam("infofile") MultipartFile infofile) {
    LOG.debug("/informe/upload infoperi: {} file: {}", infoperi, infofile.getOriginalFilename());

    try {
      Integer records = informeService.loadFile(infoperi, infofile.getInputStream());
      Map<String, Object> response = new HashMap<>();
      response.put("message", MessageFormat.format("Periodo {0} cargado con {1} registros", infoperi, records));
      return response;

    } catch (IOException ex) {
      throw new TechnicalException("Error cargando archivo", ex);
    }
  }

  /*
   *  Periodos
   *
   */

  @RequestMapping(path = "/informe/periodos", method = RequestMethod.GET)
  public List<Periodo> findAllPeriodos() {
    LOG.debug("/informe/periodos");
    return periodoService.findAll();
  }

  @RequestMapping(path = "/informe/periodos/{periano}", method = RequestMethod.GET)
  public List<Periodo> findAllPeriodosByAno(@PathVariable Integer periano) {
    LOG.debug("/informe/periodos periano: {}", periano);
    return periodoService.findAllPeriodosByAno(periano);
  }

  @RequestMapping(path = "/informe/periodos/{infoperi}/count", method = RequestMethod.GET)
  public Long countByInfoperi(@PathVariable Integer infoperi) {
    LOG.debug("/informe/periodos/{}/count", infoperi);
    return informeService.countByInfoperi(infoperi);
  }

  @RequestMapping(path = "/informe/anios", method = RequestMethod.GET)
  public List<Integer> obtainAnios(HttpServletRequest request) {
    LOG.debug("/informe/anios");

    Validate.notNull(request.getParameter("size"), "Falta el parámetro size");

    Integer size = Integer.valueOf(request.getParameter("size"));

    if (request.getParameter("gt") != null) {
      Integer gt = Integer.valueOf(request.getParameter("gt"));
      return periodoService.findByPerianoGreaterThan(gt, size);
    }

    if (request.getParameter("lt") != null) {
      Integer lt = Integer.valueOf(request.getParameter("lt"));
      return periodoService.findByPerianoLessThan(lt, size);
    }

    return periodoService.findLastPeriano(size);
  }

  @RequestMapping(path="/informe/resumen/UsoEstrato/{infoperi}", method = RequestMethod.GET)
  public List<Object[]> resumenUsoEstrato(@PathVariable Integer infoperi) {
    return informeService.resumenUsoEstrato(infoperi);
  }

  @RequestMapping(path="/informe/resumen/Estado/{infoperi}", method = RequestMethod.GET)
  public List<Object[]> resumenEstado(@PathVariable Integer infoperi) {
    return informeService.resumenEstado(infoperi);
  }

}
