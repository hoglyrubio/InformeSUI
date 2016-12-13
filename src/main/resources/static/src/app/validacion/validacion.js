new Vue({

  el: '#formulario',

  data: {
    periodos: [],
    resumenUsoEstrato: [],
    resumenUsoEstratoTotalUsuarios: 0,
    resumenUsoEstratoTotalConsumo: 0,
    resumenEstado: [],
    resumenEstadoTotalUsuarios: 0,
    resumenEstadoTotalConsumo: 0
  },

  methods: {

    onSubmit: function (event) {
      event.preventDefault();
      var formData = new FormData(document.getElementById("formValidacion"));
      var infoperi = formData.get("infoperi");   
      console.log("onSubmit");
      console.log(this);   
      this.loadResumenEstado(infoperi);
      this.loadResumenUsoEstrato(infoperi);
    },
    
    loadResumenUsoEstrato: function(infoperi) {
      console.log(this);
      var self = this;
      var url = "informe/resumen/UsoEstrato/" + infoperi;
      $.get(url, function (data) {
        self.resumenUsoEstrato = data;
        self.resumenUsoEstratoTotalUsuarios = 0;
        self.resumenUsoEstratoTotalConsumo = 0;
        for (i = 0; i < data.length; i++) {
          self.resumenUsoEstratoTotalUsuarios += data[i][3];
          self.resumenUsoEstratoTotalConsumo += data[i][4];          
        }
      }).fail(function (response) {
        var responseObject = jQuery.parseJSON(response.responseText)
        notifyError(responseObject.error, "Error consultando Informe: " + responseObject.message);
      });    
    },
    
    loadResumenEstado: function(infoperi) {
      console.log(this);
      var self = this;
      var url = "informe/resumen/Estado/" + infoperi;
      $.get(url, function (data) {
        self.resumenEstado = data;
        self.resumenEstadoTotalUsuarios = 0;
        self.resumenEstadoTotalConsumo = 0;
        for (i = 0; i < data.length; i++) {
          self.resumenEstadoTotalUsuarios += data[i][1];
          self.resumenEstadoTotalConsumo += data[i][2];          
        }        
      }).fail(function (response) {
        var responseObject = jQuery.parseJSON(response.responseText)
        notifyError(responseObject.error, "Error consultando Informe: " + responseObject.message);
      });    
    }
  },
  
  ready: function () {
    var self = this;
    $.get("informe/periodos", function (data) {
      self.periodos = data;
    }).fail(function (error) {
      var responseObject = jQuery.parseJSON(response.responseText)
      notifyError(responseObject.error, "Error cargando periodo: " + responseObject.message);
    });
  }

});
