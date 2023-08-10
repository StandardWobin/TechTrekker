const axios = require('axios');

let headers = {
    'Host': 'rest.arbeitsagentur.de',
    'Accept': '*/*',
    'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8',
    'Accept-Language': 'en-us',
    'User-Agent': 'Jobsuche/1070 CFNetwork/1220.1 Darwin/20.3.0'
};

let data = {
    'client_id': 'c003a37f-024f-462a-b36d-b001be4cd24a',
    'client_secret': '32a39620-32b3-4307-9aa1-511e3d7f48a8',
    'grant_type': 'client_credentials'
};

const lookup = async (keyWord) => {
    // get OAuthToken
    let access_token = await axios({
        method: 'post', url: 'https://rest.arbeitsagentur.de/oauth/gettoken_cc', headers, data,
    }).then(function (response) {
        // handle success
        return response.data['access_token'];
        // console.log(response);
    })
        .catch(function (error) {
            // TODO: smarter Error handlings here
            console.log(error);
        });
    // append Oath Token from last request
    headers = { "OAuthAccessToken": access_token }
    let page = 1;
    let listings = [];
    targetAmount = 1;
    // PAGINATION (result size is 100 max so we have to iterate over all results 100 wise)
    while (listings.length < targetAmount) {
        let config = {
            headers: headers,
            // search parameter for AA API
            params: {
                "was": keyWord,
                "page": page,
                "umkreis": 0,
                "size": 100,
                "angebotsart": 1,
                "pav": "false",
            }
        }

        let result = await axios.get(
            'https://rest.arbeitsagentur.de/jobboerse/jobsuche-service/pc/v4/jobs', config,
        ).then(function (response) {
            return { 'max': response.data.maxErgebnisse, 'listings': response.data.stellenangebote };

        })
            .catch(function (error) {
                // TODO: smarter Error handlings here
                console.log(error);
            });

        // iterate and fill data from pagination
        targetAmount = Math.max(targetAmount, result.max);
        listings = [...listings, ...result.listings];
        page += 1;
    }
    return listings;
}

// function call (entry point)
(async () => {
    let listings = await lookup('Vue.js');
    console.log(listings.length)
})()

