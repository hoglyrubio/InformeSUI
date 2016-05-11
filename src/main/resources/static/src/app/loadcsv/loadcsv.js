new Vue({

  el: '#formulario',

  data: {
    periodos: []
  },

  methods: {

    onSubmit1: function (event) {
      var infoperi = $('#infoperi');
      var infofile = $('#infofile');
      var formData = new FormData();
      formData.append('infoperi', infoperi);
      formData.append('infofile', infofile, infofile.name);
      console.log(formData);
      $.post("informe/upload", formData);
    },

    onSubmit2: function (event) {
      var form = document.querySelector("form");
      var formData = new FormData(form);
      console.log(form);
      console.log(formData);
      $.post("informe/upload", formData);
    },

    onSubmit3: function (event) {
      var serialized = $("#formLoadCsv").serialize();
      console.log(serialized);
      $.post("informe/upload", serialized);
    },

    onSubmit4: function (event) {
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
        processData: false
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
