function getBackendUrl(){
    return "http://localhost:8080"
}

window.addEventListener('load', () =>{
    getAuthorFromURL()
    getGetAuthorSongs()
})

function getAuthorFromURL(){
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayAuthor(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/authors/' + new URLSearchParams(window.location.search).get("author"), true);
    xhttp.send();

    let songLink = document.getElementById("addSongLink")
    songLink.href += "?author=" + new URLSearchParams(window.location.search).get("author") 

}

function displayAuthor(response) {
    let author_div = document.getElementsByClassName("authorInformation")[0]
    author_div.appendChild(document.createTextNode("Author info:"))
    let list = document.createElement('ul')

    let e0 = document.createElement('li')
    e0.innerText = "id: " + response.id
    list.appendChild(e0)

    let e1 = document.createElement('li')
    e1.innerText = "Name: " + response.name
    list.appendChild(e1)

    let e2 = document.createElement('li')
    e2.innerText = "Date of birth: " + response.dateOfBirth
    list.appendChild(e2)

    author_div.appendChild(list)

}

function getGetAuthorSongs(){
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
                displayLicenses(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/authors/' + new URLSearchParams(window.location.search).get("author") +"/songs", true);
    xhttp.send();
}

function displayLicenses(response){
    let tableBody = document.getElementsByClassName("songTable")[0]

    while(tableBody.firstChild){
        tableBody.removeChild(tableBody.firstChild)
    }
    if (response.songs.length > 0){
        let author_div = document.getElementsByClassName("authorInformation")[0]
        if (author_div.childNodes.length == 2)
            author_div.appendChild(document.createTextNode("Songs:"))
        let headerRow = tableBody.insertRow(0)
        let headerCell1 = document.createElement("th")
        headerCell1.innerText = "id"
        headerRow.appendChild(headerCell1)
        let headerCell2 = document.createElement("th")
        headerCell2.innerText = "Title"
        headerRow.appendChild(headerCell2)
        let headerCell12 = document.createElement("th")
        headerCell12.innerText = "Time"
        headerRow.appendChild(headerCell12)
        let headerCell3 = document.createElement("th")
        headerCell3.innerText = "Date of release"
        headerRow.appendChild(headerCell3)
        let headerCell4 = document.createElement("th")
        headerCell4.innerText = "Details"
        headerRow.appendChild(headerCell4)
        let headerCell5 = document.createElement("th")
        headerCell5.innerText = "Update"
        headerRow.appendChild(headerCell5)
        let headerCell6 = document.createElement("th")
        headerCell6.innerText = "Destroy"
        headerRow.appendChild(headerCell6)

        response.songs.forEach(song => {
            createSongRow(song)
        })
    }
}

function createSongRow(song){
    let tableBody = document.getElementsByClassName('songTable')[0];

    let row = tableBody.insertRow(-1)
    let cell_id = document.createElement("td")
    cell_id.innerText = song.id
    row.appendChild(cell_id)

    let cell_plevel = document.createElement("td")
    cell_plevel.innerText = song.title
    row.appendChild(cell_plevel)

    let cell_time = document.createElement("td")
    cell_time.innerText = song.time
    row.appendChild(cell_time)

    let cell_date = document.createElement("td")
    cell_date.innerText = song.dateOfRelease
    row.appendChild(cell_date)

    let link_cell = document.createElement("td")
    let details_link = document.createElement("a")
    details_link.href = "songView.html?song="+song.id
    details_link.innerText = "More"
    link_cell.appendChild(details_link)
    row.appendChild(link_cell)

    let edit_cell = document.createElement("td")
    let edit_link = document.createElement("a")
    edit_link.innerText = "Edit"
    edit_link.href = "editSong.html?author="+new URLSearchParams(window.location.search).get("author") +"&song=" +song.id
    edit_cell.appendChild(edit_link)
    row.appendChild(edit_cell)

    let delete_cell = document.createElement("td")
    let delete_link = document.createElement("a")
    delete_link.innerText = "Delete"
    delete_link.addEventListener('click',  () => deleteSong(song))
    delete_cell.appendChild(delete_link)

    row.appendChild(delete_cell)
}

function deleteSong(song){
    const xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 202) {
                getGetAuthorSongs()
            }
        };
        xhttp.open("DELETE",getBackendUrl() + '/api/authors/' + new URLSearchParams(window.location.search).get("author") +"/songs/"+song.id, true);
        xhttp.send();
}