import {getParameterByName, setTextNode} from '../../js/dom_utils.js'
import {getBackendUrl} from "../../js/configuration.js";

window.addEventListener('load', () => {
    fetchAndDisplayWorker();
});

function fetchAndDisplayWorker() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayWorker(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/jobs/'
        + getParameterByName('job') + '/workers/' + getParameterByName('worker'), true);
    xhttp.send();
}

function displayWorker(worker) {
    setTextNode('job', worker.job);
    setTextNode('surname', worker.surname);
    setTextNode('age', worker.age);
    setTextNode('name', worker.name);
}