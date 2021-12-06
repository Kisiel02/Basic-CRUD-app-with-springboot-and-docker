import {getBackendUrl} from "../../js/configuration.js";
import {getParameterByName} from "../../js/dom_utils.js";

window.addEventListener('load', () => {
    const infoForm = document.getElementById('jobEditForm');
    infoForm.addEventListener('submit', event => editJob(event));
    fetchAndDisplayJob();
});

function fetchAndDisplayJob() {
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
    xhttp.open("GET", getBackendUrl() + '/api/jobs/' + getParameterByName('job'), true);
    xhttp.send();
}

function editJob(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayJob();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/jobs/' +
        getParameterByName('job'), true);


    const request = {
        'salary': parseInt(document.getElementById('salary').value),
        'studiesNeeded': document.getElementById('studiesNeeded').value === 'on'
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    alert(JSON.stringify(request));
    xhttp.send(JSON.stringify(request));
}