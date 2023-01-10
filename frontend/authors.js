function getBackendUrl(){
    return "http://localhost:8080"
}


window.addEventListener('load', () =>{
    let table = document.getElementsByClassName("authorTable")[0]
    while(table.firstChild){
        table.removeChild(table.firstChild)
    }
    getAllAuthors()
})


function getAllAuthors(){
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayAuthors(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/authors', true);
    xhttp.send();

}

function displayAuthors(response) {
    let tableBody = document.getElementsByClassName('authorTable')[0];
    while(tableBody.firstChild){
        tableBody.removeChild(tableBody.firstChild)
    }
    let headerRow = tableBody.insertRow(0)
    let headerCell1 = document.createElement("th")
    headerCell1.innerText = "id"
    headerRow.appendChild(headerCell1)
    let headerCell2 = document.createElement("th")
    headerCell2.innerText = "Name"
    headerRow.appendChild(headerCell2)
    let headerCell3 = document.createElement("th")
    headerCell3.innerText = "Date of birth"
    headerRow.appendChild(headerCell3)

    let headerCell5 = document.createElement("th")
    headerCell5.innerText = "More"
    headerRow.appendChild(headerCell5)
    let headerCell6 = document.createElement("th")
    headerCell6.innerText = "Update"
    headerRow.appendChild(headerCell6)
    let headerCell7 = document.createElement("th")
    headerCell7.innerText = "Destroy"
    headerRow.appendChild(headerCell7)

    response.authors.forEach(author => {
        createAuthorRow(author)
    })
}

function createAuthorRow(author){
    let tableBody = document.getElementsByClassName('authorTable')[0];

    let row = tableBody.insertRow(-1)
    let cell_id = document.createElement("td")
    cell_id.innerText = author.id
    row.appendChild(cell_id)

    let cell_name = document.createElement("td")
    cell_name.innerText = author.name
    row.appendChild(cell_name)


    let cell_date = document.createElement("td")
    cell_date.innerText = author.dateOfBirth
    row.appendChild(cell_date)

    let cell_link = document.createElement('td')
    let details_link = document.createElement("a")
    details_link.href = "authorView.html?author="+author.id
    details_link.innerText = "Details"
    
    cell_link.appendChild(details_link)
    row.appendChild(cell_link)

    let edit_cell = document.createElement("td")
    let edit_link = document.createElement("a")
    edit_link.innerText = "Edit"
    edit_link.href = "editAuthor.html?author="+author.id
    edit_cell.appendChild(edit_link)
    row.appendChild(edit_cell)

    let delete_cell = document.createElement("td")
    let delete_link = document.createElement("a")
    delete_link.innerText = "Delete"
    delete_link.addEventListener('click',  () => deleteAuthor(author))
    delete_cell.appendChild(delete_link)

    row.appendChild(delete_cell)
}

function deleteAuthor(author){
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            getAllAuthors()
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/authors/' + author.id, true);
    xhttp.send();
}

