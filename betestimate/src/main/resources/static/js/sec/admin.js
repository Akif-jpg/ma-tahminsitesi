function getCookie(name) {
    let cookieValue = null;
    if (document.cookie && document.cookie !== '') {
        let cookies = document.cookie.split(';');
        for (let i = 0; i < cookies.length; i++) {
            let cookie = cookies[i].trim();
            // Does this cookie string begin with the name we want?
            if (cookie.substring(0, name.length + 1) === (name + '=')) {
                cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                break;
            }
        }
    }
    return cookieValue;
}

var sessionid = document.querySelector('meta[name="JSESSIONID"]').content;

function cPostTable(){//read json data from server
    //current host ip
    fetch("/api/listofallcbets")
    .then(response => {//if response status code is not 200
        if (!response.ok) {
            throw new Error("HTTP error " + response.status);
        }
        return response.json();
    })
    .then(json =>{
        var counter = 0;
        var str = "";
        JSON.parse(JSON.stringify(json)).map(element => {
            if(counter % 3 == 0){
                str = str  + "<tbody><tr>";
            }
            counter++;

            str = str + "<td>" 
            +"yayım tarihi= "+element["timestamp"]
            +"&#9"
            +"id=" + element["id"]
            +"<br>"
            + "başlık="+element["title"]
            + "<br>"
            + element["team1"]
            +"-vs-"
            + element["team2"] 
            +"<br>"
            +"<button onClick=deleteCurrentPost("+element["id"]+") class=\"w3-button w3-red w3-padding-small w3-margin-right\">Sil</button>"
            +"<button onClick=updateCurrentPost("+element["id"]+") class=\"w3-button w3-teal w3-padding-small w3-margin-right\">Güncelle</button>"
            +"<button onClick=dropToPost("+element["id"]+") class=\"w3-button w3-khaki w3-padding-small w3-margin-right\">Güncel postlardan düşür.</button>"
            + "</td>";

            if(counter % 3 == 0){
                str = str  + "</tr></tbody>";
            }
        });
        document.getElementById("currentPostTable").innerHTML = str;

    })
    .catch(function(){//throw exception
        this.data.error = true;
    });
}

function postTable(){//read json data from server
    //current host ip
    fetch("/api/listofallbets")
    .then(response => {//if response status code is not 200
        if (!response.ok) {
            throw new Error("HTTP error " + response.status);
        }
        return response.json();
    })
    .then(json =>{
        var counter = 0;
        var str = "";
        JSON.parse(JSON.stringify(json)).map(element => {
            if(counter % 3 == 0){
                str = str  + "<tbody><tr>";
            }
            counter++;
            str = str + "<td>" 
            +"yayım tarihi= "+ element["timestamp"]
            +"&#9"
            + "id=" + element["id"]
            +"<br>"
            + "başlık="+element["title"]
            + "<br>"
            + element["team1"]
            +"-vs-"
            + element["team2"] 
            +"<br>"
            +"<button onClick=deletePost("+element["id"]+") class=\"w3-button w3-red w3-padding-small w3-margin-right\">Sil</button>"
            +"<button onClick=updatePost("+element["id"]+") class=\"w3-button w3-teal w3-padding-small w3-margin-right\">Güncelle</button>"
            +"<button onClick=raiseToCPost("+element["id"]+") class=\"w3-button w3-green w3-padding-small w3-margin-right\">Güncel post yap.</button>"
            
            + "</td>";

            if(counter % 3 == 0){
                str = str  + "</tr></tbody>";
            }
        });
        document.getElementById("postTable").innerHTML = str;

    })
    .catch(function(){//throw exception
        this.data.error = true;
    });
}

function deleteCurrentPost(id) {
    console.log("deleteCurrentPost " + id);
    fetch("/api/sec/deleteCBet/"+id, {
        method: "POST",
        credentials: "same-origin",
        body: JSON.stringify({
            deletedPostId: id
        }),
        headers: {
            "Content-type": "application/json; charset=UTF-8",
            "Cookie": "JSESSIONID="+sessionid+"; Path=/; HttpOnly;"
        }
        })
        .then((json) => {console.log(json);
             cPostTable();
        });
}

function updateCurrentPost(id) {
    console.log("updateCurrentPost " + id);
}

function dropToPost(id) {
    console.log("dropToPost " + id);
    fetch("/api/getCBet/"+id,{
        method:"GET",
        credentials:"include",
        headers:{
            "Content-type": "application/json; charset=UTF-8",

        }
    }).then(response => {
        if (!response.ok) {
            throw new Error("HTTP error " + response.status);
        }
        
        return response.json();
          
       
    }).then(element=>{
        var jsonPayload = JSON.stringify(element);
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
                    throw new Error("HTTP error " + response.status);
                }
                dropToPostOperations(id);
            })
        }).catch(error => console.error(error)) 
        
}

function dropToPostOperations(id) {
    postTable();
    deleteCurrentPost(id); 
}

function deletePost(id) {
    console.log("deletePost " + id);    
    fetch("/api/sec/deleteBet/"+id,{
        method: "POST",
        credentials: "same-origin",
        body: JSON.stringify({
            deletedPostId: id
        }),
        headers: {
            "Content-type": "application/json; charset=UTF-8",
            "Cookie": "JSESSIONID="+sessionid+"; Path=/; HttpOnly;",        
        }
        })
        .then((json) => {console.log(json);
             postTable();
        });
}

function updatePost(id) {
    console.log("updateNewPost " + id);
}

function raiseToCPost(id) {
    console.log("raiseToCPost " + id);

    fetch("/api/getBet/"+id,{
        method:"GET",
        credentials:"include",
        headers:{
            "Content-type": "application/json; charset=UTF-8",

        }
    }).then(response => {
        if (!response.ok) {
            throw new Error("HTTP error " + response.status);
        }  
        return response.json();
          
       
    }).then(element=>{
        var jsonPayload = JSON.stringify(element);
        fetch("/api/sec/createCBet", {
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
                    throw new Error("HTTP error: " + response.status);
                   }
                   raiseToCPostOperations(id); 
            
            })
        }).catch(error => console.error(error))  
           
    

}

function raiseToCPostOperations(id){
    deletePost(id);
    cPostTable(); 
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
  
    // JSON nesnesini bir stringe dönüştür
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
            postTable();              
        });
    });


    document.getElementById('createNewPost').style.display='none'
}
cPostTable()

postTable()