/**
 *
 */
$(document).ready(function () {
  console.log("ready()");
  //loadModule("landing");
});

/**
 *
 */
Vue.filter('localeDateString', function (milliseconds) {
  var fecha = new Date(milliseconds);
  return fecha.toLocaleDateString();
});

Vue.filter('numeric', function (value) {
  return numeral(value).format("0,0");
});

/**
 *
 */
function loadModule(name) {
  var _div = $("#main-content");
  var _src = "src/app/"+ name +"/"+ name +".html";
  console.log("loading module: " + _src);
  _div.load(_src);
}

/**
 *
 */
function notifySuccess(message) {
  $.notify(
    { message: message },
    { type: "success" }
  );
}

/**
 * 
 */
function notifyError(title, message) {
  $.notify(
    { title: title, message: message },
    { type: "danger", delay: 0 }
  );
}

/**
 * Muestra una notificación de error esperando un objeto JSON
 * del tipo de respuesta establecido en el backend
 */
function notifyError(responseObject) {
  $.notify(
    { title: responseObject.error, message: responseObject.message },
    { type: "danger", delay: 0 }
  );
}