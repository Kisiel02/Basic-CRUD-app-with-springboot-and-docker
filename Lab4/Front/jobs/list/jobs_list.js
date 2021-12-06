import {getBackendUrl} from "../../js/configuration.js";
import {clearElementChildren, createButtonCell, createLinkCell, createTextCell} from "../../js/dom_utils.js";

window.addEventListener('load', () => {
  fetchAndDisplayJobs();
});


function fetchAndDisplayJobs() {
  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
      displayJobs(JSON.parse(this.responseText));
    }
  }
  xhttp.open("GET", getBackendUrl() + '/api/jobs', true);
  xhttp.send();
}

function deleteJobs(job) {
  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 202) {
      fetchAndDisplayJobs();
    }
  }
  xhttp.open("DELETE", getBackendUrl() + '/api/jobs/' + job, true);
  xhttp.send();
}

function displayJobs(jobs) {
  let table = document.getElementById("table");
  clearElementChildren(table);
  jobs.jobs.forEach(job => {
    table.appendChild(createTableRow(job));
  })
}

function createTableRow(job) {
  let tr = document.createElement('tr');
  tr.appendChild(createTextCell(job));
  tr.appendChild(createLinkCell('view', '../view/job_view.html?job=' + job));
  tr.appendChild(createButtonCell('delete', () => deleteJobs(job)));
  return tr;
}

