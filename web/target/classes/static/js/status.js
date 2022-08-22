function min(array){
  let minVal = 100000;
    for (let i = 0; i < array.length; i++) {
      if(minVal > array[i]) minVal = array[i];
    }
  if (minVal < 0) {minVal = (parseInt(minVal/5, 10))*5-5;}
  else {minVal = (parseInt(minVal/5, 10))*5;}
  return minVal;
}

function max(array){
  let maxVal = -100000;
    for (let i = 0; i < array.length; i++) {
      if(maxVal < array[i]) maxVal = array[i];
    }
  if (maxVal < 0) {maxVal = (parseInt(maxVal/5, 10))*5;}
  else {maxVal = (parseInt(maxVal/5, 10))*5+5;}
  return maxVal;
}

function stepSize(array){
    let minVal = min(array);
    let maxVal = max(array);
    let val = Math.abs(maxVal - minVal);
    if (val > 10) return 5;
    else return 1;
}

let scaleGrid = 12;
let scaleData = scaleGrid * 2;
let scaleWindow = 0;

function getScaleData(){
    document.getElementById('scaleData').innerHTML = scaleData;
}

function getScaleWindow(){
    document.getElementById('scaleWindow').innerHTML = scaleWindow;
}

function plusScale(){
    if (scaleData + scaleGrid <= dateJ.length){
        scaleData += scaleGrid;
        getScaleData();
        temperature();
        humidity();
        pressure();
    }
}
function minusScale(){
    if(scaleData - scaleGrid > 0){
        scaleData -= scaleGrid;
        getScaleData();
        temperature();
        humidity();
        pressure();
    }

}

function leftScale(){
    if (scaleWindow + scaleData <= dateJ.length){
        scaleWindow += scaleGrid;
        getScaleWindow();
        temperature();
        humidity();
        pressure();
    }
}

function rightScale(){
    if(scaleWindow - scaleGrid >= 0){
        scaleWindow -= scaleGrid;
        getScaleWindow();
        temperature();
        humidity();
        pressure();
    }
}

window.onload = function (){
    temperature();
    humidity();
    pressure();
    getScaleData();
    getScaleWindow();
}

function temperature() {
    let date = dateJ.slice(dateJ.length - scaleData - scaleWindow, dateJ.length - scaleWindow);
    let temperature = temperatureJ.slice(dateJ.length - scaleData - scaleWindow, dateJ.length - scaleWindow);
    var ctx = document.getElementById('diagramTemperature').getContext('2d');
    var temperatureChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: date,
            datasets: [{
                label: 'Температура',
                borderColor: 'rgb(20, 50, 255)',
                data: temperature
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        min: min(temperature),
                        max: max(temperature),
                        stepSize: stepSize(temperature)
                    }
                }]
            }
        }
    });
}
function humidity() {
    if (typeof humidityJ != 'undefined'){
    let date = dateJ.slice(dateJ.length - scaleData - scaleWindow, dateJ.length - scaleWindow);
    let humidity = humidityJ.slice(dateJ.length - scaleData - scaleWindow, dateJ.length - scaleWindow);
    var ctx = document.getElementById('diagramHumidity').getContext('2d');
    var humidityChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: date,
            datasets: [{
                label: 'Влажность',
                borderColor: 'rgb(128, 0, 128)',
                data: humidity
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        min: min(humidity),
                        max: max(humidity),
                        stepSize: stepSize(humidity)
                    }
                }]
            }
        }

    });
    }
}

function pressure() {
    if (typeof pressureJ != 'undefined'){
    let date = dateJ.slice(dateJ.length - scaleData - scaleWindow, dateJ.length - scaleWindow);
    let pressure = pressureJ.slice(dateJ.length - scaleData - scaleWindow, dateJ.length - scaleWindow);
    var ctx = document.getElementById('diagramPressure').getContext('2d');
    var pressureChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: date,
            datasets: [{
                label: 'Давление',
                borderColor: 'rgb(255, 50, 20)',
                data: pressure
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        min: min(pressure),
                        max: max(pressure),
                        stepSize: stepSize(pressure)
                    }
                }]
            }
        }
    });
    }
}