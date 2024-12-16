var form = document.querySelector('form');

form.addEventListener('submit', e => {
    e.preventDefault();
    var formData = new FormData(form);

    var jsonObject = {};
    for (const [key, value]  of formData.entries()) {
      jsonObject[key] = value;
    }
  
    // JSON nesnesini bir stringe dönüştür
    var jsonPayload = JSON.stringify(jsonObject);

    fetch("/api/createMail",{
        method: "POST",
        credentials: "include",
        body: jsonPayload,
        headers: {
            "Content-type": "application/json; charset=UTF-8",
        }
        })
        .then((response) => {         
            if (!response.ok) {
                throw new Error("HTTP error " + response.status);
            }
            console.log(response.text)             
        });
    
    window.location.href = "/sentSuccessfully";
})