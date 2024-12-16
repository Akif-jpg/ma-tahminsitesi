var sessionid = document.querySelector('meta[name="JSESSIONID"]').content;

function writeNoticiations(){
    fetch("/api/sec/listofallmails",{
        method: "GET",
        credentials: "same-origin",
        headers: {
            "Content-type": "application/json; charset=UTF-8",
            "Cookie": "JSESSIONID="+sessionid+"; Path=/; HttpOnly;",        
        }
        })
        .then((response) => {
            if(!response.ok)
                new Error(response.status);   
            return response.json();         
        })
        .then(json=>{
            
            //json data to html code
            const notsList = document.getElementById("notsList").innerHTML;

            var str = "<center><h3>Bildirimler</h3></center>";
            json.forEach(element => {
                str += "<li  class=\" w3-margin w3-paddding-64 w3-card\">"
                +"<div style=\"display:flex;\" class=\"w3-margin\">"
                +"<p class=\"w3-padding-16 w3-margin-right\">" + element["email"] + "</p>"                                                                  //email adress of sender
                +"<p class=\"w3-padding-16 w3-margin-right\">" + element["content"].substring(0,100) + "</p>"                                               // substr for too long messages
                +"<p class=\"w3-padding-16 w3-margin-right\">" + element["timestamp"].substring(0,10) + "</p>"                                              //date subdivided to day-month-year format
                +"<form style=\"margin-left:auto; margin-right:16;\" class=\"w3-padding-16\">"
                + "<label for=\"isRead\">okundu: </label>"
                +"<input id=\"isRead\" type=\"checkbox\" name=\"unchecked\" value=false + onchange=\"markAs("+element["id"]+")\""+ischecked(element) +"  />"//is readed by administrator
                +"</form>"
                +"</div>"
                +"</li>";        

            });
            document.getElementById("notsList").innerHTML = str;


            return json.json();
        }).catch(error=>{this.error = error});
}

function ischecked(element) {
    if (!element["checked"])
        return "unchecked";
    else
        return "checked";
}
function markAs(id) {
    console.log("markAs");
    /* 
    *   we send update request to yourserver.com/api/sec/update/id of notifications
    *   this will only update checked status of the notification
    */
}

function createNewPost() {
    console.log("createNewPost");
    var form = document.getElementById("createPostForm");
    form.addEventListener("submit", function(e){

    var formData = new FormData(form);

    // FormData nesnesini bir JSON nesnesine dönüştür
    var jsonObject = {};
    for (const [key, value]  of formData.entries()) {
        jsonObject[key] = value;
        
    }
  
    // convert json object to string
    var jsonPayload = JSON.stringify(jsonObject);

    fetch("/api/sec/createBet", {
        method: "POST",
        credentials: "include",
        body: jsonPayload,
        headers: {
            "Content-type": "application/json; charset=UTF-8",
            "Cookie": "JSESSIONID="+sessionid+"; Path=/; HttpOnly;"
        }
        })
        .then((response) => {         
            if(!response.ok){
                new Error(response.status);
            }             
            return response.json();
        });
    });


    document.getElementById('createNewPost').style.display='none' 
}

writeNoticiations();