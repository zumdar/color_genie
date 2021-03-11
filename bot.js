console.log('the bot is starting!')
var Twit = require('twit');
var config = require('./config');
var T = new Twit(config);

var exec = require('child_process').exec;
var fs = require('fs');



//setting up a user stream

// var stream = T.stream('user');
// //anytime someone follows me 
// stream.on('follow', followed);
// function followed(event_msg){
//     console.log('the follow bot is starting');
//     var name = event_msg.source.name;
//     var screenName = event_msg.source.screen_name;
//     tweetIt('.@' + screenName + ' thank so much for believeing in rainbow power')
// }





tweetIt();
setInterval(tweetIt, 1000*60*60);



function tweetIt(txt) {
    var cmd = 'genie_2_manyStyles/genie_2_manystyles';
    exec(cmd, processing);

    function processing(data) {
        var filename = 'genie_2_manyStyles/genie_2_manyStyles/output.png';
        var params = {
            encoding: 'base64'
        }
        var b64 = fs.readFileSync(filename, params);
        T.post('media/upload', {media_data: b64}, uploaded);
        var r = Math.floor( Math.random()*10000);
        function uploaded(err, data, response) {
            //this is where i will tweet
            var id = data.media_id_string;
            console.log(data);
            var tweet = {
                status: '#colorGenie' + ' ' +  r,
                media_ids: [id]
            }
            T.post('statuses/update', tweet, tweeted)
        }
        console.log(data);
        console.log('finished');
    }


    function tweeted(err, data, response) {
        if (err) {
            console.log("Something went wrong!");
            console.log(data)
        } else {
            console.log("it worked!");
        }
    }
}




// var params = { 
//     q: 'synthesizer', 
//     count: 10 
// };
// // https://developer.twitter.com/en/docs/twitter-api/v1/tweets/search/api-reference/get-search-tweets
// T.get('search/tweets', params, gotData); 

// function gotData(err, data, response) {
//     var tweets = data.statuses;
//     for (var i = 0; i < tweets.length; i++){
//     console.log(tweets[i].text)
//     }
//   };