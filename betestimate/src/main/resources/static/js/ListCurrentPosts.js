
function fillCoursalItem(data){
    data.slice(0,5).forEach(element => {//write data to list
        var str = document.getElementById("listcurrentposts").innerHTML;
        var counter = 2;
        document.getElementById("listcurrentposts").innerHTML=str
        +"<div class=\"carousel-item\">"
        +"<a href= \"/page?cid="+element["id"]+"\">"
        +"<img src=\"images/"+element["image_url"]+"\" class=\"d-block w-100\" alt=\""+counter+"\">" 
        +"<div class=\"h4 w3-padding-small w3-hover-light-gray w3-white w3-text-black w3-display-bottommiddle\">"
        +element["title"]
        +"</div>"
        +"</a>"
        +"</div>"
        ;
        counter++;

    });
}

function readCurrentJson(){//read json data from server
    //current host ip
    fetch("api/listofallcbets")
    .then(response => {//if response status code is not 200
        if (!response.ok) {
            throw new Error("HTTP error " + response.status);
        }
        return response.json();
    })
    .then(json =>{
        console.log("read current json file= " + JSON.stringify(json));
        const data = json;
        fillCoursalItem(data);
    })
    .catch(function(){//throw exception
        this.data.error = true;
    });
}
readCurrentJson();