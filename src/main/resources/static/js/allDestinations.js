const loader = {
    show: () => {
        $('#page-loader').show();
    },
    hide: () => {
        $('#page-loader').hide();
    },
};

const URLS = {
    destinations: '/api/destinations',
};

const isInMyDestinations = (destination, myDestinations) => {
    return myDestinations.includes(destination);
};

const addButton = (destination, index, myDestinations) => {

    let button = `<a id="${index}" th:href="@{/destinations/add-to-my/{townName}(townName=${destination.townName})}"
                       class="btn btn-primary font-weight-bold text-white btn-sm">Add To My Destinations</a>`;
    if (isInMyDestinations(destination, myDestinations)) {
        button = "";
    }

    return button;
};


loader.show();
fetch(URLS.destinations)
    .then(response => response.json())
    .then(items => {
        let allDestinations = items[0]; //String []
        let myDestinations = items[1]; //String []

        allDestinations.forEach((destination, index) => {
            let button = addButton(destination, index, myDestinations);
            if (isInMyDestinations(destination, myDestinations)) {
                $(`#${index + 1}`).remove();
            }
        });

        loader.hide();
    });


$('#items-table').on('submit', '.buy-item-form', function (ev) {
    const url = $(this).attr('action');

    loader.show();
    fetch(url, {method: 'post'})
        .then(data => {
            console.log(data)
            loader.hide();
        });

    ev.preventDefault();
    return false;
});