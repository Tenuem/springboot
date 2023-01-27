function getBackendUrl(){
    return "http://localhost:8080"
}


window.addEventListener('load', () =>{
    let table = document.getElementsByClassName("coverTable")[0]
    while(table.firstChild){
        table.removeChild(table.firstChild)
    }
    getAllCovers()
})


function getAllCovers(){
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayCovers(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/covers', true);
    xhttp.send();

}

function displayCovers(response) {
    let tableBody = document.getElementsByClassName('coverTable')[0];
    while(tableBody.firstChild){
        tableBody.removeChild(tableBody.firstChild)
    }
    let headerRow = tableBody.insertRow(0)
    let headerCell1 = document.createElement("th")
    headerCell1.innerText = "id"
    headerRow.appendChild(headerCell1)
    let headerCell2 = document.createElement("th")
    headerCell2.innerText = "Description"
    headerRow.appendChild(headerCell2)
    let headerCell3 = document.createElement("th")
    headerCell3.innerText = "Author"
    headerRow.appendChild(headerCell3)

    let headerCell5 = document.createElement("th")
    headerCell5.innerText = "More"
    headerRow.appendChild(headerCell5)
    let headerCell7 = document.createElement("th")
    headerCell7.innerText = "Destroy"
    headerRow.appendChild(headerCell7)

    response.covers.forEach(cover => {
        createAuthorRow(cover)
    })
}

function createAuthorRow(cover){
    let tableBody = document.getElementsByClassName('coverTable')[0];

    let row = tableBody.insertRow(-1)
    let cell_id = document.createElement("td")
    cell_id.innerText = cover.id
    row.appendChild(cell_id)

    let cell_name = document.createElement("td")
    cell_name.innerText = cover.desc
    row.appendChild(cell_name)

    let cell_date = document.createElement("td")
    cell_date.innerText = cover.author
    row.appendChild(cell_date)

    let cell_link = document.createElement('td')
    let details_link = document.createElement("a")
    details_link.href = "coverView.html?cover="+cover.id
    details_link.innerText = "Details"

    cell_link.appendChild(details_link)
    row.appendChild(cell_link)

    let delete_cell = document.createElement("td")
    let delete_link = document.createElement("a")
    delete_link.innerText = "Delete"
    delete_link.addEventListener('click',  () => deleteAuthor(cover))
    delete_cell.appendChild(delete_link)

    row.appendChild(delete_cell)
}

function deleteCover(cover){
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            getAllCovers()
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/covers/' + cover.id, true);
    xhttp.send();
}

