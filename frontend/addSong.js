function getBackendUrl(){
    return "http://localhost:8080"
}

window.addEventListener('load', () =>{
    let form = document.getElementsByClassName('addForm')[0]
    form.addEventListener('submit', event => createSong(event))
    let backLink = document.getElementById("backLink")
    backLink.href = 'authorView.html?author='+new URLSearchParams(window.location.search).get("author")
})

function createSong(event){
    let authorId = new URLSearchParams(window.location.search).get("author")

    const xhttp = new XMLHttpRequest();
    xhttp.open("PUT", getBackendUrl() + '/api/authors/' + authorId + '/songs', true);
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201) {
            window.location.href='authorView.html?author='+authorId
        }
    };

    event.preventDefault()
    let request_object = {
        'id' : document.getElementById('id_form').value,
        'title' : document.getElementById('title_form').value,
        'dateOfRelease' : document.getElementById('date_form').value,
        'time' : document.getElementById('time_form').value,

    }

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request_object));  

    document.getElementById('id_form').value = ""
    document.getElementById('title_form').value = ""
    document.getElementById('date_form').value = ""
    document.getElementById('time_form').value = ""

}