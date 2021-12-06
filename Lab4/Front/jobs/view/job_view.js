import {
    clearElementChildren,
    createButtonCell,
    createLinkCell,
    createTextCell,
    getParameterByName,
    setTextNode
} from '../../js/dom_utils.js'
import {getBackendUrl} from "../../js/configuration.js";

window.addEventListener('load', () => {
    fetchAndDisplayJob();
    fetchAndDisplayWorkers();
});

function fetchAndDisplayJob() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayJob(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/jobs/' + getParameterByName('job'), true);
    xhttp.send();
}

function fetchAndDisplayWorkers() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayWorkers(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/jobs/' + getParameterByName('job') + '/workers', true);
    xhttp.send();
}

function displayJob(job) {
    setTextNode('jobName', job.name);
    setTextNode('salary', job.salary);
    setTextNode('studiesNeeded', job.studiesNeeded);
    document.getElementById('newWorker').href = '../../workers/create/create_worker.html?job='
        + getParameterByName('job');
}

function displayWorkers(workers) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    workers.workers.forEach(worker => {
        let tr = document.createElement('tr');
        tr.appendChild(createTextCell(worker));
        tr.appendChild(createLinkCell('edit', '../../workers/edit/edit_worker.html?job='
            + getParameterByName('job') + '&worker=' + worker));
        tr.appendChild(createLinkCell('view', '../../workers/view/worker_view.html?job='
            + getParameterByName('job') + '&worker=' + worker));
        tr.appendChild(createButtonCell('delete', () => deleteWorker(worker)));
        tableBody.appendChild(tr);
    })
}

function deleteWorker(worker) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayWorkers();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/jobs/' + getParameterByName('job')
        + '/workers/' + worker, true);
    xhttp.send();
}


