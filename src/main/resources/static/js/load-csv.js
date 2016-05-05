new Vue({

  el: '#formulario',

  data: {
    periodos: []
  },

  ready: function() {
    var self = this;
    $.get("informe/periodos", function(data) {
      self.periodos = data;
    }).fail(function(error) {
      console.log(error);
      alert(error.responseText);
    });
  }

});
