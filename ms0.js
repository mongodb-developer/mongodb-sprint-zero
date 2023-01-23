const { MongoClient, ServerApiVersion } = require('mongodb');

const uri = "mongodb+srv://main_user:main_pwd_001@m10-default.5rkb2.mongodb.net?retryWrites=true&w=majority";
const client = new MongoClient(uri, { serverApi: ServerApiVersion.v1 });

const args = process.argv;
console.log(args);
let db = client.db("MongoSprint");
let collection = db.collection('NodeSandbox');

let doc1 = {
    "a": 1,
    "example": true,
    "time": new Date()
};

let doc2 = {
    "a": 3,
    "example": false,
    "time": new Date(),
    "newField": "new"
}

if (args.includes('insert')) {
    collection.insertOne(doc1);
}

if (args.includes('updateMatched')) {
    collection.updateMany({ "a": 1 }, { "$unset": { "updated": true } });
}

if (args.includes('updateUnmatched')) {
    let updateResponse = collection.updateMany({ "a": 2 }, { "$set": { "blah": true } });
    console.log(JSON.stringify(updateResponse, null, 2));
}

if (args.includes('replaceMatched')) {
    collection.replaceOne({ "a": 1 }, doc2);
}

if (args.includes('upsert')) {
    collection.updateMany({ "a": 2 }, { "$set": { "updated": true } }, { upsert: true });
}

if (args.includes('reset')) {
    collection.deleteMany({});
}

if (args.includes('currentop')) {
    db = client.db('admin');
    let updateResponse = db.aggregate([{ "$currentOp": {} }]);
    updateResponse.forEach(document => {
        console.log(JSON.stringify(document, null, 2));
    });
}
