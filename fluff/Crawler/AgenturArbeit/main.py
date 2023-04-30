# Started from sample https://github.com/bundesAPI/jobsuche-api/blob/main/python-client/README.md
# Added OAuth code according to https://github.com/bundesAPI/jobsuche-api/blob/main/README.md
#
#
#
# THIS IS JUST AN TEST SCRIPT AN DOES NOT PROCIDE BUILD STANDARTS AND CLEAN CODE RULES
#
#
#       use it as 'python main.py'
#
import requests
import json
from deutschland import jobsuche
from pprint import pprint
from deutschland.jobsuche.api import default_api
from deutschland.jobsuche.model.job_application_details import JobApplicationDetails
from deutschland.jobsuche.model.job_details import JobDetails
from deutschland.jobsuche.model.job_search_response import JobSearchResponse
import pandas as pd
import os

# add chechup so file will be renewed frequently
def gain_city_databse():
    pathGps = "de.csv"
    gps_cities = pd.read_csv(pathGps, encoding='ISO-8859-1', sep=",")
    return gps_cities


def get_access_token():
    global access_token
    """ 
        Access Token: Get the new access token
        params : client_id,
                client_secret,
                grant_type,
    """
    headers = {
        'Host': 'rest.arbeitsagentur.de',
        'Accept': '*/*',
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8',
        'Accept-Language': 'en-us',
        'User-Agent': 'Jobsuche/1070 CFNetwork/1220.1 Darwin/20.3.0',
    }
    data = {
        'client_id': 'c003a37f-024f-462a-b36d-b001be4cd24a',
        'client_secret': '32a39620-32b3-4307-9aa1-511e3d7f48a8',
        'grant_type': 'client_credentials'
    }
    response = requests.post(
        'https://rest.arbeitsagentur.de/oauth/gettoken_cc', headers=headers, data=data)
    response = response.json()
    access_token = response['access_token']
    print("got token OK") if len(
        access_token) > 10 else print("token not long enough")
    return access_token


access_token = get_access_token()
# cities = gain_city_databse()

# The client must configure the authentication and authorization parameters
# in accordance with the API server security policy.
# Examples for each auth method are provided below, use the example that
# satisfies your auth use case.

# Configure OAuth2 access token for authorization: clientCredAuth
configuration = jobsuche.Configuration(
    host="https://rest.arbeitsagentur.de/jobboerse/jobsuche-service"
)
configuration.access_token = access_token

headers = {"OAuthAccessToken": access_token}

# word which will be looked for
targetWords = ["Vue.js", "Angular", "Node.js", "Python"]

for targetWord in targetWords:
    params = {"was": targetWord,
                "umkreis": 0,
                "size": 100,
                "angebotsart": 1,
                "pav": "false"}
    
    # API REQUEST
    response = requests.get(
        "https://rest.arbeitsagentur.de/jobboerse/jobsuche-service/pc/v4/jobs", headers=headers, params=params)

    if (response.status_code != 200):
        # something went wrong
        print(response.text)
        exit

    y = json.loads(response.text)

    print("Anzahl der Stellenangebote ", len(y["stellenangebote"]))
    print("Anzahl für ", targetWord)
    print(y["maxErgebnisse"])
    input()