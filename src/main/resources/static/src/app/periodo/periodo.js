new Vue({

  el: '#tabla-periodos',

  data: {
    periodos: [],
    anios: []
  },

  methods: {

    requestPeriodos: function (url) {
      console.log("periodos: " + url);
      var self = this;
      $.get(url, function (data) {
        self.periodos = data;
      }).fail(function (error) {
        var msg = "Error obteniendo Periodos desde:" + url + ". Message:" + error.responseText;
        console.log(msg);
        alert(msg);
      });            
    },
    
    requestAnios: function (url) {
      console.log("anios: " + url);
      var self = this;
      $.get(url, function (data) {
        self.anios = data;
      }).fail(function (error) {
        var msg = "Error obteniendo Periodos desde:" + url + ". Message:" + error.responseText;
        console.log(msg);
        alert(msg);
      });            
    },

    obtainPeriodosByAnio: function (anio) {
      this.requestPeriodos("informe/periodos/" + anio);
    },

    obtainPreviousGroupOfAnios: function (anios) {
      var first = anios[0].periano; 
      this.requestAnios("informe/anios?gt=" + first);
    },

    obtainNextGroupOfAnios: function (anios) {
      var last = anios[ anios.length - 1 ].periano;
      this.requestAnios("informe/anios?lt=" + last);
    }
  },
  
  ready: function () {
    this.requestPeriodos("informe/periodos");
    this.requestAnios("informe/anios");

/*    
    var self = this;

    $.get("informe/periodos", function (data) {
      self.periodos = data;
    }).fail(function (error) {
      console.log(error);
      alert(error.responseText);
    });

    $.get("informe/anios", function (data) {
      self.anios = data;
    }).fail(function (error) {
      console.log(error);
      alert(error.responseText);
    });
*/
  }
});
