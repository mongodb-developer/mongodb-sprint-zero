### ~~~~~   Notes ~~~~~
# Author: Winston Vargo winston.vargo@mongodb.com
# This is for referential purposes and is not meant to be production ready code.
# There are no warranties associated with these functions
# in order to run this, execute the script and include command line arguments to perform various functions
# for example, run "python3 ms0.py init" to create 2 collections and insert a sample document to both



### ~~~~~   Variables and functions    ~~~~~~

import datetime
import pymongo
import sys
import json

from bson.objectid import ObjectId
# objInstance = ObjectId("63b8401dac80f1a9d6f6bc0c")

from faker import Faker
fake = Faker()


operations = sys.argv[1:]

client = pymongo.MongoClient("mongodb://<u>:<p>@winvargo-shard-00-00.6ngvd.mongodb.net:27017,winvargo-shard-00-01.6ngvd.mongodb.net:27017,winvargo-shard-00-02.6ngvd.mongodb.net:27017/?ssl=true&replicaSet=WinVargo-shard-0&authSource=admin&retryWrites=true&w=majority")

db = client.initialSprint

userCollection = db.users
toolCollection = db.tools

###  ~~~~~   Sample docs and functions   ~~~~~~

tool1 = {
    "_id":ObjectId("63b8401dac80f1a9d6f6bc0c"), # THIS IS FOR EXAMPLE REFERENTIAL INTEGRITY AND SHOULD NOT BE MANUALLY SET IN A REAL WORKLOAD
    "category": "Financial Management", # 1:1
    "area": "Accounts Receivable", #1:1
    "name": "GPS Workflow Client Invoice Approval", #1:1
    "link": "https://workflow.gps.deloitte.com/#/section-dashboard/mytasks/open",
    "type": "Application",
    "restricted_access": True,
    "lifecycle": [ #1:Many
        "Delivery",
        "Closeout"
    ],
    "target_user": [ #1:Many
        "Enabling Areas Professional",
        "Engagement Manager",
        "PPMD"
    ],
    "description": "A dashboard showing My Tasks in Workflow containing all client invoices currently requiring approval", #Free Text
    "search_terms": [ #should be able to search with free search, used for searching not UI
        "Workflow",
        "my tasks",
        "client invoice",
        "invoice",
        "approving invoice"
    ],
    "instructions": "How do I approve or reject a client invoice?"
}

user1 = {
    "useremail": "johndoe@deloitte.com",
    "updated": datetime.datetime.utcnow(),
    "bookmarks": [ #correct decision (bookmark array per user)
        {
        "created": datetime.datetime.utcnow(),
        "toolID": ObjectId("63b8401dac80f1a9d6f6bc0c")
        }
    ]
}


def userBookmarkTool(collection,usrEmail,toolID):
    resultObj = collection.update_one({"useremail":usrEmail},{"$push":{"bookmarks":{"toolID":toolID,"created":datetime.datetime.utcnow()}}})
    print(resultObj.raw_result)

print("Running")

### ~~~~~   List of operations   ~~~~~

#create user and tool collections, insert sample data
if 'init' in operations:
    userCollection.insert_one(user1)
    toolCollection.insert_one(tool1)

#use the aggregation framework to return a user and the user's associated bookmarked tools
if 'userBookmarks' in operations:
    pipeline = [
            {
                '$match': {
                    'useremail': 'johndoe@deloitte.com'
                }
            }, {
                '$unwind': {
                    'path': '$bookmarks', 
                    'preserveNullAndEmptyArrays': True
                }
            }, {
                '$lookup': {
                    'from': 'tools', 
                    'localField': 'bookmarks.toolID', 
                    'foreignField': '_id', 
                    'as': 'bookmarkedTool'
                }
            }
        ]

    print(list(userCollection.aggregate(pipeline)))

#updateTool and updateToolBack change the name field of a tool with a given ObjectID
if 'updateTool' in operations:
    #print is to show that there is an update object that comes back from the server after an update statement
    print(toolCollection.update_one({'_id': ObjectId("63b8401dac80f1a9d6f6bc0c")}, {'$set': {'name': "GPS Wiki"}}))

if 'updateToolBack' in operations:
    toolCollection.update_one({'_id': ObjectId("63b8401dac80f1a9d6f6bc0c")}, {'$set': {'name': "Wiki"}})

#this calls the previously defined function 'userBookmarkTool' which adds a new tool to a user's bookmark array
if 'addNewTool' in operations:
    userBookmarkTool(userCollection,"johndoe@deloitte.com",ObjectId("63bd9b2ea248cd11a879b965"))

#this uses a search index (default index name) to do a fuzzy match against the tools collection search tems
if 'basicSearch' in operations:
    pipeline = [
            {
                '$search': {
                    'text': {
                        'query': 'invoic', 
                        'path': 'search_terms', 
                        'fuzzy': {
                            'maxEdits': 2
                        }
                    }
                }
            }
        ]
    print(list(toolCollection.aggregate(pipeline)))

if 'currentop' in operations:
    db = client.admin
    updateResponse = db.aggregate([{ "$currentOp": {} }])
    for document in updateResponse:
        print(document)
