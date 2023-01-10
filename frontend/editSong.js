function getBackendUrl(){
    return "http://localhost:8080"
}

window.addEventListener('load', () =>{
    let form = document.getElementsByClassName('addForm')[0]
    form.addEventListener('submit', event => editSong(event))

    getSong()
    let backLink = document.getElementById("backLink")
    backLink.href = 'authorView.html?author='+new URLSearchParams(window.location.search).get("author")

})

function getSong(){
    let songId = new URLSearchParams(window.location.search).get("song")
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            song = JSON.parse(this.responseText)
            document.getElementById('id_form').value = song.id
            document.getElementById('title_form').value = song.title
            document.getElementById('date_form').value = song.dateOfRelease
            document.getElementById('time_form').value = song.time
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/songs/' + songId, true);
    xhttp.send();
}

function editSong(event){

    let author = new URLSearchParams(window.location.search).get("author")
    let song = new URLSearchParams(window.location.search).get("song")
    const xhttp = new XMLHttpRequest();
    xhttp.open("PUT", getBackendUrl() + '/api/authors/' + author +'/songs/' + song, true);
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            window.location.href='authorView.html?author='+author
        }
    };

    event.preventDefault()
    let request_object = {
        'title' : document.getElementById('plevel_form').value,
        'dateOfRelease' : document.getElementById('date_form').value,
        'time' : document.getElementById('dsc_form').value
    }

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request_object));  
      
    document.getElementById('id_form').value = ""
    document.getElementById('title_form').value = ""
    document.getElementById('date_form').value = ""
    document.getElementById('time_form').value = ""

}