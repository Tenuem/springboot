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
    xhttp.open("GET", getBackendUrl() + '/api/songs/' + new URLSearchParams(window.location.search).get("song"), true);
    xhttp.send();

}

function displaySong(response) {
    let song_div = document.getElementsByClassName("songInformation")[0]
    song_div.appendChild(document.createTextNode("Song info:"))
    let list = document.createElement('ul')

    let link = document.getElementById('back')
    link.href = "authorView.html?author=" + response.authorId

    let e0 = document.createElement('li')
    e0.innerText = "id: " + response.id
    list.appendChild(e0)

    let e1 = document.createElement('li')
    e1.innerText = "Title: " + response.title
    list.appendChild(e1)

    let e12 = document.createElement('li')
    e12.innerText = "Time: " + response.time
    list.appendChild(e12)

    let e2 = document.createElement('li')
    e2.innerText = "Date of release: " + response.dateOfRelease
    list.appendChild(e2)

    let e3 = document.createElement('li')
    e3.innerText = "AuthorId: " + response.authorId
    list.appendChild(e3)

    song_div.appendChild(list)
}