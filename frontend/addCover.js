function getBackendUrl(){
    return "http://localhost:8080"
}

window.addEventListener('load', () =>{
    let form = document.getElementsByClassName('addForm')[0]
    form.addEventListener('submit', event => createCover(event))
})

function createCover(event){
    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + '/api/covers/', true);
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201) {
            window.location.href='covers.html'
        }
    };

    event.preventDefault()
    let request_object = {
        'id' : document.getElementById('id_form').value,
        'desc' : document.getElementById('desc_form').value,
        'author' : document.getElementById('author_form').value,
        'file' : document.getElementById('file_form').value,

    }

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request_object));

    document.getElementById('id_form').value = ""
    document.getElementById('desc_form').value = ""
    document.getElementById('author_form').value = ""
    document.getElementById('file_form').value = ""

}
