/**
 *
 *
 */
$(document).ready(function () {
  console.log("ready()");
  //loadModule("landing");
});

/**
 *
 *
 */
Vue.filter('localeDateString', function (milliseconds) {
  var fecha = new Date(milliseconds);
  return fecha.toLocaleDateString();
});

/**
 *
 *
 */
function loadModule(name) {
  var _div = $("#main-content");
  var _src = "src/app/"+ name +"/"+ name +".html";
  console.log("loading module: " + _src);
  _div.load(_src);
}
