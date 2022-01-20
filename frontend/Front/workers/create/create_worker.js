import {getBackendUrl} from "../../js/configuration.js";
import {getParameterByName} from "../../js/dom_utils.js";

window.addEventListener('load', () => {
    const infoForm = document.getElementById('createWorkerForm');
    infoForm.addEventListener('submit', event => addWorker(event));
    document.getElementById('title').innerText = 'Create new '
        + getParameterByName('job');
});

function addWorker(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            //fetchAndDisplayTeacher();
        }
    };
    xhttp.open("POST", getBackendUrl() + '/api/jobs/'
        + getParameterByName('job') + '/workers', true);


    const request = {
        'surname': document.getElementById('surname').value,
        'name': document.getElementById('name').value,
        'age': parseInt(document.getElementById('age').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}