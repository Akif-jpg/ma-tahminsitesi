var date = new Date();

var day = String(date.getDate()).padStart(2, '0');
var month = String(date.getMonth() + 1).padStart(2, '0'); //January is 0!
var year = date.getFullYear();

date = year + '-' + month + '-' + day;

document.getElementById("setTime").value = date;


function changeList(){    
    console.log(document.getElementById("selectall").value )
    if(!document.getElementById("selectall").checked ){
        readJson();
    }else{
        readJson2();
    }
}

function filterByDate(){
    readJson();
}


function writeBetList(data){
    var str = "";
    data.slice(0,20).forEach(element => {//write data tstringify 
        console.log(element["title"]);
        str += 
        "<div class=\"w3-card w3-auto w3-margin w3-padding-16 card-postlink\" title=\"devamı için tıkla\" onclick=\"window.location.href='/page?id="+element["id"]+"'\">"
        +"<div  class=\"w3-margin card-post\" style=\"display:flex !important;\">"
        +"<h5 style=\"border-right: 2px solid black; \">"+element["timestamp"].substring(0,10) +"</h5>"
        +"<h5 style=\"padding-left:8px; border-right: 2px solid black; \">"+element["timestamp"].substring(11,16) +"</h5>"
        +"<h5 style=\"padding-left:8px; padding-right:8px;\">"+element["team1"]+" -VS- "+element["team2"]+"</h5>"      
        +"</div>"
        +"<p id=\"clickforcontinue\" style=\"display:none;\"> devamı için tıklayınız...</p>"
        +"</div>";
    });
    document.getElementById("listofposts").innerHTML = str;
}

function addZeroToStart(day){
    if(day<10){
        return "0"+day;
    }else{
        return day;
    }
}

function addDayToDate(date){
    var array = date.split("-");
    return array[0]+"-"+array[1]+"-"+addZeroToStart(parseInt(array[2])+1)
}

function readJson2(){
    fetch("api/listofallbets",{
        method:"GET",
        credentials: "include",
        headers: {
            "Content-type": "application/json; charset=UTF-8",
        },        
    })//fetch bets by date 
    .then(response => {//if response status code is not 200
        if (!response.ok) {
            throw new Error("HTTP error " + response.status);//throw new Error
        }
        return response.json();
    })
    .then(json =>{
        console.log("bet list json file= " + JSON.stringify(json));
        writeBetList(json);
        return json;
    })
    .catch(function(){//throw exception
        this.data.error = true;
    });
}

function readJson(){//read json data from server
    //current host ip
    var date1 = document.getElementById("setTime").value;
   
   date1 = date1+"T00:00:00.208+00:00";
   var date2 = addDayToDate(date1)+"T00:00:00.208+00:00";
   var jsonObject = {"date1":date1,"date2": date2};
   var jsonPayload = JSON.stringify(jsonObject);
   console.log(jsonPayload);

    fetch("api/listBetByDate",{
        method: 'POST',
        credentials: "include",
        headers: {
            "Content-type": "application/json; charset=UTF-8",
        },
        body: jsonPayload
        
    })//fetch bets by date 
    .then(response => {//if response status code is not 200
        if (!response.ok) {
            throw new Error("HTTP error " + response.status);//throw new Error
        }
        return response.json();
    })
    .then(json =>{
        console.log("bet list json file= " + JSON.stringify(json));
        writeBetList(json);
        return json;
    })
    .catch(function(){//throw exception
        this.data.error = true;
    });
}
changeList();