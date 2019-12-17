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

loader.show();
fetch(URLS.destinations)
    .then(response => response.json())
    .then(items => {
        let allDestinations = items[0]; //String []
        let myDestinations = items[1]; //String []

        allDestinations.forEach((destination, index) => {
            if (isInMyDestinations(destination, myDestinations)) {
                $(`#${index + 1}`).remove();
            } else {
                $(`#details${index + 1}`).remove();
            }
        });

        loader.hide();
    });
