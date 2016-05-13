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
        success: function(response) {
          var responseObject = jQuery.parseJSON(response)
          $.notify(
            { message: responseObject.message },
            { type: "success" }
          );
        },
        error: function(response) {
          var responseObject = jQuery.parseJSON(response.responseText)
          $.notify(
            { title: responseObject.error, message: responseObject.message },
            { type: "danger", delay: 0 }
          );
        }
      });
    }
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
