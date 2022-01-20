import {getBackendUrl} from "../../js/configuration.js";

window.addEventListener('load', () => {
    const infoForm = document.getElementById('createForm');
    infoForm.addEventListener('submit', event => addJob(event));
});

function addJob(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            //fetchAndDisplayTeacher();
        }
    };
    xhttp.open("POST", getBackendUrl() + '/api/jobs/', true);


    const request = {
        'name': document.getElementById('name').value,
        'salary': parseInt(document.getElementById('salary').value),
        'studiesNeeded': document.getElementById('studiesNeeded').value === 'on'
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}