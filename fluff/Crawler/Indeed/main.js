/*

little example how to crawl indeed, run by 

            node main.js

please check ineed terms of service before you use. 
*/

const puppeteer = require('puppeteer');
const randomUseragent = require('random-useragent');

// little helper to generate random integers in a range
const randomInt = (min, max) => {
    return (Math.floor(Math.random() * max) + min);
}

// start async call
(async () => {
    const browser = await puppeteer.launch();
    const page = await browser.newPage();

    // emulate random browser
    let userAgent = randomUseragent.getRandom();
    console.log("emulating user agent: " + userAgent); // gets a random user agent string
    await page.setExtraHTTPHeaders({
        'user-agent': userAgent,
        'upgrade-insecure-requests': '1',
        'accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8',
        'accept-encoding': 'gzip, deflate, br',
        'accept-language': 'en-US,en;q=0.9,en;q=0.8'
    });
    await page.setViewport({ width: 1920 + randomInt(-10, 10), height: 1280 + randomInt(-10, 10) });

    // go to target location
    let start = Date.now();
    await page.goto('https://de.indeed.com/jobs?q=developer&l=berlin');
    const html = await page.content();

    // if we get caught, some text will be displayed
    if (html.includes("ACCES DENIED")) {
        throw new Exception("Crawler got caught :(")
    }

    // get the amount of search results
    let amountQuery = await page.evaluate("document.getElementsByClassName('jobsearch-JobCountAndSortPane-jobCount')[0].innerText") + ""

    // extract the number using regex
    let amount = amountQuery.replace(/\D/g, '');

    if (amount == 0) {
        // TODO: make this a wanring.. 
        throw new Exception("no search results")
    }

    // screenhots for debug
    await page.screenshot({
        path: 'screenshots/screenshot_0.jpg'
    })

    // container for results
    let jobs = [];
    console.log(`Start crawling ${amount} jobs`);
    // iterate trough all pages
    for (let pageIndex = 0; pageIndex < Math.ceil(amount / 15); pageIndex++) {
        if (pageIndex > 0) {
            start = Date.now();

            // im a human a swear
            await new Promise(r => setTimeout(r, randomInt(100, 1000)))

            // TODO: add proxy or a least head changer
            await page.goto('https://de.indeed.com/jobs?q=developer&l=berlin&start='  + (pageIndex*10));

            const html = await page.content();

            // did we get caught now?
            if (html.includes("ACCES DENIED")) {
                throw new Exception("Crawler got caught :(")
            }
            await page.screenshot({
                path: 'screenshots/screenshot_' + (pageIndex) + '.jpg'
            })
        }

        // extract data
        let result =  await page.evaluate('Array.from(document.querySelectorAll("h2 > a"), element => element.innerText)');
        jobs = jobs.concat(result);

        // debug
        console.log(`Page ${pageIndex} done in ${(Date.now() - start)/1000}s | JOBS:` + jobs.length + " ["  + result[0] + ", ...");
    }

    console.log("** crawling finished ** ");
    await browser.close();
    return amount, jobs    
})();


