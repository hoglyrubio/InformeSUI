new Vue({

  el: '#formulario',

  data: {
    periodos: []
  },

  methods: {

    onSubmit: function (event) {
      event.preventDefault();
      var formData = new FormData(document.getElementById("formAcueducto"));
      var infoperi = formData.get("infoperi");   
      var self = this;
      var url = "acueducto/procesar/" + infoperi;
      $.get(url, function (response) {
        console.log(response.responseText);
        var responseObject = jQuery.parseJSON(response.responseText)
        notifySucess(responseObject.message);
      }).fail(function (response) {
        console.log(response.responseText);
        var responseObject = jQuery.parseJSON(response.responseText)
        notifyError(responseObject);
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