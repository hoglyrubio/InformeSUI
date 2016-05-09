var express = require('express');
var app = express();

app.use(express.static('./'));

app.get("/informe/periodos", function (req, res) {
  var _response = [
    { "pericodi": 201512, "periano": 2015, "perimes": 12, "perifein": 0, "perifefi": 0 },
    { "pericodi": 201511, "periano": 2015, "perimes": 11, "perifein": 0, "perifefi": 0 },
    { "pericodi": 201510, "periano": 2015, "perimes": 10, "perifein": 0, "perifefi": 0 },
    { "pericodi": 201509, "periano": 2015, "perimes": 09, "perifein": 0, "perifefi": 0 },
    { "pericodi": 201508, "periano": 2015, "perimes": 08, "perifein": 0, "perifefi": 0 },
    { "pericodi": 201507, "periano": 2015, "perimes": 07, "perifein": 0, "perifefi": 0 },
    { "pericodi": 201506, "periano": 2015, "perimes": 06, "perifein": 0, "perifefi": 0 },
    { "pericodi": 201505, "periano": 2015, "perimes": 05, "perifein": 0, "perifefi": 0 },
    { "pericodi": 201504, "periano": 2015, "perimes": 04, "perifein": 0, "perifefi": 0 },
    { "pericodi": 201503, "periano": 2015, "perimes": 03, "perifein": 0, "perifefi": 0 },
    { "pericodi": 201502, "periano": 2015, "perimes": 02, "perifein": 0, "perifefi": 0 },
    { "pericodi": 201501, "periano": 2015, "perimes": 01, "perifein": 0, "perifefi": 0 }
  ];
  res.status(200).send(_response);
});

app.get("/informe/anios", function (req, res) {
  var _response = [
    { "periano": 2015 },
    { "periano": 2014 },
    { "periano": 2013 },
    { "periano": 2012 },
    { "periano": 2010 },
    { "periano": 2009 }
  ];
  res.status(200).send(_response);
});

app.listen(process.env.PORT || 3000);