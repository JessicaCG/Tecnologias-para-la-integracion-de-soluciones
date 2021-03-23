var mensaje = '<?xml version="1.0" encoding="utf-8"?>' +
    '<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">' +
    '<Body>' +
    '<SumarRequest xmlns="http://www.example.org/calculadora">' +
    '<a>4</a>' +
    '<b>2</b>' +
    '</SumarRequest>' +
    '</Body>' +
    '</Envelope>';

function soap(){
    //alert('hola')
    axios.post('http://localhost:8081/ws/calculadora', mensaje, {
        headers:{
            'Content-Type' : 'text/xml'
        }
    })
    .then(response => console.log(response.data))
    .catch(err =>console.log(err));
    //.catch(error => {
      //  console.log(error.response)
    //});
}
