# GolfTournament Application

REST Spring Boot application that receives golf tournament data from different sources, maps the incoming JSON into a unify format, and saves it in a database.

## Overview

- A single POST endpoint `/tournaments` accepts JSON data from various sources.
- The source of the data is indicated by `xSource` HTTP header.
- Each source has a specific mapper to convert data to a unified DTO format.
- Non-standard fields are stored in the `additionalData` field in JSON structure.
- The mapped data is stored in table `golf_tournament`.

## API Endpoints

### POST `/tournaments`

Create a new tournament. The request body must contain JSON data and the HTTP header `xSource` specifying the source.

#### Headers

```
xSource: source_1
Content-Type: application/json
```

#### Request Body Example (source_1)

```json
{
  "tournamentId": "174638",
  "tournamentName": "Women's Open Championship",
  "forecast": "fair",
  "courseName": "Sunnydale Golf Course",
  "countryCode": "GB",
  "startDate": "09/07/21",
  "endDate": "13/07/21",
  "roundCount": "4"
}
```

#### Request Body Example (source_2)

```json
{
  "tournamentUUID": "87fc6650-e114-4179-9aef-6a9a13030f80",
  "golfCourse": "Happy Days Golf Club",
  "competitionName": "South West Invitational",
  "hostCountry": "United States Of America",
  "epochStart": "1638349200",
  "epochFinish": "1638468000",
  "rounds": "2",
  "playerCount": "35"
}
```

### GET `/tournaments/by-externalId?externalId={externalId}`

Retrieve a tournament by external ID.

### GET `/tournaments/by-source?source={source}`

Retrieve all tournaments by source.

### GET `/tournaments/by-course?course={courseName}`

Retrieve all tournaments by specific golf course name.

### GET `/tournaments/by-country?countryCode={countryCode}`

Retrieve all tournaments in a specific country.

## Output Format (DTO)

### Example Output (source_1)

```json
[
  {
    "externalId": "174638",
    "courseName": "Sunnydale Golf Course",
    "country": "GB",
    "startDate": [2021, 7, 9, 0, 0],
    "endDate": [2021, 7, 13, 0, 0],
    "roundCount": 4,
    "source": "source_1",
    "additionalData": {
      "tournamentName": "Women's Open Championship",
      "forecast": "fair"
    }
  }
]
```

### Example Output (source_2)

```json
{
  "externalId": "87fc6650-e114-4179-9aef-6a9a13030f80",
  "courseName": "Happy Days Golf Club",
  "country": "US",
  "startDate": [2021, 12, 1, 0, 0],
  "endDate": [2021, 12, 2, 0, 0],
  "roundCount": 2,
  "source": "source_2",
  "additionalData": {
    "competitionName": "South West Invitational",
    "playerCount": "35"
  }
}
```

## Database Representation

| id | additional_data | country | course_name | end_date | external_id | round_count | source | start_date |
|----|-----------------|---------|-------------|----------|-------------|-------------|--------|------------|
| 5  | {"competitionName":"South West Invitational","playerCount":"35"} | US | Happy Days Golf Club | 2021-12-02 | 87fc6650-e114-4179-9aef-6a9a13030f80 | 2 | source_2 | 2021-12-01 |
| 6  | {"tournamentName":"Women's Open Championship","forecast":"fair"} | GB | Sunnydale Golf Course | 2021-07-13 | 174638 | 4 | source_1 | 2021-07-09 |