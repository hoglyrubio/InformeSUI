new Vue({

  el: '#formulario',

  data: {
    periodos: []
  },

  methods: {

    onSubmit: function (event) {
      var self = this;
      event.preventDefault();
      var formData = new FormData(document.getElementById("formLoadCsv"));
      console.log(formData);

      $.ajax({
        url: "informe/upload",
        type: "post",
        dataType: "html",
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        success: function (response) {
          var responseObject = jQuery.parseJSON(response)
          notifySuccess(responseObject.message);
        },
        error: function (response) {
          notifyError(jQuery.parseJSON(response.responseText));
        }
      });
    }
  },

  ready: function () {
    var self = this;
    $.get("informe/periodos", function (data) {
      self.periodos = data;
    }).fail(function (response) {
      notifyError(jQuery.parseJSON(response.responseText));
    });
  }

});
