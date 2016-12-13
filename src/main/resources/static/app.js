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
  var _response = [];
  console.log(req.param);
  if (req.query.gt != undefined) {
    _response = [
      { "periano": 2015 },
      { "periano": 2014 },
      { "periano": 2013 },
      { "periano": 2012 },
      { "periano": 2011 }
    ];        
  } else if (req.query.lt != undefined) {
    _response = [
      { "periano": 2004 },
      { "periano": 2003 },
      { "periano": 2002 },
      { "periano": 2001 },
      { "periano": 2000 }
    ];          
  } else {
    _response = [
      { "periano": 2010 },
      { "periano": 2009 },
      { "periano": 2008 },
      { "periano": 2007 },
      { "periano": 2005 }
    ];    
  }
  res.status(200).send(_response);
});

app.get("/informe/periodos/:periano", function (req, res) {
  var _response = [
    { "pericodi": req.params.periano + "12", "periano": req.params.periano, "perimes": 12, "perifein": 0, "perifefi": 0 },
    { "pericodi": req.params.periano + "11", "periano": req.params.periano, "perimes": 11, "perifein": 0, "perifefi": 0 },
    { "pericodi": req.params.periano + "10", "periano": req.params.periano, "perimes": 10, "perifein": 0, "perifefi": 0 },
    { "pericodi": req.params.periano + "09", "periano": req.params.periano, "perimes": 09, "perifein": 0, "perifefi": 0 },
    { "pericodi": req.params.periano + "08", "periano": req.params.periano, "perimes": 08, "perifein": 0, "perifefi": 0 },
    { "pericodi": req.params.periano + "07", "periano": req.params.periano, "perimes": 07, "perifein": 0, "perifefi": 0 },
    { "pericodi": req.params.periano + "06", "periano": req.params.periano, "perimes": 06, "perifein": 0, "perifefi": 0 },
    { "pericodi": req.params.periano + "05", "periano": req.params.periano, "perimes": 05, "perifein": 0, "perifefi": 0 },
    { "pericodi": req.params.periano + "04", "periano": req.params.periano, "perimes": 04, "perifein": 0, "perifefi": 0 },
    { "pericodi": req.params.periano + "03", "periano": req.params.periano, "perimes": 03, "perifein": 0, "perifefi": 0 },
    { "pericodi": req.params.periano + "02", "periano": req.params.periano, "perimes": 02, "perifein": 0, "perifefi": 0 },
    { "pericodi": req.params.periano + "01", "periano": req.params.periano, "perimes": 01, "perifein": 0, "perifefi": 0 }
  ];
  res.status(200).send(_response);
});

app.post("/informe/upload", function (req, res) {
  console.log("uploaded");
  console.log(req.params);
  console.log(req.query);
  console.log(req.body);
  console.log(req.files);
  console.log(req);
  res.status(200).send("OK");
});

app.listen(process.env.PORT || 3000);