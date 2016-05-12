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

import javax.servlet.http.HttpServletRequest;

@RestController
public class InformeController {

  private static final Logger LOG = LoggerFactory.getLogger(InformeController.class);

  @Autowired
  private InformeService informeService;

  @Autowired
  private PeriodoService periodoService;

  @RequestMapping(path = "/informe/upload", method = RequestMethod.POST)
  public void runImport(@RequestParam("infoperi") Integer infoperi, @RequestParam("infofile") MultipartFile infofile) {
    LOG.debug("/informe/upload -> runImport({})", infoperi);

    try {
      informeService.loadFile(infoperi, infofile.getInputStream());
    } catch (IOException ex) {
      throw new TechnicalException("Error cargando archivo", ex);
    }
  }

  @RequestMapping(path = "/informe/periodos", method = RequestMethod.GET)
  public List<Periodo> findAllPeriodos() {
    LOG.debug("/informe/periodos -> findAllPeriodos()");
    return periodoService.findAll();
  }

  @RequestMapping(path = "/informe/periodos/{periano}", method = RequestMethod.GET)
  public List<Periodo> findAllPeriodosByAno(@PathVariable Integer periano) {
    LOG.debug("/informe/periodos -> findAllPeriodos()");
    return periodoService.findAllPeriodosByAno(periano);
  }

  @RequestMapping(path = "/informe/{infoperi}/count", method = RequestMethod.GET)
  public Long countByInfoperi(@PathVariable Integer infoperi) {
    return informeService.countByInfoperi(infoperi);
  }

  @RequestMapping(path = "/informe/anios", method = RequestMethod.GET)
  public List<Integer> obtainAnios(HttpServletRequest request) {

    if (request.getParameter("size") == null) {
      throw new BusinessException("Falta el parámetro size", HttpStatus.BAD_REQUEST);
    }

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

}
