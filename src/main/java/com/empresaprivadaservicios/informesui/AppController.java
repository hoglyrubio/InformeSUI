package com.empresaprivadaservicios.informesui;

import com.empresaprivadaservicios.informesui.informe.InformeService;
import com.empresaprivadaservicios.informesui.periodo.Periodo;
import com.empresaprivadaservicios.informesui.periodo.PeriodoService;
import com.empresaprivadaservicios.informesui.sui.SuiAcueductoService;
import com.empresaprivadaservicios.informesui.sui.SuiAlcantarilladoService;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AppController {

  private static final Logger LOG = LoggerFactory.getLogger(AppController.class);

  private InformeService informeService;
  private PeriodoService periodoService;
  private SuiAcueductoService acueductoService;
  private SuiAlcantarilladoService alcantarilladoService;

  @Autowired
  public AppController(InformeService informeService, PeriodoService periodoService, SuiAcueductoService acueductoService, SuiAlcantarilladoService alcantarilladoService) {
    this.informeService = informeService;
    this.periodoService = periodoService;
    this.acueductoService = acueductoService;
    this.alcantarilladoService = alcantarilladoService;
  }

  @RequestMapping(path = "/informe/upload", method = RequestMethod.POST)
  public Map<String, Object> runImport(@RequestParam("infoperi") Integer infoperi, @RequestParam("infofile") MultipartFile infofile) {
    LOG.debug("/informe/upload infoperi: {} file: {}", infoperi, infofile.getOriginalFilename());
    try {
      Integer records = informeService.loadFile(infoperi, infofile.getInputStream());
      Map<String, Object> response = new HashMap<>();
      response.put("message", MessageFormat.format("Periodo {0} cargado con {1} registros", infoperi.toString(), records));
      return response;

    } catch (IOException ex) {
      throw new TechnicalException("Error cargando archivo", ex);
    }
  }

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

  @RequestMapping("/acueducto/procesar/{pericodi}")
  public Map<String, Object> processAcueducto(@PathVariable Integer pericodi) {
    Integer records = acueductoService.process(pericodi);
    Map<String, Object> response = new HashMap<>();
    response.put("message", MessageFormat.format("Procesado Acueducto periodo: {0} con {1} registros", pericodi.toString(), records));
    return response;
  }

  @RequestMapping("/acueducto/descargar/{pericodi}")
  public void descargarAcueducto(@PathVariable Integer pericodi, HttpServletResponse response) {
    try {
      String lines = acueductoService.obtainCsvLines(pericodi);
      LOG.info(lines);
      response.setContentType("text/csv");
      response.setHeader("Content-Disposition", String.format("inline; filename=\"%s-acu.csv\"", pericodi));
      ServletOutputStream out = response.getOutputStream();
      out.write(lines.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/alcantarillado/procesar/{pericodi}")
  public Map<String, Object> processAlcantarillado(@PathVariable Integer pericodi) {
    Integer records = alcantarilladoService.process(pericodi);
    Map<String, Object> response = new HashMap<>();
    response.put("message", MessageFormat.format("Procesado Alcantarillado periodo: {0} con {1} registros", pericodi.toString(), records));
    return response;
  }

  @RequestMapping("/alcantarillado/descargar/{pericodi}")
  public void descargarAlcantarillado(@PathVariable Integer pericodi, HttpServletResponse response) {
    try {
      String lines = alcantarilladoService.obtainCsvLines(pericodi);
      LOG.info(lines);
      response.setContentType("text/csv");
      response.setHeader("Content-Disposition", String.format("inline; filename=\"%s-alc.csv\"", pericodi));
      ServletOutputStream out = response.getOutputStream();
      out.write(lines.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
