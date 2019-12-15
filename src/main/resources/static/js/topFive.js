$('#myForm input').on('change', function () {
    let message = $('input[name=radioBtn]:checked', '#myForm').val();
    getTopFive(message);
});

const getTopFive = function (message) {

    let type = message;
    fetch(`/api/top-five/${message}`, {method: 'get'})
        .then(response => response.json())
        .then(data => {
            let result = "";
            console.log(data);
            console.log(type);
            if (type === "destinations") {
                result = createDestinationsTable(data);
            } else if (type === "fishings") {
                result = createFishingsTable(data);
            } else if (type === "fishes") {
                result = createFishesTable(data);
            }

            $('.top-five').html(result);
            setHeader(type);
        });
};

const createDestinationsTable = function (destinations) {
    let result = `<table class="table table-hover w-50 mx-auto label-color font-weight-bold">
        <thead>
        <tr class="row mx-auto border border-white">
            <th class="col-md-2 text-center font-italic">#</th>
            <th class="col-md-3 text-center font-italic">Image</th>
            <th class="col-md-2 text-center font-italic">Town</th>
            <th class="col-md-2 text-center font-italic">Creator</th>
            <th class="col-md-3 text-center font-italic">Count of Fishings</th>
        </tr>
            <tbody>`;

    destinations.forEach((destination, index) => {
        result += destinationsTable(destination, index);
    });

    result += `</tbody>
    </table>`;

    return result;
};

let destinationsTable = function (destination, index) {
    let table = `
        </thead>
            <tr class="row mx-auto border border-white">
                <td class="col-md-2 text-center">${index + 1}</td>
                <td class="col-md-3 text-center">
                    <div class="text-center"><img src="${destination.imgUrl}" width="100" height="50"
                                                  class="img-thumb" alt="Destination"></div>
                </td>
                <td class="col-md-2 text-center">${destination.townName}</td>
                <td class="col-md-2 text-center">${destination.creator}</td>
                <td class="col-md-3 text-center">${destination.fishingsCount}</td>
            </tr>`;

    return table;
};

const createFishingsTable = function (fishings) {
    let result = `<table class="table table-hover w-50 mx-auto label-color font-weight-bold">
        <thead>
        <tr class="row mx-auto border border-white">
            <th class="col-md-2 text-center font-italic">#</th>
            <th class="col-md-2 text-center font-italic">Date</th>
            <th class="col-md-2 text-center font-italic">Town Name</th>
            <th class="col-md-2 text-center font-italic">Fisherman</th>
            <th class="col-md-2 text-center font-italic">Count of Fishes</th>
            <th class="col-md-2 text-center font-italic">Count of Lures</th>
        </tr>
            <tbody>`;

    fishings.forEach((fishing, index) => {
        result += fishingTable(fishing, index);
    });

    result += `</tbody>
    </table>`;

    return result;
};

let fishingTable = function (fishing, index) {
    let table = `
        </thead>
            <tr class="row mx-auto border border-white">
                <td class="col-md-2 text-center">${index + 1}</td>
                <td class="col-md-2 text-center">${fishing.date}</td>
                <td class="col-md-2 text-center">${fishing.townName}</td>
                <td class="col-md-2 text-center">${fishing.creator}</td>
                <td class="col-md-2 text-center">${fishing.countOfFishes}</td>
                <td class="col-md-2 text-center">${fishing.countOfLures}</td>
            </tr>`;

    return table;
};


const createFishesTable = function (fishes) {

    let result = `<table class="table table-hover w-50 mx-auto label-color font-weight-bold">
        <thead>
        <tr class="row mx-auto border border-white">
            <th class="col-md-2 text-center font-italic">#</th>
            <th class="col-md-2 text-center font-italic">Fish Name</th>
            <th class="col-md-2 text-center font-italic">Fisherman</th>
            <th class="col-md-3 text-center font-italic">Weight</th>
            <th class="col-md-3 text-center font-italic">Length</th>
        </tr>
            <tbody>`;

    fishes.forEach((fish, index) => {
        result += fishTable(fish, index);
    });

    result += `</tbody>
    </table>`;

    return result;
};

let fishTable = function (fish, index) {
    let table = `
        </thead>
            <tr class="row mx-auto border border-white">
                <td class="col-md-2 text-center">${index + 1}</td>
                <td class="col-md-2 text-center">${fish.fishName}</td>
                <td class="col-md-2 text-center">${fish.creator}</td>
                <td class="col-md-3 text-center">${fish.weightInKilograms} kg.</td>
                <td class="col-md-3 text-center">${fish.lengthInCentimeters} cm.</td>
            </tr>`;

    return table;
};

const setHeader = function (type) {
    let message = "";
    switch (type) {
        case "destinations":
            message = "Top 5 Fishing Destinations";
            break;
        case "fishings":
            message = "Top 5 Fishings";
            break;
        case "fishes":
            message = "Top 5 Big Fishes";
            break;
    }

    let html = `<div id="welcome" class="row justify-content-center">
            <h1 class="text-center welcome"><em>${message}!</em>
            </h1>
        </div>`;

    $('#welcome').html(html);
};