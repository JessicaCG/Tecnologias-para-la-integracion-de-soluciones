var mensaje=''

function ini(){
   mensaje='<?xml version="1.0" encoding="utf-8"?>'+
   '<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">'+
   '<Body>'+
   '<SumarRequest xmlns="http://www.example.org/calculadora">'+
    '<a>'+document.getElementById('a').value + '</a>'+
    '<b>'+document.getElementById('b').value + '</b>'+
    '</SumarRequest>'+
    '</Body>'+
    '</Envelope>'; 
}


function soap() {
    // alert('hola')
    ini();
    axios.post('http://localhost:8081/ws/calculadora', mensaje,{
        headers:{
            'Content-Type' : 'text/xml'
        }
    })
    .then(function(response){
        //console-log(respinse.data)
        document.getElementById('r').value = resultado(response.data)
    })
    .catch(err => console.log(err));
}
/*para pintar resultado en la pantalla y sea visible*/
function resultado(rXml){
    var parser = new DOMParser(); //variable tipo parser
    var xmlDoc = parser.parseFromString(rXml, "text/xml");//convierte a cadena lo que le paso 
    var resul = xmlDoc.getElementsByTagName("ns2:resultado")[0].childNodes[0].nodeValue; //devuelve el primer valor que encuentra con nombre resultado
    return resul; //lo pinta 
}