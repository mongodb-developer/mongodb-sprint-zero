### ~~~~~   Notes ~~~~~
# Author: <>
# This is for referential purposes and is not meant to be production ready code.
# There are no warranties associated with these functions
# in order to run this, execute the script and include command line arguments to perform various functions
# for example, run "python3 ms0.py insert" to create a collection and insert a sample document



### ~~~~~   Variables and functions    ~~~~~~

import datetime
import pymongo
import sys
import json

from bson.objectid import ObjectId
# objInstance = ObjectId("63b8401dac80f1a9d6f6bc0c")

from faker import Faker
fake = Faker()

#operations array
operations = sys.argv[1:]


print("Running")

client = pymongo.MongoClient("<MDB_CONNECTION_STR>")

db = client.ms0

sandboxCollection = db.sandbox

###  ~~~~~   Sample docs and functions   ~~~~~~

doc1 = {"a":1,
        "example":True,
    "time":datetime.datetime.utcnow()}

doc2 = {"a":3,
        "example":False,
    "time":datetime.datetime.utcnow(),
    "newField":"new"}


### ~~~~~   List of operations   ~~~~~

if 'insert' in operations:
    sandboxCollection.insert_one(doc1)

if 'findIt' in operations:
    returnVal = sandboxCollection.find({})
    print(returnVal[0])    

if 'updateMatched' in operations:   
    sandboxCollection.update_many({"a":1},{"$unset":{"updated":True}})

if 'updateUnmatched' in operations:    
    updateResponse = sandboxCollection.update_many({"a":2},{"$set":{"blah":True}})
    print(json(updateResponse))

if 'replaceMatched' in operations:
    sandboxCollection.replace_one({"a":1},doc2)

if 'upsert' in operations:    
    sandboxCollection.update_many({"a":2},{"$set":{"updated":True}},upsert=True)

if 'deleteAll' in operations:    
    sandboxCollection.delete_many({})

if 'currentop' in operations:
    db = client.admin
    updateResponse = db.aggregate([{ "$currentOp": {} }])
    for document in updateResponse:
        print(document)