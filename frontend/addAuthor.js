function getBackendUrl(){
    return "http://localhost:8080"
}

window.addEventListener('load', () =>{
    let form = document.getElementsByClassName('addForm')[0]
    form.addEventListener('submit', event => createAuthor(event))
})

function createAuthor(event){
    const xhttp = new XMLHttpRequest();
    xhttp.open("PUT", getBackendUrl() + '/api/authors/', true);
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201) {
            window.location.href='authors.html'
        }
    };

    event.preventDefault()
    let request_object = {
        'id' : document.getElementById('id_form').value,
        'name' : document.getElementById('name_form').value,
        'dateOfBirth' : document.getElementById('date_form').value
    }

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request_object));  

    document.getElementById('id_form').value = ""
    document.getElementById('name_form').value = ""
    document.getElementById('date_form').value = ""

}