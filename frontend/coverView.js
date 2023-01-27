function getBackendUrl(){
    return "http://localhost:8080"
}

window.addEventListener('load', () =>{
    getSongFromURL()

})

function getSongFromURL(){
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displaySong(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/covers/' + new URLSearchParams(window.location.search).get("cover"), true);
    xhttp.send();

}

function displaySong(response) {
    let song_div = document.getElementsByClassName("coverInformation")[0]
    song_div.appendChild(document.createTextNode("Cover info:"))
    let list = document.createElement('ul')

    let link = document.getElementById('back')
    link.href = "covers.html"

    let e0 = document.createElement('li')
    e0.innerText = "id: " + response.id
    list.appendChild(e0)

    let e1 = document.createElement('li')
    e1.innerText = "Description: " + response.desc
    list.appendChild(e1)

    let e12 = document.createElement('li')
    e12.innerText = "Author: " + response.author
    list.appendChild(e12)

    let e3 = document.createElement('li')
    e3.innerText = "file: " + response.file
    list.appendChild(e3)

    song_div.appendChild(list)
}