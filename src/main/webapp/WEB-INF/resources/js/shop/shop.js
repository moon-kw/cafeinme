'use strict';

function patch(url, jsonObj) {

  fetch(url, {
    method: 'PATCH',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(jsonObj)      
  }).catch(error => console.error(error));

}