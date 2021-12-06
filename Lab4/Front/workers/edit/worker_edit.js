import {getBackendUrl} from "../../js/configuration.js";
import {getParameterByName} from "../../js/dom_utils.js";

window.addEventListener('load', () => {
    const infoForm = document.getElementById('editWorkerForm');
    infoForm.addEventListener('submit', event => editWorker(event));
    fetchAndDisplayWorker();
    document.getElementById('title').innerText = 'Edit ' + getParameterByName('worker');
});

function fetchAndDisplayWorker() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }

    };
    xhttp.open("GET", getBackendUrl() + '/api/jobs/' + getParameterByName('job')
        + '/workers/' + getParameterByName('worker'), true);
    xhttp.send();
}

function editWorker(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayWorker();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/jobs/' +
        getParameterByName('job') + '/workers/'
        + getParameterByName('worker'), true);


    const request = {
        'name': document.getElementById('name').value,
        'age': parseInt(document.getElementById('age').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    //alert(JSON.stringify(request));
    xhttp.send(JSON.stringify(request));
}