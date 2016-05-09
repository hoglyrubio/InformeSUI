new Vue({

  el: '#tabla-periodos',

  data: {
    periodos: [],
    anios: []
  },

  ready: function () {
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

  }
});
