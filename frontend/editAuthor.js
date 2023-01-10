function getBackendUrl(){
    return "http://localhost:8080"
}

window.addEventListener('load', () =>{
    let form = document.getElementsByClassName('addForm')[0]
    form.addEventListener('submit', event => editAuthor(event))

    getAuthor()
    

})

function getAuthor(){
    let authorId = new URLSearchParams(window.location.search).get("author")
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            author = JSON.parse(this.responseText)
            document.getElementById('id_form').value = author.id
            document.getElementById('name_form').value = author.name
            document.getElementById('date_form').value = author.dateOfBirth
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/authors/' + authorId, true);
    xhttp.send();
}

function editAuthor(event){

    let authorId = new URLSearchParams(window.location.search).get("author")
    const xhttp = new XMLHttpRequest();
    xhttp.open("PUT", getBackendUrl() + '/api/authors/' + authorId, true);
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            window.location.href='authors.html'
        }
    };

    event.preventDefault()
    let request_object = {
        'name' : document.getElementById('name_form').value,
        'dateOfBirth' : document.getElementById('date_form').value
    }

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request_object));  
      
    document.getElementById('id_form').value = ""
    document.getElementById('name_form').value = ""
    document.getElementById('date_form').value = ""

}