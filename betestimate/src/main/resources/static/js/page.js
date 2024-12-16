const queryString = window.location.search;

// Create a new URLSearchParams instance
const urlParams = new URLSearchParams(queryString);

// Get a parameter from the URL


if(urlParams.has("id")) {
    const myParam = urlParams.get("id");
    const page = document.getElementById("page");
    url = "/api/getBet/"+myParam;
    console.log(url)
    fetch("/api/getBet/"+myParam,{
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
        var str = "";
        str = str + "<center><h3>"+element["title"]+"</h3></center>"
        +"<center><img src=\"/images/"+element["image_url"]+"\" class=\"w-50\"></center>"
        +"<p>"+element["content"]+"</p>";
        page.innerHTML = str;
      
    })
    .catch(error => console.error(error))
  
}else if(urlParams.has("cid")){
    const myParam = urlParams.get("cid");
    const page = document.getElementById("page");
    fetch("/api/getCBet/"+myParam,{
        method:"GET",
        credentials:"include",
        headers:{
            "Content-type": "application/json; charset=UTF-8",

        }
    }).then(
        response => {
            if (!response.ok) {
                throw new Error("HTTP error " + response.status);
            }
            return response.json();
              
           
        }
    )
    .then(element => {
        var str = "";
        str = str + "<center><h3>"+element["title"]+"</h3></center>"
        +"<center><img src=\"/images/"+element["image_url"]+"\" class=\"w-50\"></center>"
        +"<p>"+element["content"]+"</p>";
        page.innerHTML = str;
        
    }).catch(error => console.error(error))
}else{
    console.error("Invalid parameter");
}