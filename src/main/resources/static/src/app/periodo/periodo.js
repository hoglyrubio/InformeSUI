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
        notifyError(jQuery.parseJSON(response.responseText));
      });            
    },
    
    requestAnios: function (url) {
      console.log("anios: " + url);
      var self = this;
      $.get(url, function (data) {
        if (data.length > 0) {
          self.anios = data; 
        }
      }).fail(function (response) {
        notifyError(jQuery.parseJSON(response.responseText));
      });            
    },

    obtainPeriodosByAnio: function (anio) {
      this.requestPeriodos("informe/periodos/" + anio);
    },

    obtainPreviousGroupOfAnios: function (anios) {
      var first = anios[0]; 
      this.requestAnios("informe/anios?gt=" + first + "&size=5");
    },

    obtainNextGroupOfAnios: function (anios) {
      var last = anios[ anios.length - 1 ];
      this.requestAnios("informe/anios?lt=" + last + "&size=5");
    }
  },
  
  ready: function () {
    this.requestAnios("informe/anios?size=5");
  }
});
